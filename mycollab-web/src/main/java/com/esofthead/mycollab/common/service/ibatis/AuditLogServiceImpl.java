package com.esofthead.mycollab.common.service.ibatis;

import com.esofthead.mycollab.common.dao.AuditLogMapper;
import com.esofthead.mycollab.common.dao.AuditLogMapperExt;
import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Service
public class AuditLogServiceImpl extends DefaultService<Integer, AuditLog, AuditLogSearchCriteria>
        implements AuditLogService {

    private static Logger log = LoggerFactory.getLogger(AuditLogServiceImpl.class);
    @Autowired
    protected AuditLogMapper auditLogMapper;
    @Autowired
    protected AuditLogMapperExt auditLogMapperExt;

    @Override
    public ICrudGenericDAO<Integer, AuditLog> getCrudMapper() {
        return auditLogMapper;
    }

    @Override
    public ISearchableDAO<AuditLogSearchCriteria> getSearchMapper() {
        return auditLogMapperExt;
    }

    @Override
    public void saveAuditLog(String username, String module, String type, int typeid, int sAccountId, Object oldObj,
            Object newObj) {
        AuditLog auditLog = new AuditLog();
        auditLog.setPosteduser(username);
        auditLog.setModule(module);
        auditLog.setType(type);
        auditLog.setTypeid(typeid);
        auditLog.setSaccountid(sAccountId);
        auditLog.setPosteddate(new GregorianCalendar().getTime());
        auditLog.setChangeset(AuditLogUtil.getChangeSet(oldObj, newObj));
        auditLog.setObjectClass(oldObj.getClass().getName());
        auditLogMapper.insert(auditLog);
    }

    public static class AuditLogUtil {

        static public String getChangeSet(Object oldObj, Object newObj) {
            Class cl = oldObj.getClass();
            Field[] declaredFields = cl.getFields();

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                    .newInstance();
            Document document;
            try {
                DocumentBuilder docBuilder = builderFactory
                        .newDocumentBuilder();
                document = docBuilder.newDocument();
            } catch (ParserConfigurationException e1) {
                return "";
            }

            Element changesetElement = document.createElement("changeset");
            document.appendChild(changesetElement);

            for (int i = 0; i < declaredFields.length; i++) {
                Field field = declaredFields[i];
                String fieldname = field.getName();
                String setterMethod = "set"
                        + (char) (fieldname.charAt(0) + 'A' - 'a')
                        + fieldname.substring(1);
                String getterMethod = "get"
                        + (char) (fieldname.charAt(0) + 'A' - 'a')
                        + fieldname.substring(1);
                try {
                    Method getMethod = cl.getMethod(getterMethod, null);
                    Method setMethod = cl.getMethod(setterMethod,
                            new Class[]{field.getType()});

                    Object oldProp = getMethod.invoke(oldObj, null);
                    Object newProp = getMethod.invoke(newObj, null);

                    if (newProp != null) {
                        if (oldProp == null) {
                            Element changelogElement = document
                                    .createElement("changelog");
                            changelogElement.setAttribute("field", fieldname);
                            changelogElement.setAttribute("newvalue",
                                    newProp.toString());
                            changelogElement.setAttribute("oldvalue", "");
                            changesetElement.appendChild(changelogElement);
                        } else {
                            if (!newProp.toString().equals(oldProp.toString())) {
                                Element changelogElement = document
                                        .createElement("changelog");
                                changelogElement.setAttribute("field",
                                        fieldname);
                                if (field.getType() == Date.class) {
                                    changelogElement.setAttribute("newvalue",
                                            formatDateW3C((Date) newProp));
                                    changelogElement.setAttribute("oldvalue",
                                            formatDateW3C((Date) oldProp));
                                } else {
                                    changelogElement.setAttribute("newvalue",
                                            newProp.toString());
                                    changelogElement.setAttribute("oldvalue",
                                            oldProp.toString());
                                }
                                changesetElement.appendChild(changelogElement);
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("There is error when convert changeset", e);
                }
            }

            // convert xml document to string
            return getStringFromDocument(document);
        }

        static private String formatDateW3C(Date date) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            String text = df.format(date);
            String result = text.substring(0, 22) + ":" + text.substring(22);
            return result;
        }

        // method to convert Document to String
        static private String getStringFromDocument(Document doc) {
            try {
                DOMSource domSource = new DOMSource(doc);
                StringWriter writer = new StringWriter();
                StreamResult result = new StreamResult(writer);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.transform(domSource, result);
                return writer.toString();
            } catch (TransformerException ex) {
                log.error("Convert xml to string is failed", ex);
                return "";
            }
        }
    }
}

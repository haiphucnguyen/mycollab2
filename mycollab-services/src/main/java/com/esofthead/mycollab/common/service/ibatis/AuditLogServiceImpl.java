/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.service.ibatis;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.esofthead.mycollab.common.dao.AuditLogMapper;
import com.esofthead.mycollab.common.dao.AuditLogMapperExt;
import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;

@Service
public class AuditLogServiceImpl extends
		DefaultService<Integer, AuditLog, AuditLogSearchCriteria> implements
		AuditLogService {

	private static Logger log = LoggerFactory
			.getLogger(AuditLogServiceImpl.class);
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

	public static class AuditLogUtil {

		static public String getChangeSet(Object oldObj, Object newObj) {
			Class cl = oldObj.getClass();

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
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(cl, Object.class);

				for (PropertyDescriptor propertyDescriptor : beanInfo
						.getPropertyDescriptors()) {
					String fieldname = propertyDescriptor.getName();
					String oldProp = getValue(PropertyUtils.getProperty(oldObj,
							fieldname));
					String newProp = getValue(PropertyUtils.getProperty(newObj,
							fieldname));

					if (!oldProp.equals(newProp)) {
						Element changelogElement = document
								.createElement("changelog");
						changelogElement.setAttribute("field", fieldname);
						changelogElement.setAttribute("newvalue", newProp);
						changelogElement.setAttribute("oldvalue", oldProp);
						changesetElement.appendChild(changelogElement);
					}
				}
			} catch (Exception e) {
				log.error("There is error when convert changeset", e);
				return "";
			}

			// convert xml document to string
			return getStringFromDocument(document);
		}

		static String getValue(Object obj) {
			if (obj != null) {
				if (obj instanceof Date) {
					return formatDateW3C((Date) obj);
				} else {
					return obj.toString();
				}
			} else {
				return "";
			}
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

	@Override
	public SimpleAuditLog findLatestLog(int auditLogId, int sAccountId) {
		return auditLogMapperExt.findLatestLog(auditLogId);
	}
}

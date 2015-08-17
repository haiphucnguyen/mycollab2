package com.esofthead.mycollab.common.interceptor.aspect;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.core.utils.JsonDeSerializer;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
public class AuditLogUtil {
    private static Logger LOG = LoggerFactory.getLogger(AuditLogUtil.class);

    static public String getChangeSet(Object oldObj, Object newObj) {
        Class cl = oldObj.getClass();
        List<AuditChangeItem> changeItems = new ArrayList<>();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(cl, Object.class);

            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                String fieldName = propertyDescriptor.getName();
                String oldProp = getValue(PropertyUtils.getProperty(oldObj, fieldName));

                Object newPropVal;
                try {
                    newPropVal = PropertyUtils.getProperty(newObj, fieldName);
                } catch (Exception e) {
                    continue;
                }
                String newProp = getValue(newPropVal);

                if (!oldProp.equals(newProp)) {
                    AuditChangeItem changeItem = new AuditChangeItem();
                    changeItem.setField(fieldName);
                    changeItem.setNewvalue(newProp);
                    changeItem.setOldvalue(oldProp);
                    changeItems.add(changeItem);
                }
            }
        } catch (Exception e) {
            LOG.error("There is error when convert changeset", e);
            return "";
        }

        return JsonDeSerializer.toJson(changeItems);
    }

    private static String getValue(Object obj) {
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
        return text.substring(0, 22) + ":" + text.substring(22);
    }
}

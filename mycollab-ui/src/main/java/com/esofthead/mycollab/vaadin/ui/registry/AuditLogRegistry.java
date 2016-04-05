package com.esofthead.mycollab.vaadin.ui.registry;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.utils.FieldGroupFormatter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
@Component
public class AuditLogRegistry implements InitializingBean {
    private Map<String, FieldGroupFormatter> auditPrinters;

    @Override
    public void afterPropertiesSet() throws Exception {
        auditPrinters = new HashMap<>();
    }

    public void registerAuditLogHandler(String type, FieldGroupFormatter fieldGroupFormatter) {
        auditPrinters.put(type, fieldGroupFormatter);
    }

    public String generatorDetailChangeOfActivity(SimpleActivityStream activityStream) {
        if (activityStream.getAssoAuditLog() != null) {
            FieldGroupFormatter groupFormatter = auditPrinters.get(activityStream.getType());
            if (groupFormatter != null) {
                StringBuffer str = new StringBuffer("");
                boolean isAppended = false;
                List<AuditChangeItem> changeItems = activityStream.getAssoAuditLog().getChangeItems();
                if (CollectionUtils.isNotEmpty(changeItems)) {
                    for (AuditChangeItem item : changeItems) {
                        String fieldName = item.getField();
                        FieldGroupFormatter.FieldDisplayHandler fieldDisplayHandler = groupFormatter.getFieldDisplayHandler(fieldName);
                        if (fieldDisplayHandler != null) {
                            isAppended = true;
                            str.append(fieldDisplayHandler.generateLogItem(item));
                        }
                    }

                }
                if (isAppended) {
                    str.insert(0, "<p>").insert(0, "<ul>");
                    str.append("</ul>").append("</p>");
                }
                return str.toString();
            }

        }
        return "";
    }
}

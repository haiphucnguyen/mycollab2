package com.esofthead.mycollab.utils;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.utils.FieldGroupFormatter.FieldDisplayHandler;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.3.3
 */
public class AuditLogPrinter {
    private FieldGroupFormatter groupFormatter;

    public AuditLogPrinter(FieldGroupFormatter groupFormatter) {
        this.groupFormatter = groupFormatter;
    }

    public String generateChangeSet(SimpleAuditLog auditLog) {
        StringBuffer str = new StringBuffer("");
        boolean isAppended = false;
        List<AuditChangeItem> changeItems = auditLog.getChangeItems();
        if (CollectionUtils.isNotEmpty(changeItems)) {
            for (AuditChangeItem item : changeItems) {
                String fieldName = item.getField();
                FieldDisplayHandler fieldDisplayHandler = groupFormatter.getFieldDisplayHandler(fieldName);
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
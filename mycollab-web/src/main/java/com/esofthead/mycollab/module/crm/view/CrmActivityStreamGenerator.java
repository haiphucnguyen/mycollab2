/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.ui.format.*;
import com.esofthead.mycollab.utils.FieldGroupFormatter;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CrmActivityStreamGenerator {

    private static final Map<String, FieldGroupFormatter> auditPrinters;

    static {
        auditPrinters = new HashMap<>();
        auditPrinters.put(CrmTypeConstants.ACCOUNT, AccountFieldFormatter.instance());
        auditPrinters.put(CrmTypeConstants.CONTACT, ContactFieldFormatter.instance());
        auditPrinters.put(CrmTypeConstants.CAMPAIGN, CampaignFieldFormatter.instance());
        auditPrinters.put(CrmTypeConstants.LEAD, LeadFieldFormatter.instance());
        auditPrinters.put(CrmTypeConstants.OPPORTUNITY, OpportunityFieldFormatter.instance());
        auditPrinters.put(CrmTypeConstants.CASE, CaseFieldFormatter.instance());
        auditPrinters.put(CrmTypeConstants.MEETING, MeetingFieldFormatter.instance());
        auditPrinters.put(CrmTypeConstants.TASK, AssignmentFieldFormatter.instance());
        auditPrinters.put(CrmTypeConstants.CALL, CallFieldFormatter.instance());
    }

    public static String generatorDetailChangeOfActivity(SimpleActivityStream activityStream) {
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

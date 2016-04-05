package com.esofthead.mycollab.module.crm.ui.registry;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.ui.format.*;
import com.esofthead.mycollab.vaadin.ui.registry.AuditLogRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
@Component
public class CrmAuditLogRegistry implements InitializingBean {
    @Autowired
    private AuditLogRegistry auditLogRegistry;

    @Override
    public void afterPropertiesSet() throws Exception {
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.ACCOUNT, AccountFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.CONTACT, ContactFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.CAMPAIGN, CampaignFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.LEAD, LeadFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.OPPORTUNITY, OpportunityFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.CASE, CaseFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.MEETING, MeetingFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.TASK, AssignmentFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.CALL, CallFieldFormatter.instance());
    }
}

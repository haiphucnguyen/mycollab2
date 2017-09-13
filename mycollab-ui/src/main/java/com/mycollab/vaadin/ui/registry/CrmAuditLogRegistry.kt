package com.mycollab.vaadin.ui.registry

import com.mycollab.module.crm.CrmTypeConstants
import com.mycollab.module.crm.ui.format.*
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
class CrmAuditLogRegistry  : InitializingBean {
    @Autowired private val auditLogRegistry: AuditLogRegistry? = null

    override fun afterPropertiesSet() {
        auditLogRegistry!!.registerAuditLogHandler(CrmTypeConstants.ACCOUNT, AccountFieldFormatter.instance())
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.CONTACT, ContactFieldFormatter.instance())
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.CAMPAIGN, CampaignFieldFormatter.instance())
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.LEAD, LeadFieldFormatter.instance())
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.OPPORTUNITY, OpportunityFieldFormatter.instance())
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.CASE, CaseFieldFormatter.instance())
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.MEETING, MeetingFieldFormatter.instance())
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.TASK, AssignmentFieldFormatter.instance())
        auditLogRegistry.registerAuditLogHandler(CrmTypeConstants.CALL, CallFieldFormatter.instance())
    }
}
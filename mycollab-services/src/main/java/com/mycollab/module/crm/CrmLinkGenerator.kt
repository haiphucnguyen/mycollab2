package com.mycollab.module.crm

import com.mycollab.common.GenericLinkUtils

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
object CrmLinkGenerator {
    fun generateAccountPreviewLink(accountId: Int?): String {
        return "crm/account/preview/" + GenericLinkUtils.encodeParam(accountId!!)
    }

    fun generateAccountPreviewFullLink(siteUrl: String, accountId: Int?): String {
        return siteUrl + "#" + generateAccountPreviewLink(accountId)
    }

    fun generateCampaignPreviewLink(campaignId: Int?): String {
        return "crm/campaign/preview/" + GenericLinkUtils.encodeParam(campaignId!!)
    }

    fun generateCampaignPreviewFullLink(siteUrl: String, campaignId: Int?): String {
        return siteUrl + "#" + generateCampaignPreviewLink(campaignId)
    }

    fun generateCasePreviewLink(caseId: Int?): String {
        return "crm/cases/preview/" + GenericLinkUtils.encodeParam(caseId!!)
    }

    fun generateCasePreviewFullLink(siteUrl: String, caseId: Int?): String {
        return siteUrl + "#" + generateCasePreviewLink(caseId)
    }

    fun generateContactPreviewLink(contactId: Int?): String {
        return "crm/contact/preview/" + GenericLinkUtils.encodeParam(contactId!!)
    }

    fun generateContactPreviewFullLink(siteUrl: String, contactId: Int?): String {
        return siteUrl + "#" + generateContactPreviewLink(contactId)
    }

    fun generateLeadPreviewLink(leadId: Int?): String {
        return "crm/lead/preview/" + GenericLinkUtils.encodeParam(leadId!!)
    }

    fun generateLeadPreviewFullLink(siteUrl: String, leadId: Int?): String {
        return siteUrl + "#" + generateLeadPreviewLink(leadId)
    }

    fun generateOpportunityPreviewLink(opportunityId: Int?): String {
        return "crm/opportunity/preview/" + GenericLinkUtils.encodeParam(opportunityId!!)
    }

    fun generateOpportunityPreviewFullLink(siteUrl: String, opportunityId: Int?): String {
        return siteUrl + "#" + generateOpportunityPreviewLink(opportunityId)
    }

    fun generateTaskPreviewLink(taskId: Int?): String {
        return "crm/activity/task/preview/" + GenericLinkUtils.encodeParam(taskId!!)
    }

    fun generateTaskPreviewFullLink(siteUrl: String, taskId: Int?): String {
        return siteUrl + "#" + generateTaskPreviewLink(taskId)
    }

    fun generateMeetingPreviewLink(meetingId: Int?): String {
        return "crm/activity/meeting/preview/" + GenericLinkUtils.encodeParam(meetingId!!)
    }

    fun generateMeetingPreviewFullLink(siteUrl: String, meetingId: Int?): String {
        return siteUrl + "#" + generateMeetingPreviewLink(meetingId)
    }

    fun generateCallPreviewLink(callId: Int?): String {
        return "crm/activity/call/preview/" + GenericLinkUtils.encodeParam(callId!!)
    }

    fun generateCallPreviewFullLink(siteUrl: String, callId: Int?): String {
        return siteUrl + "#" + generateCallPreviewLink(callId)
    }

    fun generateCrmItemLink(type: String, typeId: Int?): String {
        var result = ""

        when (type) {
            CrmTypeConstants.ACCOUNT -> result = generateAccountPreviewLink(typeId)
            CrmTypeConstants.CAMPAIGN -> result = generateCampaignPreviewLink(typeId)
            CrmTypeConstants.CASE -> result = generateCasePreviewLink(typeId)
            CrmTypeConstants.CONTACT -> result = generateContactPreviewLink(typeId)
            CrmTypeConstants.LEAD -> result = generateLeadPreviewLink(typeId)
            CrmTypeConstants.OPPORTUNITY -> result = generateOpportunityPreviewLink(typeId)
            CrmTypeConstants.TASK -> result = generateTaskPreviewLink(typeId)
            CrmTypeConstants.MEETING -> result = generateMeetingPreviewLink(typeId)
            CrmTypeConstants.CALL -> result = generateCallPreviewLink(typeId)
        }
        return "#" + result
    }

    fun generateCrmItemFullLink(siteUrl: String, type: String, typeId: Int?): String {
        return siteUrl + generateCrmItemLink(type, typeId)
    }
}

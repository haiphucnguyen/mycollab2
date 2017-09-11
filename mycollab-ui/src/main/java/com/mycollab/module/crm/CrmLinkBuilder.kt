package com.mycollab.module.crm

import com.mycollab.common.GenericLinkUtils.URL_PREFIX_PARAM
import com.mycollab.vaadin.AppUI

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
object CrmLinkBuilder {

    fun generateAccountPreviewLinkFull(accountId: Int?): String =
            URL_PREFIX_PARAM + CrmLinkGenerator.generateAccountPreviewLink(accountId)

    fun generateCampaignPreviewLinkFull(campaignId: Int?): String =
            URL_PREFIX_PARAM + CrmLinkGenerator.generateCampaignPreviewLink(campaignId)

    fun generateCasePreviewLinkFull(caseId: Int?): String =
            URL_PREFIX_PARAM + CrmLinkGenerator.generateCasePreviewLink(caseId)

    fun generateContactPreviewLinkFull(contactId: Int?): String =
            URL_PREFIX_PARAM + CrmLinkGenerator.generateContactPreviewLink(contactId)

    fun generateLeadPreviewLinkFull(leadId: Int?): String =
            URL_PREFIX_PARAM + CrmLinkGenerator.generateLeadPreviewLink(leadId)

    fun generateOpportunityPreviewLinkFull(opportunityId: Int?): String =
            URL_PREFIX_PARAM + CrmLinkGenerator.generateOpportunityPreviewLink(opportunityId)

    fun generateActivityPreviewLinkFull(type: String, typeId: Int?): String =
            CrmLinkGenerator.generateCrmItemFullLink(AppUI.getSiteUrl(), type, typeId)

    fun generateMeetingPreviewLinkFull(meetingId: Int?): String =
            URL_PREFIX_PARAM + CrmLinkGenerator.generateMeetingPreviewLink(meetingId)

    fun generateCallPreviewLinkFul(callId: Int?): String =
            URL_PREFIX_PARAM + CrmLinkGenerator.generateCallPreviewLink(callId)
}
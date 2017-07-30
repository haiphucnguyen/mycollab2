package com.mycollab.module.crm;

import com.mycollab.vaadin.AppUI;

import static com.mycollab.common.GenericLinkUtils.URL_PREFIX_PARAM;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CrmLinkBuilder {

    public static String generateAccountPreviewLinkFull(Integer accountId) {
        return URL_PREFIX_PARAM + CrmLinkGenerator.generateAccountPreviewLink(accountId);
    }

    public static String generateCampaignPreviewLinkFull(Integer campaignId) {
        return URL_PREFIX_PARAM + CrmLinkGenerator.generateCampaignPreviewLink(campaignId);
    }

    public static String generateCasePreviewLinkFull(Integer caseId) {
        return URL_PREFIX_PARAM + CrmLinkGenerator.generateCasePreviewLink(caseId);
    }

    public static String generateContactPreviewLinkFull(Integer contactId) {
        return URL_PREFIX_PARAM + CrmLinkGenerator.generateContactPreviewLink(contactId);
    }

    public static String generateLeadPreviewLinkFull(Integer leadId) {
        return URL_PREFIX_PARAM + CrmLinkGenerator.generateLeadPreviewLink(leadId);
    }

    public static String generateOpportunityPreviewLinkFull(Integer opportunityId) {
        return URL_PREFIX_PARAM + CrmLinkGenerator.generateOpportunityPreviewLink(opportunityId);
    }

    public static String generateActivityPreviewLinkFull(String type, Integer typeId) {
        return CrmLinkGenerator.generateCrmItemFullLink(AppUI.getSiteUrl(), type, typeId);
    }

    public static String generateMeetingPreviewLinkFull(Integer meetingId) {
        return URL_PREFIX_PARAM + CrmLinkGenerator.generateMeetingPreviewLink(meetingId);
    }

    public static String generateCallPreviewLinkFul(Integer callId) {
        return URL_PREFIX_PARAM + CrmLinkGenerator.generateCallPreviewLink(callId);
    }
}
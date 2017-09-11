package com.mycollab.module.crm;

import com.mycollab.vaadin.AppUI;

import static com.mycollab.common.GenericLinkUtils.URL_PREFIX_PARAM;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CrmLinkBuilder {

    public static String generateAccountPreviewLinkFull(Integer accountId) {
        return INSTANCE.getURL_PREFIX_PARAM() + CrmLinkGenerator.INSTANCE.generateAccountPreviewLink(accountId);
    }

    public static String generateCampaignPreviewLinkFull(Integer campaignId) {
        return INSTANCE.getURL_PREFIX_PARAM() + CrmLinkGenerator.INSTANCE.generateCampaignPreviewLink(campaignId);
    }

    public static String generateCasePreviewLinkFull(Integer caseId) {
        return INSTANCE.getURL_PREFIX_PARAM() + CrmLinkGenerator.INSTANCE.generateCasePreviewLink(caseId);
    }

    public static String generateContactPreviewLinkFull(Integer contactId) {
        return INSTANCE.getURL_PREFIX_PARAM() + CrmLinkGenerator.INSTANCE.generateContactPreviewLink(contactId);
    }

    public static String generateLeadPreviewLinkFull(Integer leadId) {
        return INSTANCE.getURL_PREFIX_PARAM() + CrmLinkGenerator.INSTANCE.generateLeadPreviewLink(leadId);
    }

    public static String generateOpportunityPreviewLinkFull(Integer opportunityId) {
        return INSTANCE.getURL_PREFIX_PARAM() + CrmLinkGenerator.INSTANCE.generateOpportunityPreviewLink(opportunityId);
    }

    public static String generateActivityPreviewLinkFull(String type, Integer typeId) {
        return CrmLinkGenerator.INSTANCE.generateCrmItemFullLink(AppUI.getSiteUrl(), type, typeId);
    }

    public static String generateMeetingPreviewLinkFull(Integer meetingId) {
        return INSTANCE.getURL_PREFIX_PARAM() + CrmLinkGenerator.INSTANCE.generateMeetingPreviewLink(meetingId);
    }

    public static String generateCallPreviewLinkFul(Integer callId) {
        return INSTANCE.getURL_PREFIX_PARAM() + CrmLinkGenerator.INSTANCE.generateCallPreviewLink(callId);
    }
}
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;

public class CrmLinkGenerator {

	public static String generateAccountPreviewLink(Integer accountId) {
		return "crm/account/preview/" + UrlEncodeDecoder.encode(accountId);
	}

	public static String generateCampaignPreviewLink(Integer campaignId) {
		return "crm/campaign/preview/" + UrlEncodeDecoder.encode(campaignId);
	}

	public static String generateCasePreviewLink(Integer caseId) {
		return "crm/cases/preview/" + UrlEncodeDecoder.encode(caseId);
	}

	public static String generateContactPreviewLink(Integer contactId) {
		return "crm/contact/preview/" + UrlEncodeDecoder.encode(contactId);
	}

	public static String generateLeadPreviewLink(Integer leadId) {
		return "crm/lead/preview/" + UrlEncodeDecoder.encode(leadId);
	}

	public static String generateOpportunityPreviewLink(Integer opportunityId) {
		return "crm/opportunity/preview/"
				+ UrlEncodeDecoder.encode(opportunityId);
	}

	public static String generateTaskPreviewLink(Integer taskId) {
		return "crm/activity/task/preview/" + UrlEncodeDecoder.encode(taskId);
	}

	public static String generateMeetingPreviewLink(Integer meetingId) {
		return "crm/activity/meeting/preview/"
				+ UrlEncodeDecoder.encode(meetingId);
	}

	public static String generateCallPreviewLink(Integer callId) {
		return "crm/activity/call/preview/" + UrlEncodeDecoder.encode(callId);
	}

	public static String generateCrmItemLink(String type, int typeid) {
		String result = "";

		if (CrmTypeConstants.ACCOUNT.equals(type)) {
			result = generateAccountPreviewLink(typeid);
		} else if (CrmTypeConstants.CAMPAIGN.equals(type)) {
			result = generateCampaignPreviewLink(typeid);
		} else if (CrmTypeConstants.CASE.equals(type)) {
			result = generateCasePreviewLink(typeid);
		} else if (CrmTypeConstants.CONTACT.equals(type)) {
			result = generateContactPreviewLink(typeid);
		} else if (CrmTypeConstants.LEAD.equals(type)) {
			result = generateLeadPreviewLink(typeid);
		} else if (CrmTypeConstants.OPPORTUNITY.equals(type)) {
			result = generateOpportunityPreviewLink(typeid);
		} else if (CrmTypeConstants.TASK.equals(type)) {
			result = generateTaskPreviewLink(typeid);
		} else if (CrmTypeConstants.MEETING.equals(type)) {
			result = generateMeetingPreviewLink(typeid);
		} else if (CrmTypeConstants.CALL.equals(type)) {
			result = generateCallPreviewLink(typeid);
		}
		return "#" + result;
	}
}

package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;

public class CrmLinkGenerator {
	public static String generateCrmItemLink(String type, int typeid) {
		String result = "";

		if (CrmTypeConstants.ACCOUNT.equals(type)) {

		} else if (CrmTypeConstants.CAMPAIGN.equals(type)) {

		} else if (CrmTypeConstants.CASE.equals(type)) {

		} else if (CrmTypeConstants.CONTACT.equals(type)) {

		} else if (CrmTypeConstants.LEAD.equals(type)) {

		} else if (CrmTypeConstants.OPPORTUNITY.equals(type)) {

		} else if (CrmTypeConstants.TASK.equals(type)) {

		} else if (CrmTypeConstants.MEETING.equals(type)) {

		} else if (CrmTypeConstants.CALL.equals(type)) {

		}
		return result;
	}
}

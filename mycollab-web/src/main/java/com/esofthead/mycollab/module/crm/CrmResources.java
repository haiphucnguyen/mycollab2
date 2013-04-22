package com.esofthead.mycollab.module.crm;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.vaadin.ui.ResourceResolver;

public class CrmResources {
	private static final Map<String, String> resourceLinks;

	static {
		resourceLinks = new HashMap<String, String>();
		resourceLinks.put(CrmTypeConstants.ACCOUNT,
				ResourceResolver.getResourceLink("icons/16/crm/account.png"));
		resourceLinks.put(CrmTypeConstants.CALL,
				ResourceResolver.getResourceLink("icons/16/crm/call.png"));
		resourceLinks.put(CrmTypeConstants.CAMPAIGN,
				ResourceResolver.getResourceLink("icons/16/crm/campaign.png"));
		resourceLinks.put(CrmTypeConstants.CASE,
				ResourceResolver.getResourceLink("icons/16/crm/case.png"));
		resourceLinks.put(CrmTypeConstants.CONTACT,
				ResourceResolver.getResourceLink("icons/16/crm/contact.png"));
		resourceLinks.put(CrmTypeConstants.LEAD,
				ResourceResolver.getResourceLink("icons/16/crm/lead.png"));
		resourceLinks.put(CrmTypeConstants.MEETING,
				ResourceResolver.getResourceLink("icons/16/crm/meeting.png"));
		resourceLinks.put(CrmTypeConstants.OPPORTUNITY, ResourceResolver
				.getResourceLink("icons/16/crm/opportunity.png"));
		resourceLinks.put(CrmTypeConstants.TASK,
				ResourceResolver.getResourceLink("icons/16/crm/task.png"));

	}

	public static String getResourceLink(String type) {
		return resourceLinks.get(type);
	}
}

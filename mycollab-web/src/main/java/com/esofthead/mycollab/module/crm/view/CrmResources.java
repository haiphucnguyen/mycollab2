package com.esofthead.mycollab.module.crm.view;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.web.MyCollabResource;

public class CrmResources {
	private static final Map<String, String> resourceLinks;

	static {
		resourceLinks = new HashMap<String, String>();
		resourceLinks.put(CrmTypeConstants.ACCOUNT,
				MyCollabResource.newResourceLink("icons/16/crm/account.png"));
		resourceLinks.put(CrmTypeConstants.CALL,
				MyCollabResource.newResourceLink("icons/16/crm/call.png"));
		resourceLinks.put(CrmTypeConstants.CAMPAIGN,
				MyCollabResource.newResourceLink("icons/16/crm/campaign.png"));
		resourceLinks.put(CrmTypeConstants.CASE,
				MyCollabResource.newResourceLink("icons/16/crm/case.png"));
		resourceLinks.put(CrmTypeConstants.CONTACT,
				MyCollabResource.newResourceLink("icons/16/crm/contact.png"));
		resourceLinks.put(CrmTypeConstants.LEAD,
				MyCollabResource.newResourceLink("icons/16/crm/lead.png"));
		resourceLinks.put(CrmTypeConstants.MEETING,
				MyCollabResource.newResourceLink("icons/16/crm/meeting.png"));
		resourceLinks.put(CrmTypeConstants.OPPORTUNITY, MyCollabResource
				.newResourceLink("icons/16/crm/opportunity.png"));
		resourceLinks.put(CrmTypeConstants.TASK,
				MyCollabResource.newResourceLink("icons/16/crm/task.png"));

	}

	public static String getResourceLink(String type) {
		return resourceLinks.get(type);
	}
}

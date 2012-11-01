package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;

@SuppressWarnings("serial")
public class CampaignEvent  extends ApplicationEvent {
	
	public static String SAVE = "CRM_SAVE_CAMPAIGN_EVENT";
	
	public static String SEARCH = "CRM_SEARCH_CAMPAIGN";

	public static String GOTO_LIST_VIEW = "CRM_LIST_CAMPAIGN";

	public static String GOTO_ADD_VIEW = "CRM_ADD_CAMPAIGN";
	
	public static String GOTO_READ_VIEW = "CRM_VIEW_CAMPAIGN";

	public static String GOTO_EDIT_VIEW = "CRM_EDIT_CAMPAIGN";

	public CampaignEvent(Object source, String name) {
		super(source, name);
	}

	public CampaignEvent(Object source, String name, Object data) {
		super(source, name, data);
	}
}

package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;

public class ContactEvent  extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public static String SAVE = "CRM_SAVE_CONTACT_EVENT";
	
	public static String SEARCH = "CRM_SEARCH_CONTACT";

	public static String GOTO_LIST_VIEW = "CRM_LIST_CONTACT";

	public static String GOTO_ADD_VIEW = "CRM_ADD_CONTACT";
	
	public static String GOTO_READ_VIEW = "CRM_VIEW_CONTACT";

	public static String GOTO_EDIT_VIEW = "CRM_EDIT_CONTACT";
	

	public ContactEvent(Object source, String name) {
		super(source, name);
	}

	public ContactEvent(Object source, String name, Object data) {
		super(source, name, data);
	}
}

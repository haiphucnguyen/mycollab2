package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;

@SuppressWarnings("serial")
public class AccountEvent extends ApplicationEvent {
	
	public static String SAVE = "CRM_SAVE_ACCOUNT_EVENT";
	
	public static String SEARCH = "CRM_SEARCH_ACCOUNT";

	public static String GOTO_LIST_VIEW = "CRM_LIST_ACCOUNT";

	public static String GOTO_ADD_VIEW = "CRM_ADD_ACCOUNT";
	
	public static String GOTO_READ_VIEW = "CRM_VIEW_ACCOUNT";

	public static String GOTO_EDIT_VIEW = "CRM_EDIT_ACCOUNT";

	public AccountEvent(Object source, String name) {
		super(source, name);
	}

	public AccountEvent(Object source, String name, Object data) {
		super(source, name, data);
	}
}

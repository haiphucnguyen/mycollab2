package com.esofthead.mycollab.vaadin;

import com.vaadin.server.VaadinSession;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class MyCollabSession {
	public static final String EVENT_BUS_VAL = "eventBusVal";

	public static final String CURRENT_PROJECT = "project";

	public static final String PROJECT_MEMBER = "project_member";

	public static final String USER_TIMEZONE = "USER_TIMEZONE";

	public static final String CURRENT_MODULE = "currentModule";

	public static final String CONTROLLER_REGISTRY = "CONTROLLER_REGISTRY";

	public static final String PRESENTER_VAL = "presenterMap";

	public static final String VIEW_MANAGER_VAL = "viewMap";

	public static final String CURRENT_APP = "currentApp";

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public static void putVariable(String key, Object value) {
		VaadinSession.getCurrent().setAttribute(key, value);
	}

	/**
	 * 
	 * @param key
	 */
	public static void removeVariable(String key) {
		try {
			VaadinSession.getCurrent().setAttribute(key, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clearVariables() {
		removeVariable(EVENT_BUS_VAL);
		removeVariable(CURRENT_PROJECT);
		removeVariable(PROJECT_MEMBER);
		removeVariable(USER_TIMEZONE);
		removeVariable(CURRENT_MODULE);
		removeVariable(CONTROLLER_REGISTRY);
		removeVariable(PRESENTER_VAL);
		removeVariable(VIEW_MANAGER_VAL);
		removeVariable(CURRENT_APP);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static Object getVariable(String key) {
		return VaadinSession.getCurrent().getAttribute(key);
	}

}

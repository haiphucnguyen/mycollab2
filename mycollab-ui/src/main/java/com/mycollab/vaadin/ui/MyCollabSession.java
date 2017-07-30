package com.mycollab.vaadin.ui;

import com.mycollab.core.SessionExpireException;
import com.mycollab.vaadin.AppUI;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class MyCollabSession {

    public static final String USER_VAL = "userVal";

    public static final String EVENT_BUS_VAL = "eventBusVal";

    public static final String CURRENT_PROJECT = "project";

    public static final String PROJECT_MEMBER = "project_member";

    public static final String CURRENT_MODULE = "currentModule";

    public static final String CONTROLLER_REGISTRY = "CONTROLLER_REGISTRY";

    public static final String PRESENTER_VAL = "presenterMap";

    public static final String VIEW_MANAGER_VAL = "viewMap";

    private MyCollabSession() {
    }

    public static void putSessionVariable(String key, Object value) {
        try {
            VaadinSession.getCurrent().setAttribute(key, value);
        } catch (Exception e) {
            throw new SessionExpireException("Expire Exception");
        }
    }

    public static Object getSessionVariable(String key) {
        try {
            return VaadinSession.getCurrent().getAttribute(key);
        } catch (Exception e) {
            throw new SessionExpireException("Expire Exception");
        }
    }

    public static void removeSessionVariable(String key) {
        try {
            VaadinSession.getCurrent().setAttribute(key, null);
        } catch (Exception e) {
            throw new SessionExpireException("Expire Exception");
        }
    }

    /**
     * @param key
     * @param value
     */
    public static void putCurrentUIVariable(String key, Object value) {
        try {
            ((AppUI) UI.getCurrent()).setAttribute(key, value);
        } catch (Exception e) {
            throw new SessionExpireException("Expire Exception");
        }
    }

    /**
     * @param key
     */
    public static void removeCurrentUIVariable(String key) {
        try {
            ((AppUI) UI.getCurrent()).setAttribute(key, null);
        } catch (Exception e) {
            throw new SessionExpireException("Expire Exception");
        }
    }

    /**
     * @param key
     * @return
     */
    public static Object getCurrentUIVariable(String key) {
        try {
            return ((AppUI) UI.getCurrent()).getAttribute(key);
        } catch (Exception e) {
            throw new SessionExpireException("Expire Exception");
        }
    }
}
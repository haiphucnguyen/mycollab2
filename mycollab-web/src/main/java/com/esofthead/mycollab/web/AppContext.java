package com.esofthead.mycollab.web;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.common.domain.UserPreference;
import com.esofthead.mycollab.common.service.UserPreferenceService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext.TransactionListener;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AppContext implements TransactionListener, Serializable {

    private static final long serialVersionUID = 1L;
    private static int UPDATE_TIME_DURATION = 300000;
    private static Logger log = LoggerFactory.getLogger(AppContext.class);
    private static ThreadLocal<AppContext> instance = new ThreadLocal<AppContext>();
    
    private Application app;
    private SimpleUser session;
    private UserPreference userPreference;
    
    private Map<String, Object> variables = new HashMap<String, Object>();
    private long lastAccessTime = 0;

    public AppContext(Application application) {
        this.app = application;

        // It's usable from now on in the current request
        instance.set(this);
    }

    @Override
    public void transactionStart(Application application, Object transactionData) {
        // Set this data instance of this application
        // as the one active in the current thread.
        if (this.app == application) {
            instance.set(this);
        }

        log.debug("Transaction start: " + transactionData);
    }

    @Override
    public void transactionEnd(Application application, Object transactionData) {
        log.debug("Transaction end: " + transactionData);

        long currentTime = new GregorianCalendar().getTimeInMillis();
        if (currentTime - lastAccessTime > UPDATE_TIME_DURATION) {
            try {
                if (instance.get() != null && instance.get().userPreference != null) {
                    UserPreference pref = instance.get().userPreference;
                    UserPreferenceService prefService = AppContext.getSpringBean(UserPreferenceService.class);
                    pref.setLastaccessedtime(new GregorianCalendar().getTime());
                    prefService.updateWithSession(pref, AppContext.getUsername());
                    
                    lastAccessTime = currentTime;
                    log.debug("Update last access time of user " + AppContext.getUsername());
                }

            } catch (Exception e) {
                log.error("There is error when try to update user preference", e);
            }
        }
        
        // Clear the reference to avoid potential problems
        if (this.app == application) {
            instance.set(null);
        }
    }

    public static void updateLastModuleVisit(String moduleName) {
        try {
            UserPreference pref = instance.get().userPreference;
            UserPreferenceService prefService = AppContext.getSpringBean(UserPreferenceService.class);
            pref.setLastmodulevisit(moduleName);
            prefService.updateWithSession(pref, AppContext.getUsername());
        } catch (Exception e) {
            log.error("There is error when try to update user preference for last module visit", e);
        }
    }

    public static void setSession(SimpleUser userSession, UserPreference userPreference) {
        instance.get().session = userSession;
        instance.get().userPreference = userPreference;
    }

    public static SimpleUser getSession() {
        return instance.get().session;
    }

    public static Integer getAccountId() {
        return instance.get().session.getAccountid();
    }

    public static String getUsername() {
        return instance.get().session.getUsername();
    }

    public static UserPreference getUserPreference() {
        return instance.get().userPreference;
    }

    public static Application getApplication() {
        return instance.get().app;
    }

    public static <T> T getSpringBean(Class<T> requiredType) {
        WebApplicationContext context = (WebApplicationContext) instance.get().app
                .getContext();

        org.springframework.web.context.WebApplicationContext springContext = WebApplicationContextUtils
                .getRequiredWebApplicationContext(context.getHttpSession()
                .getServletContext());

        return springContext.getBean(requiredType);
    }
    
    public static boolean canRead(String permissionItem) {
        PermissionMap permissionMap = instance.get().session.getPermissionMaps();
        if (permissionMap == null) {
            return false;
        } else {
            return permissionMap.canRead(permissionItem);
        }
    }
    
    public static boolean canWrite(String permissionItem) {
        PermissionMap permissionMap = instance.get().session.getPermissionMaps();
        if (permissionMap == null) {
            return false;
        } else {
            return permissionMap.canWrite(permissionItem);
        }
    }
    
    public static boolean canAccess(String permissionItem) {
        PermissionMap permissionMap = instance.get().session.getPermissionMaps();
        if (permissionMap == null) {
            return false;
        } else {
            return permissionMap.canAccess(permissionItem);
        }
    }

    public static void putVariable(String key, Object value) {
        if (instance.get() != null) {
            instance.get().variables.put(key, value);
        }
    }

    public static Object getVariable(String key) {
        if (instance.get() != null) {
            return instance.get().variables.get(key);
        }
        return null;
    }

    static void clearAllVariables() {
        if (instance.get() != null) {
            instance.get().variables.clear();
        }

    }
    private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(
            "MM/dd/yyyy hh:mm a");
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "MM/dd/yyyy");
    private static SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd, hh:mm aa");

    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        return simpleDateTimeFormat.format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return simpleDateFormat.format(date);
    }
    
    public static String getDateFormat() {
        return "MM/dd/yyyy";
    }

    public static String getDateTimeFormat() {
        return "MM/dd/yyyy hh:mm a";
    }

    public static String formatDateToHumanRead(Date date) {
        if (date == null) {
            return "";
        }
        return df.format(date);

    }
}

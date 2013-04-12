package com.esofthead.mycollab.web;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.common.domain.UserPreference;
import com.esofthead.mycollab.common.service.UserPreferenceService;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.utils.StringUtils;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

public class AppContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int UPDATE_TIME_DURATION = 300000;

	private static Logger log = LoggerFactory.getLogger(AppContext.class);

	private final Map<String, Object> variables = new HashMap<String, Object>();

	private SimpleUser session;
	private UserPreference userPreference;
	private SimpleBillingAccount billingAccount;

	private long lastAccessTime = 0;
	private static org.springframework.web.context.WebApplicationContext springContext;

	public static String USER_TIMEZONE = "USER_TIMEZONE";

	public AppContext(Application application) {
		if (springContext == null) {
			WebApplicationContext context = (WebApplicationContext) application
					.getContext();
			springContext = WebApplicationContextUtils
					.getRequiredWebApplicationContext(context.getHttpSession()
							.getServletContext());
		}
	}

	public static AppContext getInstance() {
		try {
			return MyCollabApplication.getInstance().getSessionData();
		} catch (Exception e) {
			return null;
		}
	}

	public void transactionEnd() {
		long currentTime = new GregorianCalendar().getTimeInMillis();
		if (currentTime - lastAccessTime > UPDATE_TIME_DURATION) {
			try {
				if (userPreference != null) {
					UserPreference pref = userPreference;
					UserPreferenceService prefService = AppContext
							.getSpringBean(UserPreferenceService.class);
					pref.setLastaccessedtime(new GregorianCalendar().getTime());
					prefService.updateWithSession(pref,
							AppContext.getUsername());

					lastAccessTime = currentTime;
					log.debug("Update last access time of user "
							+ AppContext.getUsername());
				}

			} catch (Exception e) {
				log.error("There is error when try to update user preference",
						e);
			}
		}
	}

	public static void updateLastModuleVisit(String moduleName) {
		try {
			UserPreference pref = getInstance().userPreference;
			UserPreferenceService prefService = AppContext
					.getSpringBean(UserPreferenceService.class);
			pref.setLastmodulevisit(moduleName);
			prefService.updateWithSession(pref, AppContext.getUsername());
		} catch (Exception e) {
			log.error(
					"There is error when try to update user preference for last module visit",
					e);
		}
	}

	public static void setSession(SimpleUser userSession,
			UserPreference userPreference, SimpleBillingAccount billingAccount) {
		getInstance().session = userSession;
		getInstance().userPreference = userPreference;
		getInstance().billingAccount = billingAccount;

		TimeZone timezone = getTimezoneInContext();
		getInstance().variables.put(USER_TIMEZONE, timezone);
	}

	public static SimpleUser getSession() {
		return getInstance().session;
	}

	public static Integer getAccountId() {
		return getInstance().session.getAccountid();
	}

	public static String getUsername() {
		return getInstance().session.getUsername();
	}

	public static UserPreference getUserPreference() {
		return getInstance().userPreference;
	}

	public static SimpleBillingAccount getBillingAccount() {
		return getInstance().billingAccount;
	}

	public static Application getApplication() {
		return MyCollabApplication.getInstance();
	}

	public static <T> T getSpringBean(Class<T> requiredType) {

		return springContext.getBean(requiredType);
	}

	public static ApplicationContext getSpringContext() {
		return springContext;
	}

	public static boolean isAdmin() {
		Boolean isAdmin = getInstance().session.getIsadmin();
		if (isAdmin == null) {
			return Boolean.FALSE;
		} else {
			return isAdmin;
		}
	}

	public static boolean canRead(String permissionItem) {
		if (isAdmin()) {
			return true;
		}

		PermissionMap permissionMap = getInstance().session.getPermissionMaps();
		if (permissionMap == null) {
			return false;
		} else {
			return permissionMap.canRead(permissionItem);
		}
	}

	public static boolean canWrite(String permissionItem) {
		if (isAdmin()) {
			return true;
		}
		PermissionMap permissionMap = getInstance().session.getPermissionMaps();
		if (permissionMap == null) {
			return false;
		} else {
			return permissionMap.canWrite(permissionItem);
		}
	}

	public static boolean canAccess(String permissionItem) {
		if (isAdmin()) {
			return true;
		}
		PermissionMap permissionMap = getInstance().session.getPermissionMaps();
		if (permissionMap == null) {
			return false;
		} else {
			return permissionMap.canAccess(permissionItem);
		}
	}

	public static void putVariable(String key, Object value) {
		if (getInstance() != null) {
			getInstance().variables.put(key, value);
		}
	}

	public static Object getVariable(String key) {
		if (getInstance() != null) {
			return getInstance().variables.get(key);
		}
		return null;
	}

	public static void removeVariable(String key) {
		if (getInstance() != null) {
			getInstance().variables.remove(key);
		}
	}

	public static void clearSession() {
		ViewManager.clearResources();
		PresenterResolver.clearResources();
		EventBus.getInstance().clear();
		ControllerRegistry.getInstance().clearRegistries();
		clearAllVariables();
		if (getInstance() != null) {
			getInstance().session = null;
			getInstance().userPreference = null;
		}
	}

	static void clearAllVariables() {
		if (getInstance() != null) {
			getInstance().variables.clear();
		}

	}

	private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(
			"MM/dd/yyyy hh:mm a");
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"MM/dd/yyyy");
	private static SimpleDateFormat df = new SimpleDateFormat(
			"EEE MMM dd, hh:mm aa");

	private static TimeZone getTimezoneInContext() {
		SimpleUser session = getInstance().session;
		TimeZone timezone = null;
		if (session == null) {
			timezone = TimeZone.getDefault();
		} else {
			if (session.getTimezone() == null) {
				timezone = TimeZone.getDefault();
			} else {
				timezone = TimezoneMapper.getTimezone(session.getTimezone())
						.getTimezone();
			}
		}
		return timezone;
	}

	public static String formatDateTime(Date date) {
		if (date == null) {
			return "";
		}

		TimeZone timezone = (TimeZone) getVariable(USER_TIMEZONE);
		if (timezone != null) {
			simpleDateTimeFormat.setTimeZone(timezone);
		}
		return simpleDateTimeFormat.format(date);
	}

	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}

		TimeZone timezone = (TimeZone) getVariable(USER_TIMEZONE);
		if (timezone != null) {
			simpleDateTimeFormat.setTimeZone(timezone);
		}

		return simpleDateFormat.format(date);
	}

	public static Date convertDate(String dateVal) {
		try {
			return simpleDateFormat.parse(dateVal);
		} catch (ParseException e) {
			return new GregorianCalendar().getTime();
		}
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

	public static void addFragment(String fragement, String windowTitle) {
		MainWindowContainer mainWindow = (MainWindowContainer) getApplication()
				.getMainWindow();
		mainWindow.addFragement(fragement);
		mainWindow.setCaption(StringUtils.subString(windowTitle, 150)
				+ " [MyCollab]");
	}
}

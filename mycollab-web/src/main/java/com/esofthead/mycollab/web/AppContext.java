package com.esofthead.mycollab.web;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.common.domain.UserPreference;
import com.esofthead.mycollab.common.service.UserPreferenceService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.utils.StringUtils;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext.TransactionListener;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

public class AppContext implements TransactionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private static int UPDATE_TIME_DURATION = 300000;
	private static Logger log = LoggerFactory.getLogger(AppContext.class);
	private static ThreadLocal<AppContext> instance = new ThreadLocal<AppContext>();

	private final Application app;
	private final Map<String, Object> variables = new HashMap<String, Object>();

	private SimpleUser session;
	private UserPreference userPreference;

	private long lastAccessTime = 0;
	private static org.springframework.web.context.WebApplicationContext springContext;

	public AppContext(Application application) {
		this.app = application;
		if (springContext == null) {
			WebApplicationContext context = (WebApplicationContext) application
					.getContext();
			springContext = WebApplicationContextUtils
					.getRequiredWebApplicationContext(context.getHttpSession()
							.getServletContext());
		}

		// It's usable from now on in the current request
		instance.set(this);
	}

	public AppContext getInstance() {
		return instance.get();
	}

	@Override
	public void transactionStart(Application application, Object transactionData) {
		// Set this data instance of this application
		// as the one active in the current thread.
		if (this.app == application) {
			instance.set(this);
		}
	}

	@Override
	public void transactionEnd(Application application, Object transactionData) {
		// log.debug("Transaction end: " + transactionData);

		long currentTime = new GregorianCalendar().getTimeInMillis();
		if (currentTime - lastAccessTime > UPDATE_TIME_DURATION) {
			try {
				if (instance.get() != null
						&& instance.get().userPreference != null) {
					UserPreference pref = instance.get().userPreference;
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

		// Clear the reference to avoid potential problems
		if (this.app == application) {
			instance.set(null);
		}
	}

	public static void updateLastModuleVisit(String moduleName) {
		try {
			UserPreference pref = instance.get().userPreference;
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
			UserPreference userPreference) {
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

		return springContext.getBean(requiredType);
	}

	public static ApplicationContext getSpringContext() {
		return springContext;
	}

	public static boolean isAdmin() {
		Boolean isAdmin = instance.get().session.getIsadmin();
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

		PermissionMap permissionMap = instance.get().session
				.getPermissionMaps();
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
		PermissionMap permissionMap = instance.get().session
				.getPermissionMaps();
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
		PermissionMap permissionMap = instance.get().session
				.getPermissionMaps();
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

	public static void removeVariable(String key) {
		if (instance.get() != null) {
			instance.get().variables.remove(key);
		}
	}

	public static void clearSession() {
		ViewManager.clearResources();
		PresenterResolver.clearResources();
		EventBus.getInstance().clear();
		ControllerRegistry.getInstance().clearRegistries();
		clearAllVariables();
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
	private static SimpleDateFormat df = new SimpleDateFormat(
			"EEE MMM dd, hh:mm aa");

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

	@Deprecated
	public static void addFragment(String fragement) {
		MainWindowContainer mainWindow = (MainWindowContainer) getApplication()
				.getMainWindow();
		mainWindow.addFragement(fragement);
	}

	public static void addFragment(String fragement, String windowTitle) {
		MainWindowContainer mainWindow = (MainWindowContainer) getApplication()
				.getMainWindow();
		mainWindow.addFragement(fragement);
		mainWindow.setCaption(StringUtils.subString(windowTitle, 150)
				+ " [MyCollab]");
	}
	
	
	//LOCALIZATION
	private static IMessageConveyor mc = new MessageConveyor(Locale.US);
	public static String getMessage(Enum key) {
		return mc.getMessage(key);
	}
}

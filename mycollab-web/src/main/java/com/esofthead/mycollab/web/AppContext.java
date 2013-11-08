/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.web;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.common.localization.WebExceptionI18nEnum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.GroupIdProvider;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.events.SessionEvent;
import com.esofthead.mycollab.events.SessionEvent.UserProfileChangeEvent;
import com.esofthead.mycollab.module.billing.SubDomainNotExistException;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.UserPreference;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.module.user.service.UserPreferenceService;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.Application;

public class AppContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int UPDATE_TIME_DURATION = 300000;
	private static Logger log = LoggerFactory.getLogger(AppContext.class);

	public static String USER_TIMEZONE = "USER_TIMEZONE";

	private SimpleUser session;
	private UserPreference userPreference;
	private SimpleBillingAccount billingAccount;

	private long lastAccessTime = 0;

	private String subdomain;
	private Integer accountId = null;

	public AppContext(MyCollabApplication application) {

		GroupIdProvider.registerAccountIdProvider(new GroupIdProvider() {

			@Override
			public Integer getGroupId() {
				return AppContext.getAccountId();
			}
		});
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
					UserPreferenceService prefService = ApplicationContextUtil
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

	public void updateLastModuleVisit(String moduleName) {
		try {
			UserPreference pref = getInstance().userPreference;
			UserPreferenceService prefService = ApplicationContextUtil
					.getSpringBean(UserPreferenceService.class);
			pref.setLastmodulevisit(moduleName);
			prefService.updateWithSession(pref, AppContext.getUsername());
		} catch (Exception e) {
			log.error(
					"There is error when try to update user preference for last module visit",
					e);
		}
	}

	public void setSession(SimpleUser userSession, UserPreference userPref,
			SimpleBillingAccount billingAc) {
		session = userSession;
		userPreference = userPref;
		billingAccount = billingAc;

		TimeZone timezone = getTimezoneInContext();
		MyCollabApplication.getInstance().variables
				.put(USER_TIMEZONE, timezone);
	}

	public static SimpleUser getSession() {
		return getInstance().session;
	}

	public void initDomain(String domain) {
		this.subdomain = domain;
		BillingAccountService billingService = ApplicationContextUtil
				.getSpringBean(BillingAccountService.class);
		BillingAccount account = billingService.getAccountByDomain(domain);

		if (account == null) {
			throw new SubDomainNotExistException(LocalizationHelper.getMessage(
					WebExceptionI18nEnum.SUB_DOMAIN_IS_NOT_EXISTED, domain));
		} else {
			log.debug("Get billing account {} of subdomain {}",
					BeanUtility.printBeanObj(account), domain);
			accountId = account.getId();
		}

		EventBus.getInstance()
				.addListener(
						new ApplicationEventListener<SessionEvent.UserProfileChangeEvent>() {
							private static final long serialVersionUID = 1L;

							@Override
							public Class<? extends ApplicationEvent> getEventType() {
								return SessionEvent.UserProfileChangeEvent.class;
							}

							@Override
							public void handle(UserProfileChangeEvent event) {
								if ("avatarid".equals(event.getFieldChange())) {
									session.setAvatarid((String) event
											.getData());
								}
							}
						});
	}

	public static Integer getAccountId() {
		try {
			return getInstance().accountId;
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getSubDomain() {
		return getInstance().subdomain;
	}

	private String siteUrl = null;

	public static String getSiteUrl() {
		if (getInstance().siteUrl == null) {
			getInstance().siteUrl = SiteConfiguration
					.getSiteUrl(getInstance().subdomain);
		}

		return getInstance().siteUrl;
	}

	public static String getUsername() {
		try {
			return getInstance().session.getUsername();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getUserAvatarId() {
		return getInstance().session.getAvatarid();
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

	public static boolean isAdmin() {
		Boolean isAdmin = getInstance().session.getIsAccountOwner();
		if (isAdmin == null) {
			return Boolean.FALSE;
		} else {
			return isAdmin;
		}
	}

	public static boolean canBeYes(String permissionItem) {
		if (isAdmin()) {
			return true;
		}

		PermissionMap permissionMap = getInstance().session.getPermissionMaps();
		if (permissionMap == null) {
			return false;
		} else {
			return permissionMap.canBeYes(permissionItem);
		}
	}

	public static boolean canBeFalse(String permissionItem) {
		if (isAdmin()) {
			return true;
		}

		PermissionMap permissionMap = getInstance().session.getPermissionMaps();
		if (permissionMap == null) {
			return false;
		} else {
			return permissionMap.canBeFalse(permissionItem);
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

	public static PermissionMap getPermissionMap() {
		return getInstance().session.getPermissionMaps();
	}

	public static void putVariable(String key, Object value) {
		MyCollabApplication.getInstance().variables.put(key, value);
	}

	public static Object getVariable(String key) {
		return MyCollabApplication.getInstance().variables.get(key);
	}

	public static void removeVariable(String key) {
		if (getInstance() != null) {
			MyCollabApplication.getInstance().variables.remove(key);
		}
	}

	public static void clearSession() {
		if (getInstance() != null) {
			MyCollabApplication.getInstance().variables.clear();
			getInstance().session = null;
			getInstance().userPreference = null;
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
		return DateTimeUtils.formatDate(date,
				(TimeZone) getVariable(USER_TIMEZONE));
	}

	public static String formatDate(Date date, String textIfDateIsNull) {
		if (date == null) {
			return textIfDateIsNull;
		} else {
			return formatDate(date);
		}
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
		log.debug("Add fragement: " + fragement + " to " + mainWindow);
		mainWindow.setCaption(StringUtils.subString(windowTitle, 150)
				+ " [MyCollab]");
	}
}

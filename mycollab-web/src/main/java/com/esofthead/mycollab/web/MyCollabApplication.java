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

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.PasswordEncryptHelper;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.shell.view.FragmentNavigator;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.shell.view.NoSubDomainExistedWindow;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.annotations.Theme;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Theme("mycollab")
public class MyCollabApplication extends UI {

	private static final long serialVersionUID = 1L;

	private static final String CURRENT_APP = "currentApp";

	private static Logger log = LoggerFactory
			.getLogger(MyCollabApplication.class);

	// private static DatabaseReader reader = null;

	/**
	 * Context of current logged in user
	 */
	private AppContext currentContext;

	private String initialSubDomain = "1";
	private String initialUrl = "";

	public static final String NAME_COOKIE = "mycollab";

	// static {
	// try {
	// InputStream geoStream = MyCollabApplication.class.getClassLoader()
	// .getResourceAsStream("GeoLite2-City.mmdb");
	// reader = new Builder(geoStream).fileMode(FileMode.MEMORY).build();
	// } catch (Exception e) {
	// log.error("Can not read geo database file", e);
	// }
	// }

	public static MyCollabApplication getInstance() {
		return (MyCollabApplication) VaadinSession.getCurrent().getAttribute(
				CURRENT_APP);
	}

	@Override
	protected void init(VaadinRequest request) {
		log.debug("Init mycollab application {}", this.toString());
		log.debug("Register default error handler");
		VaadinSession.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
				Throwable e = event.getThrowable();
				UserInvalidInputException invalidException = (UserInvalidInputException) getUserInvalidException(e);
				if (invalidException != null) {
					NotificationUtil.showWarningNotification(LocalizationHelper
							.getMessage(
									GenericI18Enum.ERROR_USER_INPUT_MESSAGE,
									invalidException.getMessage()));
				} else {
					NotificationUtil.showErrorNotification(LocalizationHelper
							.getMessage(GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE));
				}
				log.error("Error", e);
			}
		});

		initialUrl = this.getPage().getUriFragment();
		VaadinSession.getCurrent().setAttribute(CURRENT_APP, this);
		currentContext = new AppContext(this);
		postSetupApp(request);
		try {
			currentContext.initDomain(initialSubDomain);
		} catch (Exception e) {
			this.setContent(new NoSubDomainExistedWindow(initialSubDomain));
			return;
		}
		this.setContent(new MainWindowContainer());

		getPage().addUriFragmentChangedListener(
				new UriFragmentChangedListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void uriFragmentChanged(UriFragmentChangedEvent event) {
						enter(event.getUriFragment());

					}
				});
	}

	private void enter(String uriFragement) {
		FragmentNavigator.navigateByFragement(uriFragement);
	}

	private void postSetupApp(VaadinRequest request) {
		VaadinServletRequest servletRequest = (VaadinServletRequest) request;
		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			initialSubDomain = servletRequest.getServerName().split("\\.")[0];
		} else {
			initialSubDomain = servletRequest.getServerName();
		}
	}

	public AppContext getSessionData() {
		return currentContext;
	}

	// @Override
	// public void onRequestEnd(HttpServletRequest request,
	// HttpServletResponse response) {
	// if (currentContext != null) {
	// currentContext.transactionEnd();
	// }
	//
	// if (currentThrowable != null) {
	// String errorMsg =
	// "An uncaught exception occurred with username %s, in account %d, useragent %s, ip %s and country code %s ";
	// try {
	// String username = AppContext.getUsername();
	// Integer accountId = AppContext.getAccountId();
	// AbstractWebApplicationContext webContext =
	// (AbstractWebApplicationContext) this
	// .getContext();
	//
	// StringBuffer userinfo = new StringBuffer("Request Headers: ")
	// .append(" - ");
	//
	// Enumeration<String> headerNames = request.getHeaderNames();
	// while (headerNames.hasMoreElements()) {
	// String headerName = headerNames.nextElement();
	// String headerVal = request.getHeader(headerName);
	// userinfo.append(headerName).append("=").append(headerVal)
	// .append(", ");
	// }
	//
	// String ipaddress = webContext.getBrowser().getAddress();
	// String countryCode = "<no defined>";
	// InetAddress address = InetAddress.getByName(ipaddress);
	// if (address != null && reader != null) {
	// try {
	// countryCode = reader.country(address).getCountry()
	// .getName();
	// } catch (Exception e2) {
	// log.error("Can not read country code", e2);
	// }
	// }
	//
	// errorMsg = String.format(errorMsg, username, accountId,
	// userinfo.toString(), ipaddress, countryCode);
	//
	// } catch (Exception e1) {
	// log.error("Error while generating issue", e1);
	// errorMsg = "An uncaught exception occurred ";
	// }
	//
	// log.error(errorMsg, currentThrowable);
	// currentThrowable = null;
	// }
	//
	// threadLocal.remove();
	// }

	public String getInitialUrl() {
		return initialUrl;
	}

	@Override
	public void close() {
		super.close();
		log.debug("Application is closed. Clean all resources");
		AppContext.clearSession();
		currentContext = null;
		VaadinSession.getCurrent().close();
	}

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
		VaadinSession.getCurrent().setAttribute(key, null);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static Object getVariable(String key) {
		return VaadinSession.getCurrent().getAttribute(key);
	}

	public void rememberPassword(String username, String password) {

		Cookie cookie = getCookieByName(MyCollabApplication.NAME_COOKIE);
		if (cookie == null) {
			cookie = new Cookie(MyCollabApplication.NAME_COOKIE, username + "$"
					+ PasswordEncryptHelper.encyptText(password));
		} else {
			cookie.setValue(username + "$"
					+ PasswordEncryptHelper.encyptText(password));
		}
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 7);
		VaadinService.getCurrentResponse().addCookie(cookie);
	}

	public void unsetRememberPassword() {
		Cookie cookie = getCookieByName(MyCollabApplication.NAME_COOKIE);

		if (cookie != null) {
			cookie.setValue("");
			VaadinService.getCurrentResponse().addCookie(cookie);
		}
	}

	public Cookie getCookieByName(String name) {
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();

		// Iterate to find cookie by its name
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}

		return null;
	}

	private static Throwable getUserInvalidException(Throwable e) {
		if (e instanceof UserInvalidInputException) {
			return e;
		} else if (e.getCause() != null) {
			return getUserInvalidException(e.getCause());
		} else {
			return null;
		}
	}
}

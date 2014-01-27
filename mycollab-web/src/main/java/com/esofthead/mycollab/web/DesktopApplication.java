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

import static com.esofthead.mycollab.vaadin.MyCollabSession.CURRENT_APP;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.PasswordEncryptHelper;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.SecurityException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.billing.SubDomainNotExistException;
import com.esofthead.mycollab.shell.view.FragmentNavigator;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.shell.view.NoSubDomainExistedWindow;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.MyCollabSession;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Theme("mycollab")
@Widgetset("com.esofthead.mycollab.widgetset.MyCollabWidgetSet")
@Push(PushMode.MANUAL)
public class DesktopApplication extends UI {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(DesktopApplication.class);

	/**
	 * Context of current logged in user
	 */
	private AppContext currentContext;

	private String initialSubDomain = "1";
	private String initialUrl = "";

	public static final String NAME_COOKIE = "mycollab";

	public static DesktopApplication getInstance() {
		return (DesktopApplication) MyCollabSession.getVariable(CURRENT_APP);
	}

	@Override
	protected void init(VaadinRequest request) {
		log.debug("Init mycollab application {} associate with session {}",
				this.toString(), VaadinSession.getCurrent());
		log.debug("Register default error handler");

		VaadinSession.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
				Throwable e = event.getThrowable();
				UserInvalidInputException invalidException = (UserInvalidInputException) getExceptionType(
						e, UserInvalidInputException.class);
				if (invalidException != null) {
					NotificationUtil.showWarningNotification(LocalizationHelper
							.getMessage(
									GenericI18Enum.ERROR_USER_INPUT_MESSAGE,
									invalidException.getMessage()));
				} else {
					SecurityException securityException = (SecurityException) getExceptionType(
							e, SecurityException.class);
					if (securityException != null) {
						NotificationUtil.showMessagePermissionAlert();
					} else {
						NotificationUtil.showErrorNotification(LocalizationHelper
								.getMessage(GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE));
						log.error("Error", e);
					}
				}

			}
		});

		initialUrl = this.getPage().getUriFragment();
		MyCollabSession.putVariable(CURRENT_APP, this);
		currentContext = new AppContext();
		postSetupApp(request);
		try {
			currentContext.initDomain(initialSubDomain);
		} catch (SubDomainNotExistException e) {
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

	public String getInitialUrl() {
		return initialUrl;
	}

	@Override
	public void close() {
		log.debug("Application is closed. Clean all resources");
		clearSession();
		currentContext = null;
		VaadinSession.getCurrent().close();
		super.close();
	}

	public void clearSession() {
		if (AppContext.getInstance() != null) {
			AppContext.getInstance().setSession(null, null, null);
			initialUrl = "";
		}
	}

	public void rememberPassword(String username, String password) {

		Cookie cookie = getCookieByName(DesktopApplication.NAME_COOKIE);
		String storeVal = username + "$"
				+ PasswordEncryptHelper.encyptText(password);
		if (cookie == null) {
			cookie = new Cookie(DesktopApplication.NAME_COOKIE, storeVal);
		} else {
			cookie.setValue(storeVal);
		}
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 7);

		Page.getCurrent()
				.getJavaScript()
				.execute(
						String.format(
								"document.cookie = '%s=%s; expires=%s;';",
								"mycollab", storeVal, 60 * 60 * 24 * 7 + ""));
	}

	public void unsetRememberPassword() {
		Cookie cookie = getCookieByName(DesktopApplication.NAME_COOKIE);

		if (cookie != null) {
			cookie.setValue("");
			cookie.setMaxAge(0);
			Page.getCurrent()
					.getJavaScript()
					.execute(
							String.format(
									"document.cookie = '%s=%s; expires=%s;';",
									"mycollab", "", "0"));
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

	private static Throwable getExceptionType(Throwable e,
			Class<? extends Throwable> exceptionType) {
		if (exceptionType.isAssignableFrom(e.getClass())) {
			return e;
		} else if (e.getCause() != null) {
			return getExceptionType(e.getCause(), exceptionType);
		} else {
			return null;
		}
	}
}

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

import java.io.InputStream;

import org.infinispan.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.shell.view.NoSubDomainExistedWindow;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.maxmind.db.Reader.FileMode;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.DatabaseReader.Builder;
import com.vaadin.annotations.Theme;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.ui.UI;

/**
 * 
 * @author haiphucnguyen
 * 
 */
@Theme("mycollab")
public class MyCollabApplication extends UI {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(MyCollabApplication.class);

	private static DatabaseReader reader = null;

	private static ThreadLocal<MyCollabApplication> threadLocal = new ThreadLocal<MyCollabApplication>();

	/**
	 * Context of current logged in user
	 */
	private AppContext currentContext;

	private String initialSubDomain = "1";

	/**
	 * Cache to keep all variables in user session
	 */
	BasicCache<String, Object> variables;

	static {
		try {
			InputStream geoStream = MyCollabApplication.class.getClassLoader()
					.getResourceAsStream("GeoLite2-City.mmdb");
			reader = new Builder(geoStream).fileMode(FileMode.MEMORY).build();
		} catch (Exception e) {
			log.error("Can not read geo database file", e);
		}
	}

	// @return the current application instance
	public static MyCollabApplication getInstance() {
		return threadLocal.get();
	}

	@Override
	protected void init(VaadinRequest request) {
		log.debug("Init mycollab application {}", this.toString());
		setInstance(this);
		variables = LocalCacheManager.getCache(this.toString());
		currentContext = new AppContext(this);
		postSetupApp(request);
		try {
			currentContext.initDomain(initialSubDomain);
		} catch (Exception e) {
			this.setContent(new NoSubDomainExistedWindow(initialSubDomain));
			return;
		}
		this.setContent(new MainWindowContainer());

		log.debug("Register default error handler");
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
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

	// Set the current application instance
	public static void setInstance(MyCollabApplication application) {
		threadLocal.set(application);
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

	@Override
	public void close() {
		super.close();
		log.debug("Application is closed. Clean all resources");
		AppContext.clearSession();
		variables.clear();
		currentContext = null;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public static void putVariable(String key, Object value) {
		getInstance().variables.put(key, value);
	}

	/**
	 * 
	 * @param key
	 */
	public static void removeVariable(String key) {
		getInstance().variables.remove(key);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static Object getVariable(String key) {
		return getInstance().variables.get(key);
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

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

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.infinispan.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.billing.SubDomainNotExistException;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.shell.view.NoSubDomainExistedWindow;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.maxmind.db.Reader.FileMode;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.DatabaseReader.Builder;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractWebApplicationContext;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window.Notification;

/**
 * 
 * @author haiphucnguyen
 * 
 */
public class MyCollabApplication extends Application implements
		HttpServletRequestListener {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(MyCollabApplication.class);

	private static DatabaseReader reader = null;

	private static ThreadLocal<MyCollabApplication> threadLocal = new ThreadLocal<MyCollabApplication>();

	/**
	 * Context of current logged in user
	 */
	private AppContext currentContext;

	private String initialUrl = "";
	private String initialSubDomain = "1";
	private boolean isInitializeApp = false;
	private Throwable currentThrowable;

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
	public void init() {
		log.debug("Init mycollab application {}", this.toString());
		setInstance(this);
		variables = LocalCacheManager.getCache(this.toString());
		isInitializeApp = true;
		setTheme("mycollab");
		currentContext = new AppContext(this);
		try {
			currentContext.initDomain(initialSubDomain);
		} catch (Exception e) {
			this.setMainWindow(new NoSubDomainExistedWindow(initialSubDomain));
			currentThrowable = e;
			return;
		}
		this.setMainWindow(new MainWindowContainer());
	}

	public AppContext getSessionData() {
		return currentContext;
	}

	// Set the current application instance
	public static void setInstance(MyCollabApplication application) {
		threadLocal.set(application);
	}

	@Override
	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		MyCollabApplication.setInstance(this);

		if (!isInitializeApp) {
			if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
				initialSubDomain = request.getServerName().split("\\.")[0];
			} else {
				initialSubDomain = request.getServerName();
			}

		}

		String pathInfo = request.getPathInfo();
		if ("".equals(pathInfo) || ("/").equals(pathInfo)) {
			if (currentContext != null) {
				String urlParam = request.getParameter("url");
				if (urlParam != null && !urlParam.equals("")) {
					if (urlParam.startsWith("/")) {
						urlParam = urlParam.substring(1);
					}
					try {
						String encodeRedirectURL = response
								.encodeRedirectURL(request.getContextPath());
						log.debug("Forward to URL: " + encodeRedirectURL);
						initialUrl = urlParam;
						response.sendRedirect(encodeRedirectURL);
					} catch (IOException e) {
						log.error("Dispatch url error: " + initialUrl, e);
					}
				}
			} else {
				try {
					initialUrl = request.getParameter("url");
					response.sendRedirect(request.getContextPath() + "/");
				} catch (IOException e) {
					log.error("Dispatch url error: " + initialUrl, e);
				}
			}
		}
	}

	@Override
	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
		if (currentContext != null) {
			currentContext.transactionEnd();
		}

		if (currentThrowable != null) {
			String errorMsg = "An uncaught exception occurred with username %s, in account %d, useragent %s, ip %s and country code %s ";
			try {
				String username = AppContext.getUsername();
				Integer accountId = AppContext.getAccountId();
				AbstractWebApplicationContext webContext = (AbstractWebApplicationContext) this
						.getContext();

				StringBuffer userinfo = new StringBuffer("Request Headers: ")
						.append(" - ");

				Enumeration<String> headerNames = request.getHeaderNames();
				while (headerNames.hasMoreElements()) {
					String headerName = headerNames.nextElement();
					String headerVal = request.getHeader(headerName);
					userinfo.append(headerName).append("=").append(headerVal)
							.append(", ");
				}

				String ipaddress = webContext.getBrowser().getAddress();
				String countryCode = "<no defined>";
				InetAddress address = InetAddress.getByName(ipaddress);
				if (address != null && reader != null) {
					try {
						countryCode = reader.country(address).getCountry()
								.getName();
					} catch (Exception e2) {
						log.error("Can not read country code", e2);
					}
				}

				errorMsg = String.format(errorMsg, username, accountId,
						userinfo.toString(), ipaddress, countryCode);

			} catch (Exception e1) {
				log.error("Error while generating issue", e1);
				errorMsg = "An uncaught exception occurred ";
			}

			log.error(errorMsg, currentThrowable);
			currentThrowable = null;
		}

		threadLocal.remove();
	}

	@Override
	public void close() {
		super.close();
		log.debug("Application is closed. Clean all resources");
		AppContext.clearSession();
		variables.clear();
		currentContext = null;
	}

	@Override
	public void terminalError(com.vaadin.terminal.Terminal.ErrorEvent event) {
		Throwable e = event.getThrowable();
		UserInvalidInputException invalidException = (UserInvalidInputException) getUserInvalidException(e);
		if (invalidException != null) {
			NotificationUtil.showNotification(LocalizationHelper.getMessage(
					GenericI18Enum.ERROR_USER_INPUT_MESSAGE,
					invalidException.getMessage()),
					Notification.TYPE_WARNING_MESSAGE);
			currentThrowable = (invalidException instanceof SubDomainNotExistException) ? e
					: null;
		} else {
			NotificationUtil
					.showNotification(
							LocalizationHelper
									.getMessage(GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE),
							Notification.TYPE_ERROR_MESSAGE);
			currentThrowable = e;
		}

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

	public String getInitialUrl() {
		return initialUrl;
	}

	public void setInitialUrl(String initialUrl) {
		this.initialUrl = initialUrl;
	}
}

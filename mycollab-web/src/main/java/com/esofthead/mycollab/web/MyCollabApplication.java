package com.esofthead.mycollab.web;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.shell.view.NoSubDomainExistedWindow;
import com.maxmind.geoip2.DatabaseReader;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractWebApplicationContext;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window.Notification;

public class MyCollabApplication extends Application implements
		HttpServletRequestListener {

	private static final long serialVersionUID = 1L;

	private String initialUrl = "";
	private String initialSubDomain = "1";
	private boolean isInitializeApp = false;
	private Throwable currentThrowable;

	private static Logger log = LoggerFactory
			.getLogger(MyCollabApplication.class);

	private static DatabaseReader reader = null;

	private static ThreadLocal<MyCollabApplication> threadLocal = new ThreadLocal<MyCollabApplication>();

	private AppContext sessionData;

	static {
		try {
			URL url = MyCollabApplication.class.getClassLoader().getResource(
					"GeoLite2-City.mmdb");
			reader = new DatabaseReader(new File(url.toURI()));
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
		isInitializeApp = true;
		setTheme("mycollab");
		sessionData = new AppContext(this);
		try {
			sessionData.initDomain(initialSubDomain);
		} catch (Exception e) {
			this.setMainWindow(new NoSubDomainExistedWindow(initialSubDomain));
			currentThrowable = e;
			return;
		}
		this.setMainWindow(new MainWindowContainer());
		setInstance(this);
	}

	public AppContext getSessionData() {
		return sessionData;
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
			if (sessionData != null) {
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
		if (sessionData != null) {
			sessionData.transactionEnd();
		}

		if (currentThrowable != null) {
			String errorMsg = "An uncaught exception occurred with username %s, in account %d, useragent %s, ip %s and country code %s ";
			try {
				String username = AppContext.getUsername();
				int accountId = AppContext.getAccountId();
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
		sessionData = null;
	}

	@Override
	public void terminalError(com.vaadin.terminal.Terminal.ErrorEvent event) {
		Throwable e = event.getThrowable();
		UserInvalidInputException invalidException = (UserInvalidInputException) getUserInvalidException(e);
		if (invalidException != null) {
			getMainWindow().showNotification(
					LocalizationHelper.getMessage(
							GenericI18Enum.ERROR_USER_INPUT_MESSAGE,
							invalidException.getMessage()),
					Notification.TYPE_WARNING_MESSAGE);
			currentThrowable = null;
		} else {
			getMainWindow()
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

package com.esofthead.mycollab.web;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.DeploymentMode;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.shell.view.NoSubDomainExistedWindow;
import com.maxmind.geoip2.DatabaseReader;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext.TransactionListener;
import com.vaadin.terminal.gwt.server.AbstractWebApplicationContext;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window.Notification;

public class MyCollabApplication extends Application implements
		HttpServletRequestListener {

	private static final long serialVersionUID = 1L;

	private String initialUrl = "";
	private String initialSubDomain = "1";
	private boolean isInitializeApp = false;

	private static Logger log = LoggerFactory
			.getLogger(MyCollabApplication.class);

	private static DatabaseReader reader = null;

	static {
		try {
			reader = new DatabaseReader(new File("GeoLite2-City.mmdb"));
		} catch (Exception e) {
			log.error("Can not read geo database file", e);
		}
	}

	private static ThreadLocal<MyCollabApplication> threadLocal = new ThreadLocal<MyCollabApplication>();

	private AppContext sessionData;

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
			log.error("System can not find subdomain " + initialSubDomain, e);
			this.setMainWindow(new NoSubDomainExistedWindow(initialSubDomain));
			return;
		}
		this.setMainWindow(new MainWindowContainer());
		setInstance(this);

		getContext().addTransactionListener(new TransactionListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void transactionStart(Application application,
					Object transactionData) {
				// log.debug("Trx start");

			}

			@Override
			public void transactionEnd(Application application,
					Object transactionData) {
				// log.debug("Trx end");

			}
		});
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
		} else {
			getMainWindow()
					.showNotification(
							LocalizationHelper
									.getMessage(GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE),
							Notification.TYPE_ERROR_MESSAGE);
			String errorMsg = "An uncaught exception occurred with username %s, in account %d, useragent %s, ip %s and country code %s ";
			try {
				String username = AppContext.getUsername();
				int accountId = AppContext.getAccountId();
				AbstractWebApplicationContext webContext = (AbstractWebApplicationContext) this
						.getContext();
				String useragent = webContext.getBrowser()
						.getBrowserApplication();
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
						useragent, ipaddress, countryCode);

			} catch (Exception e1) {
				errorMsg = "An uncaught exception occurred ";
			}

			log.error(errorMsg, event.getThrowable());
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

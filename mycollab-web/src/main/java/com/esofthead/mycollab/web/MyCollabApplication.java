package com.esofthead.mycollab.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dontpush.server.AtmosphereDontPushHandler;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.shell.view.NoSubDomainExistedWindow;
import com.vaadin.Application;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

public class MyCollabApplication extends Application implements
		HttpServletRequestListener {

	private static final long serialVersionUID = 1L;

	private String initialUrl = "";
	private String initialSubDomain = "1";
	private boolean isInitializeApp = false;

	private static Logger log = LoggerFactory
			.getLogger(MyCollabApplication.class);

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
			boolean isSupportSubDomain = ApplicationProperties
					.getBoolean(ApplicationProperties.SUPPORT_ACCOUNT_SUBDOMAIN);
			if (isSupportSubDomain) {
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

			log.error("An uncaught exception occurred: ", event.getThrowable());
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

	@Override
	public Window getWindow(String name) {
		Window w = super.getWindow(name);
		if (w == null) {
			if (name.startsWith("org.vaadin")) {
				try {
					Class<?> forName = Class.forName(name);
					try {
						Window newInstance = (Window) forName.newInstance();
						w = newInstance;
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (w != null) {
				addWindow(w);
				w.open(new ExternalResource(w.getURL()));
			}
			AtmosphereDontPushHandler a;
			return w;
		}
		return w;
	}
}

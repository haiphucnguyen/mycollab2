package com.esofthead.mycollab.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.shell.view.FragmentNavigator;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window.Notification;

public class MyCollabApplication extends Application implements
		HttpServletRequestListener {

	private static final long serialVersionUID = 1L;

	private String initialUrl = "";

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
		setTheme("mycollab");
		sessionData = new AppContext(this);
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
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("") || pathInfo.equals("/")) {
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
		if (e instanceof UserInvalidInputException) {
			getMainWindow().showNotification(
					LocalizationHelper.getMessage(
							GenericI18Enum.ERROR_USER_INPUT_MESSAGE,
							e.getMessage()), Notification.TYPE_WARNING_MESSAGE);
		} else {
			getMainWindow()
					.showNotification(
							LocalizationHelper
									.getMessage(GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE),
							Notification.TYPE_ERROR_MESSAGE);

			log.error("An uncaught exception occurred: ", event.getThrowable());
		}

	}

	public String getInitialUrl() {
		return initialUrl;
	}

	public void setInitialUrl(String initialUrl) {
		this.initialUrl = initialUrl;
	}
}

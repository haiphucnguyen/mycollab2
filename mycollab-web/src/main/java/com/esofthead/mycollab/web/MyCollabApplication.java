package com.esofthead.mycollab.web;

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

	private AppContext sessionData;

	public MyCollabApplication() {
		super();
	}

	@Override
	public void init() {
		setTheme("mycollab");
		sessionData = new AppContext(this);
		this.setMainWindow(new MainWindowContainer());

		// Register it as a listener in the application context
		this.getContext().addTransactionListener(sessionData);
	}

	@Override
	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("") || pathInfo.equals("/")) {
			initialUrl = request.getParameter("url");
			if (sessionData != null) {
				initialUrl = request.getParameter("url");
				if (initialUrl != null && !initialUrl.equals("")) {
					if (initialUrl.startsWith("/")) {
						initialUrl = initialUrl.substring(1);
					}
					FragmentNavigator.navigateByFragement(initialUrl);
				}
			}
		}
	}

	@Override
	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
	}

	@Override
	public void close() {
		super.close();
		log.debug("Application is closed. Clean all resources");
		AppContext.clearSession();
	}

	@Override
	public void terminalError(com.vaadin.terminal.Terminal.ErrorEvent event) {
		Throwable e = event.getThrowable();
		if (e instanceof UserInvalidInputException) {
			getMainWindow().showNotification(
					AppContext.getMessage(
							GenericI18Enum.ERROR_USER_INPUT_MESSAGE,
							e.getMessage()), Notification.TYPE_WARNING_MESSAGE);
		} else {
			getMainWindow()
					.showNotification(
							AppContext
									.getMessage(GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE),
							Notification.TYPE_ERROR_MESSAGE);

			log.error("An uncaught exception occurred: ", event.getThrowable());
		}

	}

	public String getInitialUrl() {
		return initialUrl;
	}
}

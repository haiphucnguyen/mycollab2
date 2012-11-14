package com.esofthead.mycollab.web;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ui.View;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext.TransactionListener;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

public class AppContext implements TransactionListener, Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(AppContext.class);

	private Application app;

	private SimpleUser session;

	private static ThreadLocal<AppContext> instance = new ThreadLocal<AppContext>();

	public AppContext(Application application) {
		this.app = application;

		// It's usable from now on in the current request
		instance.set(this);
	}

	@Override
	public void transactionStart(Application application, Object transactionData) {
		// Set this data instance of this application
		// as the one active in the current thread.
		if (this.app == application) {
			instance.set(this);
		}
	}

	@Override
	public void transactionEnd(Application application, Object transactionData) {
		// Clear the reference to avoid potential problems
		if (this.app == application) {
			instance.set(null);
		}
	}

	public static void setSession(SimpleUser userSession) {
		instance.get().session = userSession;
	}

	public static SimpleUser getSession() {
		return instance.get().session;
	}

	public static Integer getAccountId() {
		return instance.get().session.getAccountid();
	}

	public static String getUsername() {
		return instance.get().session.getUsername();
	}

	public static Application getApplication() {
		return instance.get().app;
	}

	public static <T> T getSpringBean(Class<T> requiredType) {

		WebApplicationContext context = (WebApplicationContext) instance.get().app
				.getContext();

		org.springframework.web.context.WebApplicationContext springContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context.getHttpSession()
						.getServletContext());

		return springContext.getBean(requiredType);
	}

	public static <T extends View> T getView(Class<T> viewClass) {
		T view = getSpringBean(viewClass);
		((AbstractView)view).initLayout();
		log.debug("Create view class {}", viewClass.getName());
		return view;
	}
}

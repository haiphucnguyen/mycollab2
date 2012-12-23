package com.esofthead.mycollab.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.shell.MainWindowContainer;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.Application;

public class MyCollabApplication extends Application {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(MyCollabApplication.class);

	private ViewManager viewManager;

	public MyCollabApplication() {
		super();
	}

	@Override
	public void init() {
		setTheme("mycollab");
		this.setMainWindow(ViewManager.getView(MainWindowContainer.class));
		AppContext sessionData = new AppContext(this);

		// Register it as a listener in the application context
		this.getContext().addTransactionListener(sessionData);
	}

	@Override
	public void close() {
		super.close();
		log.debug("Application is closed. Clean all resources");
		AppContext.clearAllVariables();
		ViewManager.clearResources();
		PresenterResolver.clearResources();
	}
}

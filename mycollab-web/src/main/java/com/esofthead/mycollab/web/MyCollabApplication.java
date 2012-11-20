package com.esofthead.mycollab.web;

import com.esofthead.mycollab.shell.MainWindowContainer;
import com.vaadin.Application;

public class MyCollabApplication extends Application {

	private static final long serialVersionUID = 1L;

	public MyCollabApplication() {
		super();
	}

	@Override
	public void init() {
		setTheme("mycollab");
		this.setMainWindow(new MainWindowContainer());
		AppContext sessionData = new AppContext(this);

		// Register it as a listener in the application context
		this.getContext().addTransactionListener(sessionData);
	}

}

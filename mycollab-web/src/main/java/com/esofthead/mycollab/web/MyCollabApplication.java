package com.esofthead.mycollab.web;

import com.esofthead.mycollab.module.user.ui.LoginViewImpl;
import com.esofthead.mycollab.vaadin.mvp.LogoutEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.eventbus.EventBus;
import com.vaadin.Application;

public class MyCollabApplication extends Application {

	private static final long serialVersionUID = 1L;

	public MyCollabApplication() {
		super();
	}

	@Override
	public void init() {
		setTheme("chameleon-blue");
		this.setMainWindow(new MainWindowContainer());
		AppContext sessionData = new AppContext(this);

		// Register it as a listener in the application context
		this.getContext().addTransactionListener(sessionData);

//		PresenterRegistry presenterRegistry = AppData
//				.getSpringBean(PresenterRegistry.class);
//		presenterRegistry.registerPresenter();

		LoginViewImpl mainView = AppContext.getSpringBean(LoginViewImpl.class);

		EventBus eventBus = AppContext.getSpringBean(EventBus.class);
		System.out.println("Main view: " + mainView);
		getMainWindow().setContent(mainView.createMainLayout());

		eventBus.addListener(new ApplicationEventListener<LogoutEvent>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void handle(LogoutEvent event) {
				MyCollabApplication.this.close();
			}

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return LogoutEvent.class;
			}
		});
	}

}

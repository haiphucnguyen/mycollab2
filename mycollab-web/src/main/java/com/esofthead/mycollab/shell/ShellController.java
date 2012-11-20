package com.esofthead.mycollab.shell;

import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.shell.events.ShellEvent.GotoMainPage;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class ShellController {

	private ComponentContainer container;

	public ShellController(ComponentContainer container) {
		this.container = container;

		bind();
	}

	private void bind() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoMainPage>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoMainPage.class;
					}

					@Override
					public void handle(GotoMainPage event) {
						System.out.println("Go to main page");
						
						((Window)container).setContent(new MainViewImpl());
					}
				});
	}
}

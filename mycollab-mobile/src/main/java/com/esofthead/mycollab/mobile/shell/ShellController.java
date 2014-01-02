package com.esofthead.mycollab.mobile.shell;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent.GotoMainPage;
import com.esofthead.mycollab.vaadin.mvp.IController;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class ShellController implements IController {
	private static final long serialVersionUID = 1L;

	public ShellController() {
		bind();
	}

	private void bind() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoMainPage>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoMainPage.class;
					}

					@Override
					public void handle(GotoMainPage event) {
						// TODO Auto-generated method stub

					}

				});
	}
}

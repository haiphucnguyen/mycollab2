package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;

@SuppressWarnings("serial")
public class LogoutEvent extends ApplicationEvent {

	public LogoutEvent(Object source) {
		super(source);
	}

	@Override
	public String getDescription() {
		return "LogoutEvent";
	}

}

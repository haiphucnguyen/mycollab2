package com.esofthead.mycollab.shell.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

@SuppressWarnings("serial")
public class ShellEvent {
	
	public static class GotoMainPage extends ApplicationEvent {
		public GotoMainPage(Object source, Object data) {
			super(source, data);
		}
	}
}

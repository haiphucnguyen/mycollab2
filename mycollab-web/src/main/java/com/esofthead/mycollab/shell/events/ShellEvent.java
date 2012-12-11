package com.esofthead.mycollab.shell.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

@SuppressWarnings("serial")
public class ShellEvent {

	public static class GotoMainPage extends ApplicationEvent {
		public GotoMainPage(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class GotoProjectPage extends ApplicationEvent {
		public GotoProjectPage(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class GotoCrmPagePage extends ApplicationEvent {
		public GotoCrmPagePage(Object source, Object data) {
			super(source, data);
		}
	}

	public static class LogOut extends ApplicationEvent {
		public LogOut(Object source, Object data) {
			super(source, data);
		}
	}
}

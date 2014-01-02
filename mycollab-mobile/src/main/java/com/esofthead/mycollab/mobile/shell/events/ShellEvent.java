package com.esofthead.mycollab.mobile.shell.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class ShellEvent {

	public static class GotoMainPage extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoMainPage(Object source, Object data) {
			super(source, data);
		}
	}
}

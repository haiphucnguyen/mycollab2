package com.esofthead.mycollab.module.file.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

public class CloudDriveOAuthCallbackEvent {
	
	public static class GotoList extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoList(Object source, Object data) {
			super(source, data);
		}
	}
}

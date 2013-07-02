package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

public class FileEvent {
	public static class GotoDashboard extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoDashboard(Object source, Object data) {
            super(source, data);
        }
    }
}

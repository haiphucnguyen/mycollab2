package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

public class FileEvent {
	public static class GotoList extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoList(Object source, Object data) {
            super(source, data);
        }
    }
}

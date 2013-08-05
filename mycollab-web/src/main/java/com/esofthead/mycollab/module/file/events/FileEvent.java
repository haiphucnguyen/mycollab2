package com.esofthead.mycollab.module.file.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

public class FileEvent {
	public static class GotoList extends ApplicationEvent {

		public GotoList(Object source, Object data) {
			super(source, data);
		}
	}
}

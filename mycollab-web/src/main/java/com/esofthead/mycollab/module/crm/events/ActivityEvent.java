package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

@SuppressWarnings("serial")
public class ActivityEvent {
	
	public static class GotoCalendar extends ApplicationEvent {
		public GotoCalendar(Object source, Object data) {
			super(source, data);
		}
	}
}

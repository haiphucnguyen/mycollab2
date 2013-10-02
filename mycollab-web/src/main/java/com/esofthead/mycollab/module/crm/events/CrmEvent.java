package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

@SuppressWarnings("serial")
public class CrmEvent {
	
	public static class GotoHome extends ApplicationEvent {
		public GotoHome(Object source, Object data) {
			super(source, data);
		}
	}
}

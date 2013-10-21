package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

public class CrmSettingEvent {

	public static class GotoNotificationSetting extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoNotificationSetting(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoCustomViewSetting extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoCustomViewSetting(Object source, Object data) {
			super(source, data);
		}
	}
}

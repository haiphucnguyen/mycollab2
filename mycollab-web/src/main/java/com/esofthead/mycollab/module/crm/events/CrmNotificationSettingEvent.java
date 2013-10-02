package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

public class CrmNotificationSettingEvent {
	public static class GotoSetting extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoSetting(Object source, Object data) {
			super(source, data);
		}
	}
}

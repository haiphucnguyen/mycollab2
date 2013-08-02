package com.esofthead.mycollab.module.user.accountsettings.view.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

public class AccountBillingEvent {
	public static class GotoSummary extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoSummary(Object source, Object data) {
			super(source, data);
		}
	}
}

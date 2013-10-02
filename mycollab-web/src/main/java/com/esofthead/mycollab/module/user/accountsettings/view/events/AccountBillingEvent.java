package com.esofthead.mycollab.module.user.accountsettings.view.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

public class AccountBillingEvent {
	
	public static class CancelAccount extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public CancelAccount(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class GotoSummary extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoSummary(Object source, Object data) {
			super(source, data);
		}
	}
}

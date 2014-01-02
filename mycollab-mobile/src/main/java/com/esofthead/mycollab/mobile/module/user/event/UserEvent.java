package com.esofthead.mycollab.mobile.module.user.event;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class UserEvent {
	public static class PlainLogin extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public PlainLogin(Object source, Object data) {
			super(source, data);
		}
	}
}

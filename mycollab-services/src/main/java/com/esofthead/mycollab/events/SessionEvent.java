package com.esofthead.mycollab.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

public class SessionEvent {
	public static class UserAvatarChangeEvent extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public UserAvatarChangeEvent(Object source, Object data) {
			super(source, data);
		}
	}
}

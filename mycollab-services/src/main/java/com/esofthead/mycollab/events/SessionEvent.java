package com.esofthead.mycollab.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

public class SessionEvent {
	public static class UserProfileChangeEvent extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		private String fieldChange;

		public UserProfileChangeEvent(Object source, String fieldChange,
				Object data) {
			super(source, data);

			this.fieldChange = fieldChange;
		}

		public String getFieldChange() {
			return fieldChange;
		}
	}
}

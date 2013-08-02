package com.esofthead.mycollab.module.user.accountsettings.view.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

@SuppressWarnings("serial")
public class ProfileEvent {
	
	public static class GotoProfileView extends ApplicationEvent {
		public GotoProfileView(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class GotoProfileEdit extends ApplicationEvent {
		public GotoProfileEdit(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoUploadPhoto extends ApplicationEvent {

		public GotoUploadPhoto(Object source, Object data) {
			super(source, data);
		}
	}
}

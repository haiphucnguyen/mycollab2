package com.esofthead.mycollab.module.file.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

public class CloudDriveOAuthCallbackEvent {

	public static class ReceiveCloudDriveInfo extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public ReceiveCloudDriveInfo(Object source, Object data) {
			super(source, data);
		}
	}
}

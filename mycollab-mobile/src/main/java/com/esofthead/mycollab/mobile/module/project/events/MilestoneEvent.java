package com.esofthead.mycollab.mobile.module.project.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class MilestoneEvent {

	public static class GotoList extends ApplicationEvent {

		private static final long serialVersionUID = 5974983067244370237L;

		public GotoList(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoAdd extends ApplicationEvent {

		private static final long serialVersionUID = 4004012882662987445L;

		public GotoAdd(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoRead extends ApplicationEvent {

		private static final long serialVersionUID = -3386533751870834834L;

		public GotoRead(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoEdit extends ApplicationEvent {

		private static final long serialVersionUID = 4905749540330317402L;

		public GotoEdit(Object source, Object data) {
			super(source, data);
		}
	}
}

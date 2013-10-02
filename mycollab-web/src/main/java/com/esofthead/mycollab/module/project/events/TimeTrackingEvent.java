package com.esofthead.mycollab.module.project.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

public class TimeTrackingEvent {
	public static class GotoTimeTrackingView extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoTimeTrackingView(Object source, Object data) {
			super(source, data);
		}
	}

	public static class Search extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public Search(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoList extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoList(Object source, Object data) {
			super(source, data);
		}
	}
}

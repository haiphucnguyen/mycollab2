package com.esofthead.mycollab.mobile.module.project.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class MessageEvent {
	public static class GotoList extends ApplicationEvent {

		private static final long serialVersionUID = -834543150969461602L;

		public GotoList(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoRead extends ApplicationEvent {

		private static final long serialVersionUID = 134479710975293923L;

		public GotoRead(Object source, Object data) {
			super(source, data);
		}
	}
}

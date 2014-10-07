package com.esofthead.mycollab.mobile.module.project.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class BugEvent {

	public static class GotoList extends ApplicationEvent {

		private static final long serialVersionUID = 7158198579057329578L;

		public GotoList(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoAdd extends ApplicationEvent {

		private static final long serialVersionUID = -7175115053475159861L;

		public GotoAdd(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoRead extends ApplicationEvent {

		private static final long serialVersionUID = -8018516278364744326L;

		public GotoRead(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoEdit extends ApplicationEvent {

		private static final long serialVersionUID = -5689124567831046183L;

		public GotoEdit(Object source, Object data) {
			super(source, data);
		}
	}
}

package com.esofthead.mycollab.module.project.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

public class FollowingTicketEvent {
	public static class GotoMyFollowingItems extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoMyFollowingItems(Object source, Object data) {
			super(source, data);
		}
	}
}

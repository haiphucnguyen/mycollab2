package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

@SuppressWarnings("serial")
public class ActivityEvent {

	public static class GotoCalendar extends ApplicationEvent {
		public GotoCalendar(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class GotoTodoList extends ApplicationEvent {
		public GotoTodoList(Object source, Object data) {
			super(source, data);
		}
	}

	public static class TaskAdd extends ApplicationEvent {
		public TaskAdd(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class TaskRead extends ApplicationEvent {
		public TaskRead(Object source, Object data) {
			super(source, data);
		}
	}

	public static class MeetingAdd extends ApplicationEvent {
		public MeetingAdd(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class MeetingRead extends ApplicationEvent {
		public MeetingRead(Object source, Object data) {
			super(source, data);
		}
	}

	public static class CallAdd extends ApplicationEvent {
		public CallAdd(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class CallRead extends ApplicationEvent {
		public CallRead(Object source, Object data) {
			super(source, data);
		}
	}
}

package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

@SuppressWarnings("serial")
public class CaseEvent {
	
	public static class Save extends ApplicationEvent {
		public Save(Object source, Object data) {
			super(source, data);
		}
	}

	public static class Search extends ApplicationEvent {
		public Search(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoList extends ApplicationEvent {
		public GotoList(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class GotoAdd extends ApplicationEvent {
		public GotoAdd(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class GotoRead extends ApplicationEvent {
		public GotoRead(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class GotoEdit extends ApplicationEvent {
		public GotoEdit(Object source, Object data) {
			super(source, data);
		}
	}
}

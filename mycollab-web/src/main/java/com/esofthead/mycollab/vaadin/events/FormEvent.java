package com.esofthead.mycollab.vaadin.events;

public class FormEvent {
	public static class Save extends ApplicationEvent {
		public Save(Object source, Object data) {
			super(source, data);
		}
	}
	
	public static class Edit extends ApplicationEvent {
		public Edit(Object source, Object data) {
			super(source, data);
		}
	}

	public static class Cancel extends ApplicationEvent {
		public Cancel(Object source, Object data) {
			super(source, data);
		}
	}
}

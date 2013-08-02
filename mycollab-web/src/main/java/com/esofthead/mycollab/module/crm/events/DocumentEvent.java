package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

public class DocumentEvent {
	public static class GotoDashboard extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoDashboard(Object source, Object data) {
			super(source, data);
		}
	}

	public static class Search extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public Search(Object source, FileSearchCriteria data) {
			super(source, data);
		}
	}
}

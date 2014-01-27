package com.esofthead.mycollab.mobile.ui;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

public class TableClickEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private final String fieldName;

	public TableClickEvent(Object source, Object data, String fieldName) {
		super(source, data);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}
}

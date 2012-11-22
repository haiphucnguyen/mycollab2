package com.esofthead.mycollab.vaadin.events;

public interface PreviewFormHandlers<T> {
	void onEdit(T data);

	void onCancel();
}

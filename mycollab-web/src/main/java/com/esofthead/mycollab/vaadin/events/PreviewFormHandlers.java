package com.esofthead.mycollab.vaadin.events;

public interface PreviewFormHandlers<T> {
	void onEdit(T data);
	
	void onDelete(T data);
	
	void onClone(T data);

	void onCancel();
}

package com.esofthead.mycollab.vaadin.events;

public interface EditFormHandler<T> {
	void onSave(T bean);

	void onSaveAndNew(T bean);

	void onCancel();
}

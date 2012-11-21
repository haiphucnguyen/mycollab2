package com.esofthead.mycollab.vaadin.events;

public interface EditFormHandler {
	void onSave(FormEvent.Save event);

	void onCancel(FormEvent.Cancel event);
}

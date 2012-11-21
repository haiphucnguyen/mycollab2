package com.esofthead.mycollab.vaadin.events;

public interface FormHandler {
	void onSave(FormEvent.Save event);

	void onCancel(FormEvent.Cancel event);
}

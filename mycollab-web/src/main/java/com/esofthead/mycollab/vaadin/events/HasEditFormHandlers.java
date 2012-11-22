package com.esofthead.mycollab.vaadin.events;

public interface HasEditFormHandlers<T> {
	void addFormHandler(EditFormHandler<T> handler);
}

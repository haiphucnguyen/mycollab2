package com.esofthead.mycollab.vaadin.events;

import java.io.Serializable;

public interface HasEditFormHandlers<T> extends Serializable {
	void addFormHandler(EditFormHandler<T> handler);
}

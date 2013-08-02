package com.esofthead.mycollab.vaadin.events;

import java.io.Serializable;

public interface HasPagableHandlers extends Serializable{
	void addPagableHandler(PagableHandler handler);
}

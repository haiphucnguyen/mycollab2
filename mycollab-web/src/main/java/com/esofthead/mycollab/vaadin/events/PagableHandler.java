package com.esofthead.mycollab.vaadin.events;

import java.io.Serializable;

public interface PagableHandler extends Serializable{
	void move(int newPageNumber);
	
	void displayItemChange(int numOfItems);
}

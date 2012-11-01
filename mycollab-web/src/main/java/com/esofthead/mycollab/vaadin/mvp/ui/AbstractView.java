package com.esofthead.mycollab.vaadin.mvp.ui;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.vaadin.mvp.eventbus.EventBus;
import com.vaadin.ui.ComponentContainer;

@SuppressWarnings("serial")
public abstract class AbstractView implements View, Serializable {
	
	public static String SAVE_ACTION = "Save";

	public static String EDIT_ACTION = "Edit";

	public static String CANCEL_ACTION = "Cancel";

	@Autowired
	protected EventBus eventBus;
	
	protected ComponentContainer compContainer;
	
	@Override
	public void handleRequest(Params params) {
		
	}

	public ComponentContainer createMainLayout() {
		compContainer = initMainLayout();
		return compContainer;
	}
	
	public ComponentContainer getCompContainer() {
		return compContainer;
	}

	protected abstract ComponentContainer initMainLayout();
	
	@Override
	public void openView() {
		
		ViewOpenedEvent event = new ViewOpenedEvent(this);
		eventBus.fireEvent(event);
	}	

}

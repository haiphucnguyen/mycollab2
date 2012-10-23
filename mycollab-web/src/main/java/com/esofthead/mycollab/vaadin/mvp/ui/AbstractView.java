package com.esofthead.mycollab.vaadin.mvp.ui;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.vaadin.mvp.eventbus.EventBus;
import com.vaadin.ui.ComponentContainer;

@SuppressWarnings("serial")
public abstract class AbstractView implements View, Serializable {

	@Autowired
	protected EventBus eventBus;
	
	protected ComponentContainer compContainer;
	
	public ComponentContainer createMainLayout() {
		compContainer = initMainLayout();
		return compContainer;
	}
	
	protected abstract ComponentContainer initMainLayout();
	
	@Override
	public void openView() {
		
		ViewOpenedEvent event = new ViewOpenedEvent(this);
		eventBus.fireEvent(event);
	}	

}

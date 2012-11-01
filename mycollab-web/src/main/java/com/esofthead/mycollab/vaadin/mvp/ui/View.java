package com.esofthead.mycollab.vaadin.mvp.ui;

import com.vaadin.ui.ComponentContainer;


public interface View {

	void openView();
	
	void handleRequest(Params params);
	
	ComponentContainer createMainLayout();
	
	ComponentContainer getCompContainer();
}

package com.esofthead.mycollab.vaadin.resource.ui;

import java.util.Iterator;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public class ServiceMenu extends ButtonGroup {
	private static final long serialVersionUID = 1L;
	
	private static final String COMPONENT_STYLENAME = "service-menu";
	private static final String SELECTED_STYLENAME = "selected";
	
	public ServiceMenu() {
		super();
		
		this.setStyleName(COMPONENT_STYLENAME);
	}
	
	public void addService(String serviceName, Resource linkIcon, ClickListener listener) {
		final Button newService = new Button(serviceName, listener);
		newService.setIcon(linkIcon);
		newService.setStyleName("link");
		
		this.addButton(newService);
	}
	
	public void selectService(int index) {
		Iterator<Component> iterator = this.iterator();
		
		int i = 0;
		
		while(iterator.hasNext()) {
			Component comp = iterator.next();
			if(i == index) {
				comp.addStyleName(SELECTED_STYLENAME);
			} else {
				comp.removeStyleName(SELECTED_STYLENAME);
			}
			i++;
		}
	}

}

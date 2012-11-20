package com.esofthead.mycollab.shell;

import com.esofthead.mycollab.vaadin.events.EventBus;
import com.vaadin.ui.ComponentContainer;

public class ShellController {
	
	private ComponentContainer container;
	
	private EventBus eventBus;
	
	public ShellController(ComponentContainer container) {
		this.container = container;
		
		bind();
	}
	
	private void bind() {
		
	}
}

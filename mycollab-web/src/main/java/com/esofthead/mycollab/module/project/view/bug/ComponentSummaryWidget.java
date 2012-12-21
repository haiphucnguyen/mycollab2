package com.esofthead.mycollab.module.project.view.bug;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class ComponentSummaryWidget extends Panel {
	private static final long serialVersionUID = 1L;

	public ComponentSummaryWidget() {
		super("Component");

		this.addComponent(new Label("a"));
		this.addComponent(new Label("b"));
	}

}

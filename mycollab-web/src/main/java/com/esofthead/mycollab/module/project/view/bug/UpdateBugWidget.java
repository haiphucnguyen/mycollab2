package com.esofthead.mycollab.module.project.view.bug;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class UpdateBugWidget  extends Panel {
	private static final long serialVersionUID = 1L;

	public UpdateBugWidget() {
		super("Updated Bug Recently");
		
		this.addComponent(new Label("c"));
		this.addComponent(new Label("d"));
		this.addComponent(new Label("d"));
		this.addComponent(new Label("d"));
	}
}

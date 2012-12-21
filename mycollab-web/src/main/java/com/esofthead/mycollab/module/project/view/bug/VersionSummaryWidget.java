package com.esofthead.mycollab.module.project.view.bug;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class VersionSummaryWidget extends Panel {
	private static final long serialVersionUID = 1L;

	public VersionSummaryWidget() {
		super("Versions");

		this.addComponent(new Label("c"));
		this.addComponent(new Label("d"));
		this.addComponent(new Label("d"));
		this.addComponent(new Label("d"));
	}

}

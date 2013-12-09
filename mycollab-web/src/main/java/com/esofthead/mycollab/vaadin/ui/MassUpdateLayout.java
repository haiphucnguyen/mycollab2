package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

public class MassUpdateLayout extends CssLayout {
	private static final long serialVersionUID = 1L;
	private final CssLayout body;

	public MassUpdateLayout() {
		this.setSizeFull();
		this.setStyleName("readview-layout");

		this.body = new CssLayout();
		this.body.setStyleName("readview-layout-body");
		this.body.setSizeFull();
		this.addComponent(this.body);
	}

	public void addBody(final Component content) {
		this.body.addComponent(content);
	}
}

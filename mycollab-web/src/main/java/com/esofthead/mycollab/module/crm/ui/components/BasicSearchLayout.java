package com.esofthead.mycollab.module.crm.ui.components;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomLayout;

abstract public class BasicSearchLayout extends CustomLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasicSearchLayout() {
		super("basicSearch");
		setStyleName("basicSearchLayout");
		this.initLayout();
	}

	private void initLayout() {
		ComponentContainer header = constructHeader();
		ComponentContainer body = constructBody();
		this.addComponent(header, "basicSearchHeader");
		this.addComponent(body, "basicSearchBody");
	}

	abstract public ComponentContainer constructHeader();

	abstract public ComponentContainer constructBody();
}

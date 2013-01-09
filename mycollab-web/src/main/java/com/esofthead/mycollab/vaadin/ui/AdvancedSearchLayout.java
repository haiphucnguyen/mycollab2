package com.esofthead.mycollab.module.crm.ui.components;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomLayout;

@SuppressWarnings("serial")
public abstract class AdvancedSearchLayout extends CustomLayout {

	public AdvancedSearchLayout() {
		super("advancedSearch");
		setStyleName("advancedSearchLayout");
		initLayout();
	}

	public void initLayout() {
		ComponentContainer header = constructHeader();
		ComponentContainer body = constructBody();
		ComponentContainer footer = constructFooter();
		this.addComponent(header, "advSearchHeader");
		this.addComponent(body, "advSearchBody");
		this.addComponent(footer, "advSearchFooter");
	}

	public abstract ComponentContainer constructHeader();

	public abstract ComponentContainer constructBody();

	public abstract ComponentContainer constructFooter();
}

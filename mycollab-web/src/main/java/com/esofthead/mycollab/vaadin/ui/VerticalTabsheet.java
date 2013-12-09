package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class VerticalTabsheet extends CustomComponent {
	private static final long serialVersionUID = 1L;

	private VerticalLayout tabNavigator;
	private CssLayout tabContainer;

	public VerticalTabsheet() {
		HorizontalLayout contentLayout = new HorizontalLayout();
		tabNavigator = new VerticalLayout();
		tabContainer = new CssLayout();

		contentLayout.addComponent(tabNavigator);
		contentLayout.addComponent(tabContainer);
		this.setCompositionRoot(contentLayout);
	}
	
	
}

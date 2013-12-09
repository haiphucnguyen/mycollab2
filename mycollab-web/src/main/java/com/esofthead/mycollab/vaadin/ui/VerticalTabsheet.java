package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.server.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
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

	public void addTab(Component component, String caption, Resource resource) {

	}

	public void addSelectedTabChangeListener(
			final TabSheet.SelectedTabChangeListener listener) {

	}

	public Tab selectTab(String viewName) {
		return null;
	}

	public Tab getSelectedTab() {
		return null;
	}
}

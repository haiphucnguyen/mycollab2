package com.esofthead.vaadin.navigationbarquickmenu.client;

import com.esofthead.vaadin.navigationbarquickmenu.NavigationBarQuickMenu;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractSingleComponentContainerConnector;
import com.vaadin.shared.ui.Connect;

@Connect(NavigationBarQuickMenu.class)
public class NavigationBarQuickMenuConnector extends
		AbstractSingleComponentContainerConnector {
	private static final long serialVersionUID = 7685952756391226734L;

	public NavigationBarQuickMenuConnector() {

	}

	@Override
	protected Widget createWidget() {
		return GWT.create(VNavigationBarQuickMenu.class);
	}

	@Override
	public VNavigationBarQuickMenu getWidget() {
		return (VNavigationBarQuickMenu) super.getWidget();
	}

	@Override
	public NavigationBarQuickMenuState getState() {
		return (NavigationBarQuickMenuState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		getWidget().updateButtonCaption(getState().buttonCaption);
		getWidget().setEnabled(getState().enabled);
	}

	@Override
	public void updateCaption(ComponentConnector connector) {
		// Not supported
	}

	@Override
	public void onConnectorHierarchyChange(
			ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
		getWidget().setWidget(getContentWidget());
	}

}

package com.esofthead.vaadin.mobilecomponent.client.ui;

import java.util.List;

import com.esofthead.vaadin.mobilecomponent.MobileViewToolbar;
import com.esofthead.vaadin.mobilecomponent.client.VMobileViewToolbar;
import com.esofthead.vaadin.mobilecomponent.client.shared.MobileViewToolbarState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.shared.ui.Connect;

@Connect(MobileViewToolbar.class)
public class MobileViewToolbarConnector extends AbstractComponentContainerConnector {

	private static final long serialVersionUID = -2664274991068733980L;

	@Override
	public void updateCaption(ComponentConnector connector) {
		// Do nothing
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(VMobileViewToolbar.class);
	}

	@Override
	public VMobileViewToolbar getWidget() {
		return (VMobileViewToolbar) super.getWidget();
	}

	@Override
	public MobileViewToolbarState getState() {
		return (MobileViewToolbarState) super.getState();
	}

	@Override
	public void onConnectorHierarchyChange(
			ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
		if (getParent() == null)
			return;

		List<ComponentConnector> children = getChildComponents();
		getWidget().setWidget(children.get(0).getWidget());
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
	}

}

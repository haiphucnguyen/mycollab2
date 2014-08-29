package com.esofthead.vaadin.mobilecomponent.client.ui;

import com.esofthead.vaadin.mobilecomponent.MobileNavigationManager;
import com.esofthead.vaadin.mobilecomponent.client.VMobileNavigationManager;
import com.esofthead.vaadin.mobilecomponent.client.shared.MobileNavigationManagerState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.vcom.navigation.NavigationManagerConnector;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;

@Connect(MobileNavigationManager.class)
public class MobileNavigationManagerConnector extends
		NavigationManagerConnector {

	private static final long serialVersionUID = 2649671282085312270L;

	@Override
	protected Widget createWidget() {
		return GWT.create(VMobileNavigationManager.class);
	}

	@Override
	public VMobileNavigationManager getWidget() {
		return (VMobileNavigationManager) super.getWidget();
	}

	@Override
	public MobileNavigationManagerState getState() {
		return (MobileNavigationManagerState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {

		if (getState().navigationMenu != null) {
			Widget navMenu = ((ComponentConnector) getState().navigationMenu)
					.getWidget();
			getWidget().setNavigationMenu(navMenu);
		} else {
			getWidget().setNavigationMenu(null);
		}

		super.onStateChanged(stateChangeEvent);
	}

}

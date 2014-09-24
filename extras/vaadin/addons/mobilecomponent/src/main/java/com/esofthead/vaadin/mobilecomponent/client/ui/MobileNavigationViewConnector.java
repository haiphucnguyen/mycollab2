package com.esofthead.vaadin.mobilecomponent.client.ui;

import com.esofthead.vaadin.mobilecomponent.MobileNavigationView;
import com.esofthead.vaadin.mobilecomponent.client.VMobileNavigationManager;
import com.esofthead.vaadin.mobilecomponent.client.VMobileNavigationView;
import com.esofthead.vaadin.mobilecomponent.client.shared.MobileNavigationViewState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.vcom.navigation.NavigationViewConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;

@Connect(MobileNavigationView.class)
public class MobileNavigationViewConnector extends NavigationViewConnector {

	private static final long serialVersionUID = 1413079694967359278L;

	public MobileNavigationViewConnector() {
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(VMobileNavigationView.class);
	}

	// We must implement getWidget() to cast to correct type
	@Override
	public VMobileNavigationView getWidget() {
		return (VMobileNavigationView) super.getWidget();
	}

	// We must implement getState() to cast to correct type
	@Override
	public MobileNavigationViewState getState() {
		return (MobileNavigationViewState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		if (getState().showToggleButton && !VMobileNavigationManager.IS_TABLET) {
			getWidget().addToggleButton();
		} else {
			getWidget().removeToggleButton();
		}
	}

}

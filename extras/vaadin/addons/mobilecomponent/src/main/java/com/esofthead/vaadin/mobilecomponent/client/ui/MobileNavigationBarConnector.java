package com.esofthead.vaadin.mobilecomponent.client.ui;

import com.esofthead.vaadin.mobilecomponent.MobileNavigationBar;
import com.esofthead.vaadin.mobilecomponent.client.VMobileNavigationBar;
import com.esofthead.vaadin.mobilecomponent.client.shared.MobileNavigationBarState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.vcom.navigation.NavigationBarConnector;
import com.vaadin.shared.ui.Connect;

@Connect(MobileNavigationBar.class)
public class MobileNavigationBarConnector extends NavigationBarConnector {
	private static final long serialVersionUID = 1621011360160110605L;

	@Override
	protected Widget createWidget() {
		return GWT.create(VMobileNavigationBar.class);
	}

	@Override
	public VMobileNavigationBar getWidget() {
		return (VMobileNavigationBar) super.getWidget();
	}

	@Override
	public MobileNavigationBarState getState() {
		return (MobileNavigationBarState) super.getState();
	}

}

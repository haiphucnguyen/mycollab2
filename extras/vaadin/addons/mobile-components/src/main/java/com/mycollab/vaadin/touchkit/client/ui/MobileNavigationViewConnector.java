package com.mycollab.vaadin.touchkit.client.ui;

import com.mycollab.vaadin.touchkit.MobileNavigationView;
import com.mycollab.vaadin.touchkit.client.VMobileNavigationManager;
import com.mycollab.vaadin.touchkit.client.VMobileNavigationView;
import com.mycollab.vaadin.touchkit.client.shared.MobileNavigationViewState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.vcom.navigation.NavigationViewConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.layout.ElementResizeEvent;
import com.vaadin.client.ui.layout.ElementResizeListener;
import com.vaadin.shared.ui.Connect;

@Connect(MobileNavigationView.class)
public class MobileNavigationViewConnector extends NavigationViewConnector
		implements ElementResizeListener {

	private static final long serialVersionUID = 1413079694967359278L;

	@Override
	protected void init() {
		super.init();
		this.getLayoutManager().addElementResizeListener(
				getWidget().getElement(), this);
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

		dealWithToggleButton();
	}

	@Override
	public void onElementResize(ElementResizeEvent e) {
		dealWithToggleButton();
	}

	protected void dealWithToggleButton() {
		if (getState().showToggleButton && !VMobileNavigationManager.IS_TABLET) {
			getWidget().addToggleButton();
		} else {
			getWidget().removeToggleButton();
		}
	}

}

package com.esofthead.vaadin.mobilecomponent.client.ui;

import com.esofthead.vaadin.mobilecomponent.MobileNavigationManager;
import com.esofthead.vaadin.mobilecomponent.client.VMobileNavigationManager;
import com.esofthead.vaadin.mobilecomponent.client.shared.MobileNavigationManagerState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.vcom.navigation.NavigationManagerConnector;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;

@Connect(MobileNavigationManager.class)
public class MobileNavigationManagerConnector extends
		NavigationManagerConnector implements Handler, ResizeHandler {

	private static final long serialVersionUID = 2649671282085312270L;

	private HandlerRegistration resizeHandlerRegistration;

	@Override
	protected Widget createWidget() {
		VMobileNavigationManager widget = GWT
				.create(VMobileNavigationManager.class);
		widget.addAttachHandler(this);
		return widget;
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

		getWidget().beResponsive();

		super.onStateChanged(stateChangeEvent);
	}

	@Override
	public void onResize(ResizeEvent event) {
		if (event.getWidth() >= VMobileNavigationManager.TABLET_WIDTH_THRESHOLD
				&& !VMobileNavigationManager.IS_TABLET
				&& getWidget().getMenuVisibility())
			getWidget().toggleMenu();

		if (event.getWidth() >= VMobileNavigationManager.TABLET_WIDTH_THRESHOLD)
			VMobileNavigationManager.IS_TABLET = true;
		else
			VMobileNavigationManager.IS_TABLET = false;

		getWidget().beResponsive();
		this.getLayoutManager().setNeedsMeasureRecursively(this);
	}

	@Override
	public void onAttachOrDetach(AttachEvent event) {
		if (event.isAttached()) {
			resizeHandlerRegistration = Window.addResizeHandler(this);
		} else {
			resizeHandlerRegistration.removeHandler();
		}
	}

}

package com.mycollab.vaadin.touchkit.client.ui;

import java.util.logging.Logger;

import com.mycollab.vaadin.touchkit.MobileNavigationManager;
import com.mycollab.vaadin.touchkit.client.VMobileNavigationManager;
import com.mycollab.vaadin.touchkit.client.shared.MobileNavigationManagerState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
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
import com.vaadin.client.ui.layout.ElementResizeEvent;
import com.vaadin.client.ui.layout.ElementResizeListener;
import com.vaadin.shared.ui.Connect;

@Connect(MobileNavigationManager.class)
public class MobileNavigationManagerConnector extends
		NavigationManagerConnector implements Handler, ResizeHandler {

	private static final long serialVersionUID = 2649671282085312270L;

	private HandlerRegistration resizeHandlerRegistration;

	public static Logger logger = Logger
			.getLogger(MobileNavigationManagerConnector.class.getName());

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

		if (stateChangeEvent.hasPropertyChanged("navigationMenu")) {
			getWidget().beResponsive();
		}

		super.onStateChanged(stateChangeEvent);
	}

	@Override
	public void onResize(ResizeEvent event) {
		if (event.getWidth() >= VMobileNavigationManager.TABLET_WIDTH_THRESHOLD
				&& !VMobileNavigationManager.IS_TABLET
				&& getWidget().getMenuVisibility())
			getWidget().toggleMenu();

		VMobileNavigationManager.IS_TABLET = event.getWidth() >= VMobileNavigationManager.TABLET_WIDTH_THRESHOLD;

		getWidget().beResponsive();
	}

	@Override
	public void onAttachOrDetach(AttachEvent event) {
		if (event.isAttached()) {
			resizeHandlerRegistration = Window.addResizeHandler(this);
		} else {
			resizeHandlerRegistration.removeHandler();
		}
	}

	private final ElementResizeListener listener = new ElementResizeListener() {
		public void onElementResize(ElementResizeEvent e) {
			Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

				@Override
				public void execute() {
					MobileNavigationManagerConnector.this.getLayoutManager()
							.setNeedsMeasureRecursively(
									MobileNavigationManagerConnector.this);
				}
			});

		}
	};

	@Override
	protected void init() {
		getLayoutManager().addElementResizeListener(
				getWidget().getViewContainer(), listener);
	}

	@Override
	public void onUnregister() {
		super.onUnregister();
		getLayoutManager().removeElementResizeListener(
				getWidget().getViewContainer(), listener);
	}

}

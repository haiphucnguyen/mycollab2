package com.esofthead.vaadin.floatingcomponent.client;

import java.util.logging.Logger;

import com.esofthead.vaadin.floatingcomponent.FloatingComponent;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

/**
 * @author MyCollab Ltd.
 *
 */

@Connect(FloatingComponent.class)
public class FloatingComponentConnector extends AbstractExtensionConnector
		implements ScrollHandler {

	private static final long serialVersionUID = -5478923080638016956L;

	private int originalOffset;

	private int containerOffset;

	private Widget container;

	private HandlerRegistration handlerRegistration;

	private Widget targetWidget;

	private Style currentStyle;

	public static Logger log = Logger
			.getLogger(FloatingComponentConnector.class.getName());

	@Override
	protected void extend(ServerConnector target) {
		targetWidget = ((ComponentConnector) target).getWidget();
		currentStyle = targetWidget.getElement().getStyle();
	}

	private Widget getWidget(Element el) {
		EventListener listener = DOM.getEventListener(el);

		if (listener == null) {
			return null;
		}

		if (listener instanceof Widget)
			return (Widget) listener;

		return null;
	}

	@Override
	public void onScroll(ScrollEvent event) {
		if (container.getElement().getScrollTop() > originalOffset) {
			currentStyle.setPosition(Position.FIXED);
			currentStyle.setTop(containerOffset, Unit.PX);
		} else {
			currentStyle.setPosition(Position.RELATIVE);
			currentStyle.clearTop();
		}
	}

	@Override
	public FloatingComponentState getState() {
		return (FloatingComponentState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		if (handlerRegistration != null)
			handlerRegistration.removeHandler();

		if (getState().containerId != null)
			container = getWidget(DOM.getElementById(getState().containerId));
		else
			container = RootPanel.get();

		handlerRegistration = (container != null ? container : RootPanel.get())
				.addDomHandler(this, ScrollEvent.getType());

		containerOffset = container.getElement().getAbsoluteTop();

		originalOffset = targetWidget.getElement().getAbsoluteTop()
				- containerOffset;

	}
}

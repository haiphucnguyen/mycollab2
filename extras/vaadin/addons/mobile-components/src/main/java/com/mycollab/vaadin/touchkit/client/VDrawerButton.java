package com.mycollab.vaadin.touchkit.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.HasTouchStartHandlers;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

public class VDrawerButton extends Widget implements HasTouchStartHandlers {

	public static String STYLENAME = "toggle-nav-btn";

	public VDrawerButton() {
		setElement(Document.get().createDivElement());
		setStylePrimaryName(STYLENAME);
	}

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return addDomHandler(handler, TouchStartEvent.getType());
	}

}

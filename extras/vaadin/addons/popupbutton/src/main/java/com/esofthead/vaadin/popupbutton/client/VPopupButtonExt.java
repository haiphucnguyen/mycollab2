package com.esofthead.vaadin.popupbutton.client;

import org.vaadin.hene.popupbutton.widgetset.client.ui.VPopupButton;

import com.google.gwt.user.client.ui.Widget;

public class VPopupButtonExt extends VPopupButton {

	public void setPopupPositionWidget(Widget w) {
		this.popupPositionWidget = w;
	}
}

package com.esofthead.vaadin.popupbutton.client;

import org.vaadin.hene.popupbutton.widgetset.client.ui.PopupButtonConnector;

import com.esofthead.vaadin.popupbutton.PopupButtonExt;
import com.google.gwt.core.client.GWT;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;

@Connect(PopupButtonExt.class)
public class PopupButtonExtConnector extends PopupButtonConnector {
	private static final long serialVersionUID = -4709547369342620776L;

	@Override
	protected VPopupButtonExt createWidget() {
		return GWT.create(VPopupButtonExt.class);
	}

	@Override
	public VPopupButtonExt getWidget() {
		return (VPopupButtonExt) super.getWidget();
	}

	@Override
	public PopupButtonExtState getState() {
		return (PopupButtonExtState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		getWidget().setPopupPositionWidget(((ComponentConnector)getState().popupPositionConnector).getWidget());
	}


}

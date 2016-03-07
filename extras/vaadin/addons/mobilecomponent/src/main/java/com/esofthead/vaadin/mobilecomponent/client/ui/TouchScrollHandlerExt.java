package com.esofthead.vaadin.mobilecomponent.client.ui;

import com.vaadin.client.ui.TouchScrollDelegate.TouchScrollHandler;

public class TouchScrollHandlerExt extends TouchScrollHandler {

	@Override
	protected boolean requiresDelegate() {
		return false;
	}

}

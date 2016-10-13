package com.mycollab.vaadin.touchkit.client.ui;

import com.vaadin.client.ui.TouchScrollDelegate.TouchScrollHandler;

public class TouchScrollHandlerExt extends TouchScrollHandler {

    @Override
    protected boolean requiresDelegate() {
        return false;
    }

}

package com.esofthead.vaadin.mobilecomponent.client;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.ui.VNavigationBar;

public class VMobileNavigationBar extends VNavigationBar {

	@Override
    public void setLeftWidget(Widget left) {
		Widget currentLeftWidget = getWidget(0);
        remove(currentLeftWidget);
        currentLeftWidget = null;
        
        super.setLeftWidget(left);
    }

    @Override
    public void setRightWidget(Widget right) {
        if (getWidgetCount() > 1) {
        	Widget currentRightWidget = getWidget(1);
        	remove(currentRightWidget);
        	currentRightWidget = null;
        }
        
        super.setRightWidget(right);
    }	
}

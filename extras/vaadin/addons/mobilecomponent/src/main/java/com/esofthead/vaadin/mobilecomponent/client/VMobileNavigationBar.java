package com.esofthead.vaadin.mobilecomponent.client;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.ui.VNavigationBar;

public class VMobileNavigationBar extends VNavigationBar {

	private Widget leftComponent;
	private Widget rightComponent;
	
	@Override
    public void setLeftWidget(Widget left) {
		if(getWidgetCount() == 0) {
			super.setLeftWidget(left);
			leftComponent = left;
		} else{
			if (left != null) {
	            if (leftComponent != left && leftComponent != null) {
	            	remove(leftComponent);
	            }

	            leftComponent = left;
	            if (!leftComponent.isAttached()) {
	                super.setLeftWidget(left);
	            }
	        } else if (leftComponent != null) {
	            remove(leftComponent);
	        }
		}
        
       
    }

    @Override
    public void setRightWidget(Widget right) {
        if (getWidgetCount() == 1) {
        	super.setRightWidget(right);
        	rightComponent = right;
        } else {
        	if (right != null) {
                if (rightComponent != right && rightComponent != null) {
                    remove(rightComponent);
                }

                rightComponent = right;
                if (!rightComponent.isAttached()) {
                    super.setRightWidget(right);
                }
            } else if (rightComponent != null) {
            	remove(rightComponent);
            }
        }
    }	
}

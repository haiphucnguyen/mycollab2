package com.esofthead.vaadin.mobilecomponent;

import com.esofthead.vaadin.mobilecomponent.client.shared.MobileNavigationViewState;
import com.vaadin.addon.touchkit.ui.NavigationBar;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Component;

public class MobileNavigationView extends NavigationView {
	
	private static final long serialVersionUID = -5143004451316416855L;
	
	private final MobileNavigationBar navBar = new MobileNavigationBar();

	public MobileNavigationView() {
	}

    public void setToggleButton(boolean showButton) {
        getState().showToggleButton = showButton;
    }
    
    @Override
    public NavigationBar getNavigationBar() {
    	return navBar;
    }
    
    public MobileNavigationBar getNavigationBarExt() {
    	return navBar;
    }
    
    @Override
    public void setLeftComponent(Component c) {
    	setToggleButton(false);
    	getNavigationBarExt().setLeftComponent(c);
    }
    
    @Override
    public void setRightComponent(Component c) {
    	getNavigationBarExt().setRightComponent(c);
    }

	@Override
	public MobileNavigationViewState getState() {
		return (MobileNavigationViewState) super.getState();
	}
}

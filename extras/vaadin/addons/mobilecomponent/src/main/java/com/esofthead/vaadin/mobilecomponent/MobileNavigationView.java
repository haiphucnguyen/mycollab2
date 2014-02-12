package com.esofthead.vaadin.mobilecomponent;

import com.esofthead.vaadin.mobilecomponent.client.shared.MobileNavigationViewState;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Component;

public class MobileNavigationView extends NavigationView {
	
	private static final long serialVersionUID = -5143004451316416855L;
	
	protected boolean showToggleButton = false;

	public MobileNavigationView() {
	}

    public void setToggleButton(boolean showButton) {
        this.showToggleButton = showButton;
    }
    
    @Override
    public void setLeftComponent(Component c) {
    	setToggleButton(false);
    	getNavigationBar().setLeftComponent(c);
    }

	@Override
	public MobileNavigationViewState getState() {
		return (MobileNavigationViewState) super.getState();
	}

	@Override
	protected void onBecomingVisible() {
		super.onBecomingVisible();
		
		getState().showToggleButton = this.showToggleButton;
		markAsDirty();
	}
	
	@Override
	public void setPreviousComponent(Component c) {}
	
	
}

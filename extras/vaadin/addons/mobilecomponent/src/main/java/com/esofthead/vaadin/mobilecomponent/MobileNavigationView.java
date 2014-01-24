package com.esofthead.vaadin.mobilecomponent;

import com.esofthead.vaadin.mobilecomponent.client.shared.MobileNavigationViewState;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Component;

public class MobileNavigationView extends NavigationView {
	
	private static final long serialVersionUID = -5143004451316416855L;

	public MobileNavigationView() {
	}

    public void setToggleButton(boolean showButton) {
        getState().showToggleButton = showButton;
    }

	@Override
	public MobileNavigationViewState getState() {
		return (MobileNavigationViewState) super.getState();
	}

    @Override
    public void setLeftComponent(Component c) {
        setToggleButton(false);
        super.setLeftComponent(c);
    }
}

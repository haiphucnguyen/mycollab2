package com.esofthead.vaadin.mobilecomponent;

import com.esofthead.vaadin.mobilecomponent.client.shared.MobileNavigationViewState;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Component;

public class MobileNavigationView extends NavigationView {

	private static final long serialVersionUID = -5143004451316416855L;

	private Component previousComponent;
	private final NavigationButton backBtn;

	public MobileNavigationView() {
		super();
		backBtn = (NavigationButton) getLeftComponent();
		backBtn.setCaption("Back");
		backBtn.setStyleName("back");
	}

	public void setToggleButton(boolean showButton) {
		getState().showToggleButton = showButton;
		if (!showButton && this.previousComponent != null) {
			if (!backBtn.isAttached())
				setLeftComponent(backBtn);
			super.setPreviousComponent(this.previousComponent);
		}
	}

	@Override
	public MobileNavigationViewState getState() {
		return (MobileNavigationViewState) super.getState();
	}

	@Override
	public void setPreviousComponent(Component c) {
		previousComponent = c;
		if (getState().showToggleButton)
			return;

		super.setPreviousComponent(c);
	}

}

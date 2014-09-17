package com.esofthead.vaadin.mobilecomponent;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.Button;

public class NavigationMenuButton extends AbstractExtension {
	private static final long serialVersionUID = -584626789053800394L;

	protected NavigationMenuButton(Button button) {
		super.extend(button);
	}

	public static NavigationMenuButton extend(Button button) {
		return new NavigationMenuButton(button);
	}
}

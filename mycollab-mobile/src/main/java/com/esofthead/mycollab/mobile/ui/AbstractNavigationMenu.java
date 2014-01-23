package com.esofthead.mycollab.mobile.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;

public abstract class AbstractNavigationMenu extends CssLayout {
	private static final long serialVersionUID = -8517225259459579426L;
	
	public AbstractNavigationMenu() {
		super();
		
		setWidth("100%");
		setStyleName("navigation-menu");
	}
	
	protected class MenuButton extends Button {
		private static final long serialVersionUID = -2516191547029466932L;
		
		public MenuButton(String caption, String iconCode) {
			super("<span class=\"nav-btn-icon\" aria-hidden=\"true\" data-icon=\""+ iconCode + "\"></span>" + caption);
			setStyleName("nav-btn");
			setHtmlContentAllowed(true);
			setWidth("100%");
		}
	}
}

package com.esofthead.vaadin.mobilecomponent.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.ui.Css3Propertynames;
import com.vaadin.addon.touchkit.gwt.client.ui.VNavigationManager;

public class VMobileNavigationManager extends VNavigationManager {

	private final SimplePanel navigationMenu;

	private boolean menuVisibility = false;

	private int currentOffset = 0;

	private final SimplePanel wrapper;
	private final DivElement content;

	public VMobileNavigationManager() {
		super();

		content = getElement().cast();
		wrapper = new SimplePanel();
		wrapper.setStylePrimaryName("v-mobilecomponent");

		navigationMenu = new SimplePanel();
		navigationMenu.setStyleName(getStyleName() + "-navigation");

		wrapper.getElement().appendChild(content);

		setElement(wrapper.getElement());
	}

	public void setNavigationMenu(Widget widget) {
		if (!navigationMenu.isAttached()) {
			add(navigationMenu, wrapper.getElement());
		}

		navigationMenu.setWidget(widget);
	}

	public boolean getMenuVisibility() {
		return menuVisibility;
	}

	public void toggleMenu() {
		toggleMenu(!menuVisibility);
	}

	public void toggleMenu(boolean visibility) {
		menuVisibility = visibility;

		Style style = content.getStyle();
		style.setProperty(Css3Propertynames.transition(), "");

		if (menuVisibility) {
			int menuWidth = getNavigationMenuWidth();
			if (menuWidth == 0) {
				menuVisibility = false;
			} else {
				moveLeft(menuWidth, true);
				currentOffset = menuWidth;
			}
		} else {
			moveLeft(0, true);
			currentOffset = 0;
		}
	}

	public int getNavigationMenuWidth() {
		return navigationMenu.getOffsetWidth();
	}

	public void setHorizontalOffsetExt(int deltaX, boolean animate) {
		int tmpOffset = currentOffset + deltaX;
		if (tmpOffset >= getNavigationMenuWidth()) {
			tmpOffset = getNavigationMenuWidth();
			menuVisibility = true;
		} else if (tmpOffset <= 0) {
			tmpOffset = 0;
			menuVisibility = false;
		}

		moveLeft(tmpOffset, animate);
	}

	public void moveLeft(int pos, boolean animate) {
		final Style style = content.getStyle();
		if (!animate)
			style.setProperty(Css3Propertynames.transition(), "none");

		style.setProperty(Css3Propertynames.transform(), "translateX(" + pos
				+ "px)");
	}
}
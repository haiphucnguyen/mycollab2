package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.ColorPickerArea;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class CustomColorPickerArea extends ColorPickerArea {
	private static final long serialVersionUID = -8631349584720412229L;

	public CustomColorPickerArea(String caption, String initialColor) {
		super(caption, new Color(Integer.parseInt(initialColor, 16)));

		this.setWidth("55px");
		this.setHeight("25px");
		this.setPosition(Page.getCurrent().getBrowserWindowWidth() / 2 - 248 / 2, Page
						.getCurrent().getBrowserWindowHeight() / 2 - 508 / 2);
	}
}

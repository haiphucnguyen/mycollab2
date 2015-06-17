package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.ColorPickerArea;

/**
 * This file is part of mycollab-web-premium.
 *
 * mycollab-web-premium is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-premium is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-premium.  If not, see <http://www.gnu.org/licenses/>.
 */

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

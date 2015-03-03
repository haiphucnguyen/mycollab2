/**
 * This file is part of mycollab-ui.
 *
 * mycollab-ui is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-ui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-ui.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public class HeaderWithFontAwesome  extends Label {
    private FontAwesome iconFont;
    private String title;

    public HeaderWithFontAwesome(FontAwesome iconFont, String title) {
        super();
        this.setContentMode(ContentMode.HTML);
        this.iconFont = iconFont;
        this.setStyleName("hdr-text");
        updateTitle(title);
    }

    public void updateTitle(String value) {
        this.title = value;
        this.setValue(iconFont.getHtml() + " " + value);
    }

    public void appendToTitle(String value) {
        this.setValue(iconFont.getHtml() + " " + title + " " + value);
    }
}

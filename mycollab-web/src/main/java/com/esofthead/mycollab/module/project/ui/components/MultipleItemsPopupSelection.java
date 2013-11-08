/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.ui.components;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class MultipleItemsPopupSelection extends PopupButton {

    private Panel panel;

    public MultipleItemsPopupSelection() {
        panel = new Panel();
        panel.setStyleName(Reindeer.PANEL_LIGHT);
        panel.setHeight("200px");
        panel.setWidth("300px");
        this.addComponent(panel);

    }

    public void addItemComponent(Component itemName) {
        panel.addComponent(itemName);
    }
}

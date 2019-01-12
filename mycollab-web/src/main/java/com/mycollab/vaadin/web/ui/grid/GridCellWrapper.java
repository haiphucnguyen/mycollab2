/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.web.ui.grid;

import com.mycollab.core.MyCollabException;
import com.mycollab.vaadin.web.ui.MultiSelectComp;
import com.mycollab.web.CustomLayoutExt;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.0.8
 */
public class GridCellWrapper extends CustomLayoutExt {
    private static final long serialVersionUID = 1L;

    private String fieldWidth;
    private Component field;

    GridCellWrapper() {
        super("gridCell");
        this.setStyleName("gridform-field");
    }

    public void addCaption(Component component) {
        addComponent(component, "caption");
    }

    public void addField(Component component) {
        if (!(component instanceof Button))
            component.setCaption(null);

        if (component instanceof MultiSelectComp) {
            component.setWidth("200px");
        } else if (component instanceof AbstractTextField || component instanceof RichTextArea) {
            component.setWidth("100%");
        }

        addComponent(component, "content");
    }

    public void setFieldWidth(String fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    @Override
    public void addComponent(Component component) {
        throw new MyCollabException("Error");
    }
}
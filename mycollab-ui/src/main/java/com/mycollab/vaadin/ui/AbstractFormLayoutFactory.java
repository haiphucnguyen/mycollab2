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
package com.mycollab.vaadin.ui;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public abstract class AbstractFormLayoutFactory implements IFormLayoutFactory {
    private Set<String> fields = new HashSet<>();

    public abstract AbstractComponent getLayout();

    protected abstract Component onAttachField(Object propertyId, Component field);

    @Override
    public Component attachField(Object propertyId, Component field) {
        Component component = onAttachField(propertyId, field);
        if (component != null) {
            fields.add((String) propertyId);
            return component;
        }
        return null;
    }

    public Set<String> bindFields() {
        return fields;
    }
}

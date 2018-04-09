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
package com.mycollab.vaadin.web.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.fields.AbstractNumberField;
import org.vaadin.viritin.fluency.ui.FluentCustomField;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */

// TODO: revise this class
public class DoubleField extends CustomField<Double> {

    @Override
    protected Component initContent() {
        return new TextField();
    }

    @Override
    protected void doSetValue(Double aDouble) {

    }

    @Override
    public Double getValue() {
        return null;
    }
}

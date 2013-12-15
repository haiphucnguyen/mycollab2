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
package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.data.Item;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class DefaultEditFormFieldFactory extends DefaultFieldFactory {

	private static final long serialVersionUID = 1L;

	@Override
	public Field<?> createField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {
		Field<?> field = onCreateField(item, propertyId, uiContext);
		if (field == null) {
			field = super.createField(item, propertyId, uiContext);
			if (field instanceof TextField) {
				((TextField) field).setNullRepresentation("");
				((TextField) field).setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
				((TextField) field).setCaption(null);
			}
		} else {
			if (field instanceof AbstractTextField) {
				((AbstractTextField) field).setNullRepresentation("");
			} else if (field instanceof RichTextArea) {
				((RichTextArea) field).setNullRepresentation("");
			}
		}
		return field;
	}

	protected Field onCreateField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {
		return null;
	}
}

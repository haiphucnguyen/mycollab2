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

import java.util.Collection;

import com.vaadin.data.Item;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;

public class GenericForm extends Form {

	private static final long serialVersionUID = 1L;
	public static String SAVE_ACTION = "Save";
	public static String SAVE_AND_NEW_ACTION = "Save & New";
	public static String ASSIGN_ACTION = "Assign";
	public static String EDIT_ACTION = "Edit";
	public static String CANCEL_ACTION = "Cancel";
	public static String DELETE_ACTION = "Delete";
	public static String CLONE_ACTION = "Clone";
	private IFormLayoutFactory factory;

	public GenericForm() {
		super();
	}

	public void setFormLayoutFactory(IFormLayoutFactory factory) {
		this.factory = factory;
	}

	@Override
	public void setItemDataSource(final Item newDataSource) {
		this.setLayout(factory.getLayout());
		super.setItemDataSource(newDataSource);
	}

	@Override
	public void setItemDataSource(Item newDataSource, Collection<?> propertyIds) {
		this.setLayout(factory.getLayout());
		super.setItemDataSource(newDataSource, propertyIds);
	}

	@Override
	protected void attachField(Object propertyId, Field field) {
		factory.attachField(propertyId, field);
	}
}

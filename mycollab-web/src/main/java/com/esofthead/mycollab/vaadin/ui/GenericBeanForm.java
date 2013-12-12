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
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;

public class GenericBeanForm<B> extends CssLayout {

	private static final long serialVersionUID = 1L;
	public static String SAVE_ACTION = "Save";
	public static String SAVE_AND_NEW_ACTION = "Save & New";
	public static String ASSIGN_ACTION = "Assign";
	public static String EDIT_ACTION = "Edit";
	public static String CANCEL_ACTION = "Cancel";
	public static String DELETE_ACTION = "Delete";
	public static String CLONE_ACTION = "Clone";

	protected IFormLayoutFactory layoutFactory;
	protected IBeanFieldGroupFieldFactory<B> fieldFactory;

	private B bean;

	public GenericBeanForm() {
		super();
	}

	public GenericBeanForm(IFormLayoutFactory layoutFactory) {
		setFormLayoutFactory(layoutFactory);
	}

	public void setFormLayoutFactory(IFormLayoutFactory layoutFactory) {
		this.layoutFactory = layoutFactory;
	}

	public void setBeanFormFieldFactory(
			IBeanFieldGroupFieldFactory<B> fieldFactory) {
		this.fieldFactory = fieldFactory;
	}

	public void setFormFieldFactory(FormFieldFactory fieldFactory) {
	}

	public B getBean() {
		return bean;
	}

	public void setBean(B bean) {
		this.bean = bean;

		this.removeAllComponents();
		this.addComponent(layoutFactory.getLayout());

		if (fieldFactory == null) {
			fieldFactory = new DefaultBeanFieldGroupFieldFactory<B>(this);
		}

		fieldFactory.setBean(bean);
	}

	public void commit() {
		if (fieldFactory != null) {
			fieldFactory.commit();
		}
	}

	public void setItemDataSource(final Item newDataSource) {
	}

	protected void attachField(Object propertyId, Field field) {
		layoutFactory.attachField(propertyId, field);
	}
}

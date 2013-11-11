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

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;

public abstract class AdvancedPreviewBeanCustomForm<T> extends
		AdvancedPreviewBeanForm<T> {
	private static final long serialVersionUID = 1L;

	protected DynaFormLayout formLayout;

	public AdvancedPreviewBeanCustomForm(DynaFormLayout formLayout) {
		this.formLayout = formLayout;

		this.setFormLayoutFactory(formLayout);
		this.setFormFieldFactory(new DefaultCustomFormViewFieldFactory(
				formLayout) {
			private static final long serialVersionUID = 1L;

			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				return AdvancedPreviewBeanCustomForm.this.onCreateField(item,
						propertyId, uiContext);
			}
		});
	}

	abstract protected Field onCreateField(final Item item,
			final Object propertyId, final com.vaadin.ui.Component uiContext);
}

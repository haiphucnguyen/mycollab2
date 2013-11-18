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

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

/**
 * 
 * @author haiphucnguyen
 *
 */
public class DefaultCustomFormViewFieldFactory extends DefaultFieldFactory {
	private static Logger log = LoggerFactory
			.getLogger(DefaultFormViewFieldFactory.class);

	private static final long serialVersionUID = 1L;

	private DynaFormLayout formLayout;

	public DefaultCustomFormViewFieldFactory(DynaFormLayout formLayout) {
		this.formLayout = formLayout;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Field createField(final Item item, final Object propertyId,
			final com.vaadin.ui.Component uiContext) {

		if (formLayout.isVisibleProperty(propertyId)) {
			Field field = onCreateField(item, propertyId, uiContext);
			if (field == null) {
				final Object bean = ((BeanItem<Object>) item).getBean();

				try {
					final String propertyValue = BeanUtils.getProperty(bean,
							(String) propertyId);
					field = new FormViewField(propertyValue);
				} catch (final Exception e) {
					log.error("Error while get field value", e);
					field = new FormViewField("Error");
				}
			}

			return field;
		} else {
			return null;
		}

	}

	protected Field onCreateField(final Item item, final Object propertyId,
			final com.vaadin.ui.Component uiContext) {
		return null;
	}

}

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

import com.esofthead.mycollab.core.arguments.NotBindable;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public abstract class AbstractBeanFieldGroupViewFieldFactory<B> implements
		IBeanFieldGroupFieldFactory<B> {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(AbstractBeanFieldGroupViewFieldFactory.class);

	protected GenericBeanForm<B> attachForm;

	public AbstractBeanFieldGroupViewFieldFactory(GenericBeanForm<B> form) {
		this.attachForm = form;
	}

	@Override
	public void setBean(B bean) {
		Class<?> beanClass = bean.getClass();
		java.lang.reflect.Field[] fields = ClassUtils.getAllFields(beanClass);
		for (java.lang.reflect.Field field : fields) {
			Field<?> formField = onCreateField(field.getName());
			if (formField != null) {
				attachForm.attachField(field.getName(), formField);
			} else {
				if (field.getAnnotation(NotBindable.class) != null) {
					continue;
				} else {
					try {
						final String propertyValue = BeanUtils.getProperty(
								attachForm.getBean(), field.getName());
						formField = new FormViewField(propertyValue);
					} catch (Exception e) {
						log.error("Error while get field value", e);
						formField = new FormViewField("Error");
					}

					attachForm.attachField(field.getName(), formField);
				}
			}
		}
	}

	@Override
	public void commit() {

	}

	abstract protected Field<?> onCreateField(Object propertyId);

}

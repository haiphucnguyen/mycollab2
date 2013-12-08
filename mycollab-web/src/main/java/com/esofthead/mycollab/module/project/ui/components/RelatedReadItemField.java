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

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

public class RelatedReadItemField extends CustomField {

	private static final long serialVersionUID = 1L;
	private Object bean;

	public RelatedReadItemField(Object bean) {
		this.bean = bean;
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}

	@Override
	protected Component initContent() {
		try {
			final Integer typeid = (Integer) PropertyUtils.getProperty(
					RelatedReadItemField.this.bean, "typeid");
			if (typeid == null) {
				return new Label("");
			}

			final String type = (String) PropertyUtils
					.getProperty(bean, "type");
			if (type == null || type.equals("")) {
				return new Label("");
			}

			ButtonLink relatedLink = null;

			if ("Task".equals(type)) {
			} else if ("MileStone".equals(type)) {
			} else if ("Bug".equals(type)) {
			}

			if (relatedLink != null) {
				return relatedLink;
			} else {
				return new Label("");
			}

		} catch (Exception e) {
			return new Label("");
		}
	}
}

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
package com.esofthead.mycollab.reporting;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class RpParameterBuilder {
	private List<TableViewFieldDecorator> viewFields;

	public RpParameterBuilder(List<TableViewField> fields) {
		viewFields = new ArrayList<TableViewFieldDecorator>();

		for (TableViewField field : fields) {
			TableViewFieldDecorator fieldDecorator = new TableViewFieldDecorator(
					field);
			viewFields.add(fieldDecorator);
		}
	}

	public RpParameterBuilder inject(String fieldname, DRIExpression expression) {
		for (TableViewFieldDecorator decorator : viewFields) {
			if (fieldname.equals(decorator.getField())) {
				decorator.setFieldComponentExpression(expression);
			}
		}
		return this;
	}

	public List<TableViewFieldDecorator> getFields() {
		return viewFields;
	}

}

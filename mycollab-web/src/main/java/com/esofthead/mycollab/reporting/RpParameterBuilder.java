package com.esofthead.mycollab.reporting;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

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

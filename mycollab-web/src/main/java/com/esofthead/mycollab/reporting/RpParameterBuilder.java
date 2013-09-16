package com.esofthead.mycollab.reporting;

import java.util.ArrayList;
import java.util.List;

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

	public RpParameterBuilder inject(String fieldname,
			ColumnInjectionRenderer renderer) {
		for (TableViewFieldDecorator decorator : viewFields) {
			if (fieldname.equals(decorator.getField())) {
				decorator.setColumnRenderer(renderer);
			}
		}
		return this;
	}

	public List<TableViewFieldDecorator> getFields() {
		return viewFields;
	}

}

package com.esofthead.mycollab.reporting;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import com.esofthead.mycollab.reporting.SimpleColumnComponentBuilderMap.StringFieldUtilExpression;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class TableViewFieldDecorator extends TableViewField {
	private TableViewField tableField;

	private DRIExpression fieldComponentExpression;

	private ComponentBuilder componentBuilder;

	public TableViewFieldDecorator(TableViewField tableField) {
		this.tableField = tableField;
		this.fieldComponentExpression = new StringFieldUtilExpression(
				tableField.getField(), null);
	}

	@Override
	public String getDesc() {
		return tableField.getDesc();
	}

	@Override
	public void setDesc(String desc) {
		tableField.setDesc(desc);
	}

	@Override
	public String getField() {
		return tableField.getField();
	}

	@Override
	public void setField(String field) {
		tableField.setField(field);
	}

	@Override
	public int getDefaultWidth() {
		return tableField.getDefaultWidth();
	}

	@Override
	public void setDefaultWidth(int defaultWidth) {
		tableField.setDefaultWidth(defaultWidth);
	}

	public DRIExpression getFieldComponentExpression() {
		return fieldComponentExpression;
	}

	public void setFieldComponentExpression(
			DRIExpression fieldComponentExpression) {
		this.fieldComponentExpression = fieldComponentExpression;
	}

	public ComponentBuilder getComponentBuilder() {
		if (componentBuilder == null) {
			componentBuilder = cmp.text(
					new StringFieldUtilExpression(tableField.getField(), null))
					.setWidth(tableField.getDefaultWidth());
		}
		return componentBuilder;
	}

	public void setComponentBuilder(ComponentBuilder componentBuilder) {
		this.componentBuilder = componentBuilder;
	}
}

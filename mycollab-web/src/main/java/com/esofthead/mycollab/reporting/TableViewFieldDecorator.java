package com.esofthead.mycollab.reporting;

import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class TableViewFieldDecorator extends TableViewField {
	private TableViewField tableField;
	
	private ColumnInjectionRenderer columnRenderer;

	public TableViewFieldDecorator(TableViewField tableField) {
		this.tableField = tableField;
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

	public ColumnInjectionRenderer getColumnRenderer() {
		return columnRenderer;
	}

	public void setColumnRenderer(ColumnInjectionRenderer columnRenderer) {
		this.columnRenderer = columnRenderer;
	}
}

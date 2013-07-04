package com.esofthead.mycollab.vaadin.ui.table;

import com.esofthead.mycollab.vaadin.ui.UIConstants;

public class TableViewField {

	private String desc;

	private String field;

	private int defaultWidth;

	public TableViewField() {
		this("", "");
	}

	public TableViewField(String desc, String field) {
		this(desc, field, UIConstants.TABLE_M_LABEL_WIDTH);
	}

	public TableViewField(String desc, String field, int defaultWidth) {
		this.desc = desc;
		this.field = field;
		this.defaultWidth = defaultWidth;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getDefaultWidth() {
		return defaultWidth;
	}

	public void setDefaultWidth(int defaultWidth) {
		this.defaultWidth = defaultWidth;
	}
}

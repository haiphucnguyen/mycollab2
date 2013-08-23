package com.esofthead.mycollab.flex.plugin;

public class As3Field {
	private String fieldCls;

	private String fieldName;

	public As3Field(String fieldCls, String fieldName) {
		this.fieldCls = fieldCls;
		this.fieldName = fieldName;
	}

	public String getFieldCls() {
		return fieldCls;
	}

	public void setFieldCls(String fieldCls) {
		this.fieldCls = fieldCls;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}

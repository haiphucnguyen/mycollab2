package com.esofthead.mycollab.vaadin.data.util;

import java.util.List;

import com.vaadin.data.Validator;

public class FieldItem {
	private String fieldName;
	
	private String displayName;
	
	private List<Validator> validators;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<Validator> getValidators() {
		return validators;
	}

	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}

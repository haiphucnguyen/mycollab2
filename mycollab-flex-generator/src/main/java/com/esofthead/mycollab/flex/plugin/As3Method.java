package com.esofthead.mycollab.flex.plugin;

import java.util.ArrayList;
import java.util.List;

public class As3Method {
	private String methodName;

	private List<As3Field> fields;

	public As3Method(String methodName) {
		this.methodName = methodName;
		fields = new ArrayList<As3Field>();
	}

	public void addField(As3Field field) {
		fields.add(field);
	}

	public String getMethodName() {
		return methodName;
	}

	public List<As3Field> getFields() {
		return fields;
	}
}

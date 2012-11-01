package com.esofthead.mycollab.vaadin.mvp.ui;

public class Params {
	public static String ADD = "Add";
	
	public static String VIEW = "View";
	
	public static String EDIT = "Edit";
	
	private String action;

	private Object[] parameters;
	
	public Params() {
		this ("", null);
	}

	public Params(String action, Object[] parameters) {
		this.action = action;
		this.parameters = parameters;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}

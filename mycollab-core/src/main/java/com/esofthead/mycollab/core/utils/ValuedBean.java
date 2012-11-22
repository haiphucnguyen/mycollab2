package com.esofthead.mycollab.core.utils;


public class ValuedBean {
	private boolean selected = false;
	
	private Object extraData;

	public Object getExtraData() {
		return extraData;
	}

	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}

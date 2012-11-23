package com.esofthead.mycollab.core.utils;

public class ValuedBean implements Cloneable {
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

	public Object copy() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}

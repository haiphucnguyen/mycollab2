package com.esofthead.mycollab.core.utils;

import java.io.Serializable;

import org.springframework.flex.core.io.AmfIgnoreField;

public class ValuedBean implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	@AmfIgnoreField
	private boolean selected = false;

	@AmfIgnoreField
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

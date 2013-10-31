package com.esofthead.mycollab.form.view.builder.type;

public class StringDynaField extends AbstractDynaField {
	private int maxLength = Integer.MAX_VALUE;

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
}

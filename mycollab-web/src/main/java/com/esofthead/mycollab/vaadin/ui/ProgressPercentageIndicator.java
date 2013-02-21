package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Label;

public class ProgressPercentageIndicator extends Label {
	private static final long serialVersionUID = 1L;

	public ProgressPercentageIndicator(double val) {
		String perVal = val + "%";
		this.setValue(perVal);
	}
}

package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Label;

public class Separator extends Label {
	private static final long serialVersionUID = 1L;

	public Separator() {
		super("&nbsp;", Label.CONTENT_XHTML);
		this.setStyleName("separator");
	}
}

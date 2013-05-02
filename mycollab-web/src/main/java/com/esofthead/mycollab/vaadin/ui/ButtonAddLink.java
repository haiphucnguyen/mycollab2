package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.ThemeResource;

@SuppressWarnings("serial")
public class ButtonAddLink extends ButtonLink {

	public ButtonAddLink(String caption) {
		super(caption);
		this.setIcon(new ThemeResource("icons/16/addRecord.png"));
	}

	public ButtonAddLink(String caption, ClickListener listener) {
		super(caption, listener);
		this.setIcon(new ThemeResource("icons/16/addRecord.png"));
	}

}

package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Link;

public class EmailLink extends Link{
	private static final long serialVersionUID = 1L;

	public EmailLink(String email) {
		super();
		this.setResource(new ExternalResource("mailto:"
				+ email));
		this.setCaption(email);
	}
}

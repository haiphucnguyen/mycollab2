package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Link;

public class UrlLink extends Link {
	
	private static final long serialVersionUID = 1L;
	
	public UrlLink(String urlLink) {
		super();
		this.setResource(new ExternalResource(urlLink));
		this.setCaption(urlLink);
		this.setTargetName("_blank");
	}

}

package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Link;

@SuppressWarnings("serial")
public class SocialNetworkLink extends Link {

	public SocialNetworkLink(String caption, String linkAccount) {
		super();
		this.setResource(new ExternalResource(linkAccount));
		this.setCaption(caption);
		this.setTargetName("_blank");
	}
	
}

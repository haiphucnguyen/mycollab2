package com.esofthead.mycollab.shell.view;

import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class NoSubDomainExistedWindow extends Window {
	private static final long serialVersionUID = 1L;

	public NoSubDomainExistedWindow(String domain) {
		this.addComponent(new Label("There is no sudomain: " + domain
				+ ". Do you forgot your domain?"));
	}

}

package com.esofthead.mycollab.mobile;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class MobileUI extends UI {
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		Label l = new Label("A");
		this.setContent(l);
	}

}

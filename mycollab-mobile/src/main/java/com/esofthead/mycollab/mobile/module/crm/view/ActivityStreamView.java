package com.esofthead.mycollab.mobile.module.crm.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class ActivityStreamView extends NavigationView {
	private static final long serialVersionUID = 1L;

	public ActivityStreamView() {
		this.setContent(new Label("AAAA"));
		this.setLeftComponent(new Button("Back"));
	}
}

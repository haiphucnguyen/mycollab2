package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AddViewLayout2 extends CssLayout {
	private static final long serialVersionUID = 1L;

	private final HorizontalLayout header;
	private final Embedded iconEmbed;
	private final Label titleLbl;
	private final VerticalLayout body;

	public AddViewLayout2(final String title, final Resource icon) {
		// this.setSizeFull();
		setStyleName("addview-layout");

		header = new HorizontalLayout();
		header.setWidth("100%");
		header.setMargin(true, true, false, true);
		header.setStyleName("addview-layout-header");
		this.addComponent(header);

		iconEmbed = new Embedded();
		iconEmbed.setSource(icon);
		header.addComponent(iconEmbed);

		titleLbl = new Label(title);
		titleLbl.setStyleName("h1");
		header.addComponent(titleLbl);
		header.setExpandRatio(titleLbl, 1.0f);

		body = new VerticalLayout();
		body.setStyleName("addview-layout-body");
		this.addComponent(body);
	}

	public void addBody(final ComponentContainer body) {
		this.body.addComponent(body);
		this.body.setExpandRatio(body, 1.0f);
	}

	public void addControlButtons(final Component controlsBtn) {
		controlsBtn.addStyleName("control-buttons");
		body.addComponent(controlsBtn);
	}

	public void setTitle(final String title) {
		titleLbl.setValue(title);
	}
}

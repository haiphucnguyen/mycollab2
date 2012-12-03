package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class Depot extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private boolean isOpenned = true;

	protected HorizontalLayout header;

	protected Component content;

	public Depot(String title, Component component) {
		header = new HorizontalLayout();
		this.content = component;
		this.addComponent(header);

		Label headerLbl = new Label(title);
		headerLbl.setStyleName("h2-extra");
		header.addComponent(headerLbl);
		header.addListener(new LayoutClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				isOpenned = !isOpenned;
				if (isOpenned) {
					content.setHeight("100%");
					header.removeStyleName("collapsed");
				} else {
					content.setHeight("0px");
					header.addStyleName("collapsed");
				}
			}
		});

		CustomComponent customComp = new CustomComponent(component);
		customComp.setStyleName("depotContent");
		this.addComponent(customComp);
	}
}

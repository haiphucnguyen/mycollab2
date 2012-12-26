package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
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
		this.setStyleName("depotComp");
		header = new HorizontalLayout();
		header.setStyleName("depotHeader");
		this.content = component;
		this.addComponent(header);

		Label headerLbl = new Label(title);
		headerLbl.setStyleName("h2");
		header.addComponent(headerLbl);
		header.addListener(new LayoutClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				isOpenned = !isOpenned;
				if (isOpenned) {
					content.setHeight("100%");
					// header.removeStyleName("collapsed");
					Depot.this.removeStyleName("collapsed");
				} else {
					content.setHeight("0px");
					// header.addStyleName("collapsed");
					Depot.this.addStyleName("collapsed");
				}
			}
		});

		CustomComponent customComp = new CustomComponent(component);
		customComp.setWidth("100%");
		customComp.setStyleName("depotContent");
		this.addComponent(customComp);
		this.setComponentAlignment(customComp, Alignment.MIDDLE_LEFT);
	}
}

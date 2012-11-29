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

	private HorizontalLayout header;

	private CustomComponent content;

	public Depot(String title, Component component) {
		header = new HorizontalLayout();
		this.addComponent(header);

		header.addComponent(new Label(title));
		header.addListener(new LayoutClickListener() {

			@Override
			public void layoutClick(LayoutClickEvent event) {
				isOpenned = !isOpenned;
				if (isOpenned) {
					content.setSizeUndefined();
				} else {
					content.setHeight("0px");
				}
			}
		});

		content = new CustomComponent(component);
		this.addComponent(content);
	}
}

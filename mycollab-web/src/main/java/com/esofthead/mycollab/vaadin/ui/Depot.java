package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class Depot extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private boolean isOpenned = true;
	protected HorizontalLayout header;
	protected final Label headerLbl;
	protected ComponentContainer headerContent;
	protected ComponentContainer bodyContent;

	public Depot(final String title, final ComponentContainer component) {
		this(title, null, component);
	}

	public Depot(final String title, final ComponentContainer headerElement,
			final ComponentContainer component) {
		this(title, headerElement, component, "100%", "250px");
	}

	public Depot(final String title, final ComponentContainer headerElement,
			final ComponentContainer component, final String headerWidth,
			final String headerLeftWidth) {
		setStyleName("depotComp");
		header = new HorizontalLayout();
		header.setStyleName("depotHeader");
		header.setWidth(headerWidth);
		headerContent = headerElement;
		bodyContent = component;
		// this.headerContent = header;
		this.addComponent(header);

		final CssLayout headerLeft = new CssLayout();
		headerLbl = new Label(title);
		headerLbl.setStyleName("h2");
		headerLeft.addComponent(headerLbl);
		headerLeft.setStyleName("depot-title");
		headerLeft.setWidth(headerLeftWidth);
		headerLeft.addListener(new LayoutClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void layoutClick(final LayoutClickEvent event) {
				isOpenned = !isOpenned;
				if (isOpenned) {
					bodyContent.setHeight("100%");
					Depot.this.removeStyleName("collapsed");
				} else {
					bodyContent.setHeight("0px");
					Depot.this.addStyleName("collapsed");
				}
			}
		});

		final HorizontalLayout headerRight = new HorizontalLayout();
		headerRight.setStyleName("header-elements");
		headerRight.setSizeFull();

		if (headerElement != null) {
			headerRight.addComponent(headerElement);
			headerRight.setComponentAlignment(headerElement,
					Alignment.TOP_RIGHT);
			header.addComponent(headerLeft);
			header.addComponent(headerRight);
			header.setExpandRatio(headerRight, 1.0f);
		} else {
			final VerticalLayout newHeaderLeft = new VerticalLayout();
			newHeaderLeft.addComponent(headerLbl);
			newHeaderLeft.setStyleName("depot-title");
			newHeaderLeft.setWidth("100%");
			newHeaderLeft.addListener(new LayoutClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void layoutClick(final LayoutClickEvent event) {
					isOpenned = !isOpenned;
					if (isOpenned) {
						bodyContent.setHeight("100%");
						Depot.this.removeStyleName("collapsed");
					} else {
						bodyContent.setHeight("0px");
						Depot.this.addStyleName("collapsed");
					}
				}
			});
			header.addComponent(newHeaderLeft);
		}

		final CustomComponent customComp = new CustomComponent(component);
		customComp.setWidth("100%");
		customComp.setStyleName("depotContent");
		this.addComponent(customComp);
		this.setComponentAlignment(customComp, Alignment.TOP_CENTER);
	}

	public Depot(final String title, final ComponentContainer component,
			final String headerWidth) {
		this(title, null, component, headerWidth, "250px");
	}

	public void setTitle(final String title) {
		headerLbl.setValue(title);
	}
}

package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
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
		this.setStyleName("depotComp");
		this.header = new HorizontalLayout();
		this.header.setStyleName("depotHeader");
		this.header.setWidth("100%");
		this.bodyContent = component;
		if (headerElement != null) {
			this.headerContent = headerElement;
		} else {
			this.headerContent = new HorizontalLayout();
			((HorizontalLayout) this.headerContent).setSpacing(true);
		}

		this.headerContent.setStyleName("header-elements");
		this.headerContent.setSizeUndefined();

		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.addComponent(this.header);
		headerWrapper.setStyleName("header-wrapper");
		headerWrapper.setWidth(headerWidth);
		this.addComponent(headerWrapper);

		final VerticalLayout headerLeft = new VerticalLayout();
		this.headerLbl = new Label(title);
		this.headerLbl.setStyleName("h2");
		this.headerLbl.setWidth("100%");
		headerLeft.addComponent(this.headerLbl);
		headerLeft.setStyleName("depot-title");
		headerLeft.addListener(new LayoutClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void layoutClick(final LayoutClickEvent event) {
				Depot.this.isOpenned = !Depot.this.isOpenned;
				if (Depot.this.isOpenned) {
					Depot.this.bodyContent.setHeight("100%");
					Depot.this.removeStyleName("collapsed");
				} else {
					Depot.this.bodyContent.setHeight("0px");
					Depot.this.addStyleName("collapsed");
				}
			}
		});
		this.header.addComponent(headerLeft);
		this.header.setComponentAlignment(headerLeft, Alignment.MIDDLE_LEFT);
		this.header.addComponent(this.headerContent);
		this.header.setComponentAlignment(this.headerContent,
				Alignment.MIDDLE_LEFT);
		this.header.setExpandRatio(headerLeft, 1.0f);

		final CustomComponent customComp = new CustomComponent(this.bodyContent);
		customComp.setWidth("100%");
		this.bodyContent.addStyleName("depotContent");

		this.addComponent(customComp);
		this.setComponentAlignment(customComp, Alignment.TOP_CENTER);
	}

	public Depot(final String title, final ComponentContainer component,
			final String headerWidth) {
		this(title, null, component, headerWidth, "250px");
	}

	public void addHeaderElement(final Component component) {
		if (component != null) {
			this.headerContent.addComponent(component);
		}
	}

	public void setContentBorder(final boolean hasBorder) {
		if (hasBorder) {
			this.bodyContent.addStyleName("bordered");
		} else {
			this.bodyContent.removeStyleName("bordered");
		}
	}

	public void setTitle(final String title) {
		this.headerLbl.setValue(title);
	}
}

package com.esofthead.mycollab.module.crm.ui.components;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class AddViewLayout extends CustomLayout {
	private static final long serialVersionUID = 1L;
	private final HorizontalLayout Header;

	public AddViewLayout() {
		super("addView");
		// this.setSizeUndefined();
		this.Header = new HorizontalLayout();
		Label headerName = new Label("Add New");
		headerName.setStyleName("headerName");
		this.addComponent(headerName);
		this.Header.setStyleName("addViewHeader");
		this.addComponent(Header, "addViewHeader");
	}

	public AddViewLayout(String section) {
		super("addView");
		String sectionName = section.substring(0, 1).toUpperCase()
				+ section.substring(1).toLowerCase();
		// this.setSizeUndefined();
		this.Header = new HorizontalLayout();
		this.Header.setStyleName("addViewHeader");
		this.Header.addStyleName("create" + sectionName + "Header");
		Embedded icon = new Embedded();
		icon.setSource(new ThemeResource("icons/48/crm/"
				+ section.toLowerCase() + ".png"));
		this.Header.addComponent(icon);
		Label headerName = new Label("Create " + sectionName);
		headerName.setStyleName("headerName");
		this.Header.addComponent(headerName);
		this.addComponent(Header, "addViewHeader");
	}

	public void addTopControls(ComponentContainer topControls) {
		this.addComponent(topControls, "addViewTopControls");
	}

	public void addBottomControls(ComponentContainer bottomControls) {
		this.addComponent(bottomControls, "addViewBottomControls");
	}

	public void addBody(ComponentContainer body) {
		this.addComponent(body, "addViewBody");
	}
}

package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.Resource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;

public class AddViewLayout extends CustomLayout {

	private static final long serialVersionUID = 1L;

	private final Label titleLbl;
	private final Embedded icon;

	public AddViewLayout(final String title, final Resource resource) {
		super("addView");

		icon = new Embedded();
		icon.setSource(resource);
		this.addComponent(icon, "addViewHeaderIcon");
		titleLbl = new Label();
		titleLbl.setStyleName("headerName");
		titleLbl.setSizeUndefined();
		titleLbl.setImmediate(true);

		this.addComponent(titleLbl, "addViewHeaderTitle");

		if (title == null) {
			setTitle("Undefined");
		} else {
			setTitle(title);
		}
	}

	public void addBody(final ComponentContainer body) {
		this.addComponent(body, "addViewBody");
	}

	public void addBottomControls(final ComponentContainer bottomControls) {
		this.addComponent(bottomControls, "addViewBottomControls");
	}

	public void addHeaderRight(final ComponentContainer headerRight) {
		this.addComponent(headerRight, "addViewHeaderRight");
	}

	public void addTitleStyleName(final String styleName) {
		titleLbl.addStyleName(styleName);
	}

	public void addTopControls(final ComponentContainer topControls) {
		this.addComponent(topControls, "addViewTopControls");
	}

	public void setTitle(final String title) {
		titleLbl.setValue(title);
	}

}

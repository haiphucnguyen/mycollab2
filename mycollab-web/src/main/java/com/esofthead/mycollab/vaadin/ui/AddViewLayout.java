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

		this.icon = new Embedded();
		this.setTitleIcon(resource);
		this.addComponent(this.icon, "addViewHeaderIcon");
		this.titleLbl = new Label();
		this.titleLbl.setStyleName("headerName");
		this.titleLbl.setSizeUndefined();
		this.titleLbl.setImmediate(true);

		this.addComponent(this.titleLbl, "addViewHeaderTitle");

		if (title == null) {
			if (resource != null) {
				this.setTitle("Undefined");
			}
		} else {
			this.setTitle(title);
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
		this.titleLbl.addStyleName(styleName);
	}

	public void addTopControls(final ComponentContainer topControls) {
		this.addComponent(topControls, "addViewTopControls");
	}

	public void setTitle(final String title) {
		this.titleLbl.setValue(title);
	}

	public void setTitleIcon(final Resource resource) {
		if (resource != null) {
			this.icon.setSource(resource);
		}
	}

}

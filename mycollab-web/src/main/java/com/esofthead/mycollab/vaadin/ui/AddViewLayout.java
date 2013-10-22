package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.web.CustomLayoutExt;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class AddViewLayout extends CustomLayoutExt {

	private static final long serialVersionUID = 1L;

	private final Label titleLbl;
	private final Embedded icon;
	private final HorizontalLayout header;

	public AddViewLayout(final String title, final Resource resource) {
		super("addView");

		this.header = new HorizontalLayout();
		this.header.setWidth("100%");
		this.header.setSpacing(true);

		this.icon = new Embedded();
		this.setTitleIcon(resource);
		this.header.addComponent(this.icon);
		this.titleLbl = new Label();
		this.titleLbl.setStyleName("headerName");
		this.titleLbl.setImmediate(true);

		this.header.addComponent(this.titleLbl);
		this.header.setExpandRatio(titleLbl, 1.0f);

		if (title == null) {
			if (resource != null) {
				this.setTitle("Undefined");
			}
		} else {
			this.setTitle(title);
		}

		this.addComponent(this.header, "addViewHeader");
	}

	public void addBody(final ComponentContainer body) {
		this.addComponent(body, "addViewBody");
	}

	public void addBottomControls(final ComponentContainer bottomControls) {
		this.addComponent(bottomControls, "addViewBottomControls");
	}

	public void addHeaderRight(final ComponentContainer headerRight) {
		this.header.addComponent(headerRight);
	}

	public void addTitleStyleName(final String styleName) {
		this.titleLbl.addStyleName(styleName);
	}

	public void setTitleStyleName(final String styleName) {
		this.titleLbl.setStyleName(styleName);
	}

	public void removeTitleStyleName(final String styleName) {
		this.titleLbl.removeStyleName(styleName);
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

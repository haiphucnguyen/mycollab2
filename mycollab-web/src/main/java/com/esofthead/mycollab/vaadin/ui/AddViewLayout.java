package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;

public class AddViewLayout extends CustomLayout {

	private static final long serialVersionUID = 1L;

	private final Label titleLbl;
	private final Embedded icon;

	public AddViewLayout(String title, ThemeResource resource) {
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
			this.setTitle("Undefined");
		} else {
			this.setTitle(title);
		}
	}

	public void setTitle(String title) {
		this.titleLbl.setValue(title);
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

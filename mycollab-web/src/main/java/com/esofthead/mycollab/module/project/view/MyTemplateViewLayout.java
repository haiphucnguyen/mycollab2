package com.esofthead.mycollab.module.project.view;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class MyTemplateViewLayout extends VerticalLayout {
	public MyTemplateViewLayout() {
		ComponentContainer header = constructHeader();
		ComponentContainer body = constructBody();

		this.addComponent(header);
		this.addComponent(body);
	}

	abstract protected ComponentContainer constructHeader();

	abstract protected ComponentContainer constructBody();
}

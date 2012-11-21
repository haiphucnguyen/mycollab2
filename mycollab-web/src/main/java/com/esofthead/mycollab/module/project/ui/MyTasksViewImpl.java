package com.esofthead.mycollab.module.project.ui;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class MyTasksViewImpl extends AbstractView implements MyTasksView {

	public MyTasksViewImpl() {
		this.addComponent(new Label("My Tasks"));
	}

}

package com.esofthead.mycollab.module.project.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@Component
public class MyTasksViewImpl extends AbstractView implements MyTasksView {

	public MyTasksViewImpl() {
		this.addComponent(new Label("My Tasks"));
	}

}

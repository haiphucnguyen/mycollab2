package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ProjectTaskViewImpl extends AbstractView implements
		ProjectTaskView {

	public ProjectTaskViewImpl() {
		super();
		this.addComponent(new Label("Tasks"));
	}
}

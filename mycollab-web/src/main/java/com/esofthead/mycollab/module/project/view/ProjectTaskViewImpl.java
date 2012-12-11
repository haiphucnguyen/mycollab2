package com.esofthead.mycollab.module.project.view;

import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ProjectTaskViewImpl extends ProjectAbstractView implements
		ProjectTaskView {

	public ProjectTaskViewImpl() {
		super();
		this.addComponent(new Label("Tasks"));
	}
}

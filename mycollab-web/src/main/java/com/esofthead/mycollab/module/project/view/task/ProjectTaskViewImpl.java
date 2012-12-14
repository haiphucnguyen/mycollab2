package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.view.ProjectAbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ProjectTaskViewImpl extends ProjectAbstractView implements
		ProjectTaskView {

	public ProjectTaskViewImpl() {
		super();
		this.addComponent(new Label("Tasks"));
	}
}

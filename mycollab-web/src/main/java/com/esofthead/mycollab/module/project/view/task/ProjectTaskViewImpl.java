package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.view.ProjectAbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectTaskViewImpl extends ProjectAbstractView implements
		ProjectTaskView {

	public ProjectTaskViewImpl() {
		super();
		this.addComponent(new Label("Tasks"));
	}
}

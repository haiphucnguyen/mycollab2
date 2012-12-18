package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.view.ProjectAbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectMilestoneViewImpl extends ProjectAbstractView implements
		ProjectMilestoneView {

	public ProjectMilestoneViewImpl() {
		super();
		this.addComponent(new Label("Milestones"));
	}
}

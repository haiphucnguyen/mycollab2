package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.view.ProjectAbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ProjectMilestoneViewImpl extends ProjectAbstractView implements
		ProjectMilestoneView {

	public ProjectMilestoneViewImpl() {
		super();
		this.addComponent(new Label("Milestones"));
	}
}

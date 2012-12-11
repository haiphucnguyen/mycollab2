package com.esofthead.mycollab.module.project.view;

import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ProjectMilestoneViewImpl extends ProjectAbstractView implements
		ProjectMilestoneView {

	public ProjectMilestoneViewImpl() {
		super();
		this.addComponent(new Label("Milestones"));
	}
}

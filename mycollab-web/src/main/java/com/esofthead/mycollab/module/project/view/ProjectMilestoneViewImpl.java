package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ProjectMilestoneViewImpl extends AbstractView implements
		ProjectMilestoneView {

	public ProjectMilestoneViewImpl() {
		super();
		this.addComponent(new Label("Milestones"));
	}
}

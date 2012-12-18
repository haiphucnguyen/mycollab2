package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ProjectMilestonePresenter extends
		AbstractPresenter<ProjectMilestoneView> {
	private static final long serialVersionUID = 1L;

	public ProjectMilestonePresenter() {
		super(ProjectMilestoneView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectView = (ProjectView) container;
		projectView.gotoSubView("Milestones");
	}
}

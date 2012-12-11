package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ProjectMilestonePresenter extends AbstractPresenter {
	private static final long serialVersionUID = 1L;

	private ProjectMilestoneView view;

	public ProjectMilestonePresenter(ProjectMilestoneView view) {
		this.view = view;
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectView = (ProjectView) container;
		ProjectMilestoneView milestonesView = (ProjectMilestoneView) projectView
				.gotoSubView("Milestones");
	}

}

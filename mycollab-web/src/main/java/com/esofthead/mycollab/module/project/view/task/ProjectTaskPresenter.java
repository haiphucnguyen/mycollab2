package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ProjectTaskPresenter extends AbstractPresenter<ProjectTaskView> {
	private static final long serialVersionUID = 1L;

	public ProjectTaskPresenter() {
		super(ProjectTaskView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		view = (ProjectTaskView) projectViewContainer.gotoSubView("Tasks");
	}
}

package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ProjectMessagePresenter extends AbstractPresenter {
	private static final long serialVersionUID = 1L;

	private ProjectMessageView view;

	public ProjectMessagePresenter(ProjectMessageView view) {
		this.view = view;
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		ProjectMessageView messageView = (ProjectMessageView) projectViewContainer
				.gotoSubView("Messages");
	}

}

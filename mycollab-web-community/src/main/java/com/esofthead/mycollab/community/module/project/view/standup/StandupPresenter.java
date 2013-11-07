package com.esofthead.mycollab.community.module.project.view.standup;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.standup.IStandupContainer;
import com.esofthead.mycollab.module.project.view.standup.IStandupPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class StandupPresenter extends AbstractPresenter<IStandupContainer>
		implements IStandupPresenter {
	private static final long serialVersionUID = 1L;

	public StandupPresenter() {
		super(IStandupContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("StandUp");

	}
}

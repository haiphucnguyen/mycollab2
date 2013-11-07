package com.esofthead.mycollab.community.module.project.view.time;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.time.ITimeTrackingContainer;
import com.esofthead.mycollab.module.project.view.time.ITimeTrackingPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class TimeTrackingPresenter extends
		AbstractPresenter<ITimeTrackingContainer> implements
		ITimeTrackingPresenter {
	private static final long serialVersionUID = 1L;

	public TimeTrackingPresenter() {
		super(ITimeTrackingContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Time");
	}
}

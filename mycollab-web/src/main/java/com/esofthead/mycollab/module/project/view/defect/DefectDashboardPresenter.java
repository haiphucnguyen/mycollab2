package com.esofthead.mycollab.module.project.view.defect;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class DefectDashboardPresenter extends
		AbstractPresenter<DefectDashboardView> {
	private static final long serialVersionUID = 1L;

	public DefectDashboardPresenter() {
		super(DefectDashboardView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		view = (DefectDashboardView) projectViewContainer
				.gotoSubView("Defects");
	}

}

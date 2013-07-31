package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

public class BugDashboardPresenter extends AbstractPresenter<BugDashboardView> {

	private static final long serialVersionUID = 1L;

	public BugDashboardPresenter() {
		super(BugDashboardView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		TrackerContainer trackerContainer = (TrackerContainer) container;
		trackerContainer.gotoSubView("Dashboard");

		view.displayDashboard();

		ProjectBreadcrumb breadcrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		breadcrumb.gotoBugDashboard();
	}
}

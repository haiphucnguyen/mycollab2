package com.esofthead.mycollab.module.project.view.time;

import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

public class TimeTrackingListPresenter extends
		AbstractPresenter<TimeTrackingListView> {
	private static final long serialVersionUID = 1L;

	public TimeTrackingListPresenter() {
		super(TimeTrackingListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		TimeTrackingContainer timeContainer = (TimeTrackingContainer) container;
		timeContainer.removeAllComponents();
		timeContainer.addComponent(view.getWidget());
		view.setSearchCriteria((ItemTimeLoggingSearchCriteria) data.getParams());

		ProjectBreadcrumb breadCrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		breadCrumb.gotoTimeTrackingList();
	}
}

package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd
 * @since 2.0
 * 
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
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
				.getCacheComponent(ProjectBreadcrumb.class);
		breadCrumb.gotoTimeTrackingList();
	}
}

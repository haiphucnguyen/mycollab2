package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListCommand;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

public class StandupListPresenter extends AbstractPresenter<StandupListView>
		implements ListCommand<StandupReportSearchCriteria> {
	private static final long serialVersionUID = 1L;

	public StandupListPresenter() {
		super(StandupListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		StandupContainer standupContainer = (StandupContainer) container;
		standupContainer.removeAllComponents();
		standupContainer.addComponent(cacheableView.getWidget());
		doSearch((StandupReportSearchCriteria) data.getParams());

		ProjectBreadcrumb breadCrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		breadCrumb.gotoStandupList();
	}

	public void doSearch(StandupReportSearchCriteria searchCriteria) {
		cacheableView.setSearchCriteria(searchCriteria);
	}

}

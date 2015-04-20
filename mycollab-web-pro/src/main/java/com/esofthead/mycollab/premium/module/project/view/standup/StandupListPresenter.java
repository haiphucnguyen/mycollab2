package com.esofthead.mycollab.premium.module.project.view.standup;

import java.util.Date;

import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.*;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
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
		standupContainer.addComponent(view.getWidget());
		StandupReportSearchCriteria searchCriteria = (StandupReportSearchCriteria) data
				.getParams();
		doSearch(searchCriteria);

		Date showDate = null;
		if (searchCriteria.getOnDate() != null) {
			showDate = searchCriteria.getOnDate().getValue();
		}
		ProjectBreadcrumb breadCrumb = ViewManager
				.getCacheComponent(ProjectBreadcrumb.class);
		breadCrumb.gotoStandupList(showDate);
	}

	public void doSearch(StandupReportSearchCriteria searchCriteria) {
		view.setSearchCriteria(searchCriteria);
	}

}

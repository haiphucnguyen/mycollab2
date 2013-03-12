/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class MilestoneListPresenter extends
		AbstractPresenter<MilestoneListView> implements
		ListPresenter<MilestoneSearchCriteria> {
	private static final long serialVersionUID = 1L;

	public MilestoneListPresenter() {
		super(MilestoneListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		MilestoneContainer milestoneContainer = (MilestoneContainer) container;
		milestoneContainer.removeAllComponents();
		milestoneContainer.addComponent(view.getWidget());
		doSearch((MilestoneSearchCriteria) data.getParams());

		ProjectBreadcrumb breadcrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		breadcrumb.gotoMilestoneList();
	}

	@Override
	public void doSearch(MilestoneSearchCriteria searchCriteria) {
		MilestoneService milestoneService = AppContext
				.getSpringBean(MilestoneService.class);
		List<SimpleMilestone> milestones = milestoneService
				.findPagableListByCriteria(new SearchRequest<MilestoneSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));
		view.displayMilestones(milestones);
	}

}

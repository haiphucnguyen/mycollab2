/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectDashboardPresenter extends
		AbstractPresenter<ProjectDashboardContainer> {
	private static final long serialVersionUID = 1L;

	public ProjectDashboardPresenter() {
		super(ProjectDashboardContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Dashboard");

		view.removeAllComponents();

		ProjectBreadcrumb breadcrumb = ViewManager
				.getView(ProjectBreadcrumb.class);

		if (data instanceof ProjectScreenData.Edit) {
			if (CurrentProjectVariables
					.canWrite(ProjectRolePermissionCollections.PROJECT)) {
				ProjectAddPresenter presenter = PresenterResolver
						.getPresenter(ProjectAddPresenter.class);
				presenter.go(view, data);
				breadcrumb.gotoProjectEdit();
			} else {
				MessageConstants.showMessagePermissionAlert();
			}
		} else {
			if (CurrentProjectVariables
					.canRead(ProjectRolePermissionCollections.PROJECT)) {
				ProjectSummaryPresenter presenter = PresenterResolver
						.getPresenter(ProjectSummaryPresenter.class);
				presenter.go(view, data);
				breadcrumb.gotoProjectDashboard();
			} else {
				MessageConstants.showMessagePermissionAlert();
			}
		}
	}
}

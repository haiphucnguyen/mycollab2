/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectMemberListPresenter extends
		AbstractPresenter<ProjectMemberListView> {

	private static final long serialVersionUID = 1L;

	public ProjectMemberListPresenter() {
		super(ProjectMemberListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectMemberContainer userGroupContainer = (ProjectMemberContainer) container;
		userGroupContainer.removeAllComponents();
		userGroupContainer.addComponent(view.getWidget());
		ProjectMemberSearchCriteria criteria = null;
		if (data == null) {
			criteria = new ProjectMemberSearchCriteria();
			criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
		} else {
			criteria = (ProjectMemberSearchCriteria) data.getParams();
		}

		view.setSearchCriteria(criteria);

		ProjectBreadcrumb breadCrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		breadCrumb.gotoUserList();
	}
}

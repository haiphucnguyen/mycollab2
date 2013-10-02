/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.ProjectMemberScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectRoleScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class UserSettingPresenter extends AbstractPresenter<UserSettingView> {
	private static final long serialVersionUID = 1L;

	public UserSettingPresenter() {
		super(UserSettingView.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Users & Settings");

		AbstractPresenter<?> presenter;
		if (ClassUtils.instanceOf(data, ProjectRoleScreenData.Search.class,
				ProjectRoleScreenData.Add.class,
				ProjectRoleScreenData.Read.class)) {
			view.gotoSubView("Roles");
			presenter = PresenterResolver
					.getPresenter(ProjectRolePresenter.class);
		} else if (ClassUtils.instanceOf(data,
				ProjectMemberScreenData.Read.class,
				ProjectMemberScreenData.Search.class,
				ProjectMemberScreenData.Add.class)) {
			view.gotoSubView("Users");
			presenter = PresenterResolver
					.getPresenter(ProjectUserPresenter.class);
		} else {
			throw new MyCollabException("No support screen data: " + data);
		}

		presenter.go(view, data);
	}
}

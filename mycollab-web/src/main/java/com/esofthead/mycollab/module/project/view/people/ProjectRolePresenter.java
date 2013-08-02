/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.view.parameters.ProjectRoleScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectRolePresenter extends
		AbstractPresenter<ProjectRoleContainer> {
	private static final long serialVersionUID = 1L;

	public ProjectRolePresenter() {
		super(ProjectRoleContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		AbstractPresenter<?> presenter = null;

		if (data instanceof ProjectRoleScreenData.Search) {
			presenter = PresenterResolver
					.getPresenter(ProjectRoleListPresenter.class);
		} else if (data instanceof ProjectRoleScreenData.Add) {
			presenter = PresenterResolver
					.getPresenter(ProjectRoleAddPresenter.class);
		} else if (data instanceof ProjectRoleScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(ProjectRoleReadPresenter.class);
		} else {
			throw new MyCollabException("Can not handle data " + data);
		}

		presenter.go(view, data);
	}
}

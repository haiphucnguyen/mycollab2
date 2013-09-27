/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.view.parameters.ProjectMemberScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectUserPresenter extends
		AbstractPresenter<ProjectUserContainer> {
	private static final long serialVersionUID = 1L;

	public ProjectUserPresenter() {
		super(ProjectUserContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		view.removeAllComponents();

		AbstractPresenter<?> presenter = null;

		if (data instanceof ProjectMemberScreenData.Add) {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberAddPresenter.class);
		} else if (data instanceof ProjectMemberScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberReadPresenter.class);
		} else if (data instanceof ProjectMemberScreenData.Search) {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberListPresenter.class);
		} else {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberListPresenter.class);
		}

		presenter.go(view, data);
	}
}

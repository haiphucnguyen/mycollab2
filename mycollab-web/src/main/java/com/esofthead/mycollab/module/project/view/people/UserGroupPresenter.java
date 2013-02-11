/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.ProjectRoleScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class UserGroupPresenter extends AbstractPresenter<UserGroupView> {
	private static final long serialVersionUID = 1L;

	public UserGroupPresenter() {
        super(UserGroupView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        projectViewContainer.gotoSubView("Users & Group");
        
        AbstractPresenter<?> presenter;
        if (data instanceof ProjectRoleScreenData.Add) {
        	presenter = PresenterResolver.getPresenter(ProjectRolePresenter.class);
        } else {
        	presenter = PresenterResolver.getPresenter(ProjectMemberPresenter.class);
        }
        
        presenter.go(view, data);
    }
}

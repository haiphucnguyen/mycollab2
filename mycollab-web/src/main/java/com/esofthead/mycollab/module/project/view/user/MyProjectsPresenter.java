/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.view.UserDashboardView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class MyProjectsPresenter extends AbstractPresenter<MyProjectsContainer> {

    public MyProjectsPresenter() {
        super(MyProjectsContainer.class);
    }
    
    @Override
    public void go(ComponentContainer container, ScreenData<?> data) {
        super.go(container, data, false);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        UserDashboardView projectViewContainer = (UserDashboardView) container;
        projectViewContainer.gotoSubView("Risks");

        view.removeAllComponents();

        if (data instanceof ScreenData.Search) {
            MyProjectsListPresenter presenter = PresenterResolver
                    .getPresenter(MyProjectsListPresenter.class);
            presenter.go(view, data);

        } else if (data instanceof ScreenData.Add) {
            ProjectAddPresenter presenter = PresenterResolver
                    .getPresenter(ProjectAddPresenter.class);
            presenter.go(view, data);
        } 
    }
}

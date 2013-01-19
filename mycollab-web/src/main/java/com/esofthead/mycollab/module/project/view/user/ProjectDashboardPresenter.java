/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectDashboardPresenter extends AbstractPresenter<ProjectDashboardContainer> {
    
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
        
        ProjectBreadcrumb breadcrumb = ViewManager.getView(ProjectBreadcrumb.class);
        
        if (data instanceof ScreenData.Add
                || data instanceof ScreenData.Edit) {
            ProjectEditPresenter presenter = PresenterResolver.getPresenter(ProjectEditPresenter.class);
            presenter.go(view, data);
            breadcrumb.gotoProjectEdit();
        } else {
            ProjectSummaryPresenter presenter = PresenterResolver.getPresenter(ProjectSummaryPresenter.class);
            presenter.go(view, data);
            breadcrumb.gotoProjectDashboard();
        }
    }
}

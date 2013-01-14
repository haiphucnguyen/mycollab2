package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ProjectSummaryPresenter extends AbstractPresenter<ProjectSummaryView> {
    
    public ProjectSummaryPresenter() {
        super(ProjectSummaryView.class);
    }
    
    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectDashboardContainer projectViewContainer = (ProjectDashboardContainer) container;
        projectViewContainer.removeAllComponents();
        projectViewContainer.addComponent(view.getWidget());
        view.displayDashboard();
    }
}

package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.view.reports.IReportContainer;
import com.esofthead.mycollab.module.project.view.reports.IReportPresenter;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardContainer;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class ReportPresenter extends AbstractPresenter<IReportContainer> implements IReportPresenter {
    public ReportPresenter() {
        super(IReportContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectDashboardContainer projectViewContainer = (ProjectDashboardContainer) container;
        projectViewContainer.removeAllComponents();
        projectViewContainer.addComponent(view.getWidget());

        ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
        breadcrumb.gotoReportConsole();
    }
}

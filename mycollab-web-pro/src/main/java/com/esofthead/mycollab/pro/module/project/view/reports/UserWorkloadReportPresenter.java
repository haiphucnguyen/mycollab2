package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.view.reports.IReportContainer;
import com.esofthead.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public class UserWorkloadReportPresenter extends AbstractPresenter<UserWorkloadReportView> {
    public UserWorkloadReportPresenter() {
        super(UserWorkloadReportView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        IReportContainer reportContainer = (IReportContainer) container;
        reportContainer.addView(view);
        view.display();

        ReportBreadcrumb breadCrumb = ViewManager.getCacheComponent(ReportBreadcrumb.class);
        breadCrumb.gotoUserWorkloadReport();
    }
}

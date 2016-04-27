package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.view.reports.IReportContainer;
import com.esofthead.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class TimeTrackingPresenter extends AbstractPresenter<TimeTrackingView> {
    private static final long serialVersionUID = 1L;

    public TimeTrackingPresenter() {
        super(TimeTrackingView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        IReportContainer projectModule = (IReportContainer) container;
        projectModule.addView(view);
        view.display();

        ReportBreadcrumb breadCrumb = ViewManager.getCacheComponent(ReportBreadcrumb.class);
        breadCrumb.gotoTimesheetReport();
    }
}

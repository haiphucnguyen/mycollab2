package com.mycollab.pro.module.project.view.reports;

import com.mycollab.module.project.view.reports.IReportContainer;
import com.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

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
    protected void onGo(HasComponents container, ScreenData<?> data) {
        IReportContainer projectModule = (IReportContainer) container;
        projectModule.addView(view);
        view.display();

        ReportBreadcrumb breadCrumb = ViewManager.INSTANCE.getCacheComponent(ReportBreadcrumb.class);
        breadCrumb.gotoTimesheetReport();
    }
}

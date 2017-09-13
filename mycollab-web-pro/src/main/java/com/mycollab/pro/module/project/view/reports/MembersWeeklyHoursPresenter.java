package com.mycollab.pro.module.project.view.reports;

import com.mycollab.module.project.view.reports.IReportContainer;
import com.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public class MembersWeeklyHoursPresenter extends AbstractPresenter<MembersWeeklyHoursView> {
    public MembersWeeklyHoursPresenter() {
        super(MembersWeeklyHoursView.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        IReportContainer reportContainer = (IReportContainer) container;
        reportContainer.addView(view);
        view.display();

        ReportBreadcrumb breadCrumb = ViewManager.INSTANCE.getCacheComponent(ReportBreadcrumb.class);
        breadCrumb.gotoWeeklyTimingReport();
    }
}

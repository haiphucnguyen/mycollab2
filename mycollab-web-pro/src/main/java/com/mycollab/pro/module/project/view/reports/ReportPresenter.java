package com.mycollab.pro.module.project.view.reports;

import com.mycollab.module.project.view.ProjectModule;
import com.mycollab.module.project.view.parameters.ReportScreenData;
import com.mycollab.module.project.view.parameters.StandupScreenData;
import com.mycollab.module.project.view.reports.IReportContainer;
import com.mycollab.module.project.view.reports.IReportPresenter;
import com.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class ReportPresenter extends AbstractPresenter<IReportContainer> implements IReportPresenter {
    public ReportPresenter() {
        super(IReportContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectModule projectModule = (ProjectModule) container;
        projectModule.removeAllComponents();
        projectModule.addComponent(view);

        if (data instanceof StandupScreenData.Search) {
            StandupListPresenter presenter = PresenterResolver.getPresenter(StandupListPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ReportScreenData.GotoWeeklyTiming) {
            MembersWeeklyHoursPresenter presenter = PresenterResolver.getPresenter(MembersWeeklyHoursPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ReportScreenData.GotoTimesheet) {
            TimeTrackingPresenter timeTrackingPresenter = PresenterResolver.getPresenter(TimeTrackingPresenter.class);
            timeTrackingPresenter.go(view, data);
        } else if (data instanceof ReportScreenData.GotoUserWorkload) {
            UserWorkloadReportPresenter userWorkloadReportPresenter = PresenterResolver.getPresenter(UserWorkloadReportPresenter.class);
            userWorkloadReportPresenter.go(view, data);
        } else {
            projectModule.removeAllComponents();
            projectModule.addComponent(view);
            view.showDashboard();

            ReportBreadcrumb breadcrumb = ViewManager.getCacheComponent(ReportBreadcrumb.class);
            breadcrumb.gotoReportDashboard();
        }
    }
}

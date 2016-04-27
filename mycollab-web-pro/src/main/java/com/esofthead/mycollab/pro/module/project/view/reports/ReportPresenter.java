package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.view.ProjectModule;
import com.esofthead.mycollab.module.project.view.parameters.ReportScreenData;
import com.esofthead.mycollab.module.project.view.parameters.StandupScreenData;
import com.esofthead.mycollab.module.project.view.reports.IReportContainer;
import com.esofthead.mycollab.module.project.view.reports.IReportPresenter;
import com.esofthead.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
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
        ProjectModule projectViewContainer = (ProjectModule) container;

        if (data instanceof StandupScreenData.Search) {
            StandupListPresenter presenter = PresenterResolver.getPresenter(StandupListPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof StandupScreenData.Add) {
            StandupAddPresenter presenter = PresenterResolver.getPresenter(StandupAddPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ReportScreenData.GotoHoursWeekly) {
            MembersWeeklyHoursPresenter presenter = PresenterResolver.getPresenter(MembersWeeklyHoursPresenter.class);
            presenter.go(projectViewContainer, data);
        } else {
            projectViewContainer.removeAllComponents();
            projectViewContainer.addComponent(view);
            view.showDashboard();

            ReportBreadcrumb breadcrumb = ViewManager.getCacheComponent(ReportBreadcrumb.class);
            breadcrumb.gotoReportDashboard();
        }
    }
}

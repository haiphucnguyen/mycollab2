package com.mycollab.module.project.view.user;

import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.view.*;
import com.mycollab.module.project.view.assignments.ICalendarPresenter;
import com.mycollab.module.project.view.parameters.ProjectScreenData;
import com.mycollab.module.project.view.parameters.ReportScreenData;
import com.mycollab.module.project.view.parameters.StandupScreenData;
import com.mycollab.module.project.view.reports.IReportPresenter;
import com.mycollab.vaadin.mvp.*;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectDashboardPresenter extends AbstractPresenter<ProjectDashboardContainer> {
    private static final long serialVersionUID = 1L;

    public ProjectDashboardPresenter() {
        super(ProjectDashboardContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        projectViewContainer.gotoSubView(ProjectTypeConstants.INSTANCE.getDASHBOARD());

        ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);

        if (data instanceof ProjectScreenData.Edit) {
            if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INSTANCE.getPROJECT())) {
                ProjectAddPresenter presenter = PresenterResolver.getPresenter(ProjectAddPresenter.class);
                presenter.go(view, data);
                breadcrumb.gotoProjectEdit();
            } else {
                NotificationUtil.showMessagePermissionAlert();
            }
        } else if (data instanceof ProjectScreenData.GotoTagList) {
            ITagListPresenter presenter = PresenterResolver.getPresenter(ITagListPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ProjectScreenData.GotoFavorite) {
            IFavoritePresenter presenter = PresenterResolver.getPresenter(IFavoritePresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ProjectScreenData.SearchItem) {
            ProjectSearchItemPresenter presenter = PresenterResolver.getPresenter(ProjectSearchItemPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ProjectScreenData.GotoGanttChart) {
            IGanttChartPresenter presenter = PresenterResolver.getPresenter(IGanttChartPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ProjectScreenData.GotoCalendarView) {
            ICalendarPresenter presenter = PresenterResolver.getPresenter(ICalendarPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ProjectScreenData.GotoReportConsole || data instanceof StandupScreenData.Search
                || data instanceof ReportScreenData.GotoWeeklyTiming) {
            IReportPresenter presenter = PresenterResolver.getPresenter(IReportPresenter.class);
            presenter.go(view, data);
        } else {
            if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.INSTANCE.getPROJECT())) {
                ProjectSummaryPresenter presenter = PresenterResolver.getPresenter(ProjectSummaryPresenter.class);
                presenter.go(view, data);
                breadcrumb.gotoProjectDashboard();
            } else {
                NotificationUtil.showMessagePermissionAlert();
            }
        }
    }

    @Override
    protected void onHandleChain(HasComponents container, PageActionChain pageActionChain) {
        ScreenData<?> pageAction = pageActionChain.peek();

        Class<? extends IPresenter> presenterCls = ProjectPresenterDataMapper.presenter(pageAction);
        if (presenterCls != null) {
            IPresenter<?> presenter = PresenterResolver.getPresenter(presenterCls);
            presenter.handleChain(view, pageActionChain);
        } else {
            throw new UnsupportedOperationException("Not support page action chain " + pageAction);
        }
    }
}

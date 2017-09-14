package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.assignments.CalendarView;
import com.mycollab.module.project.view.assignments.ICalendarPresenter;
import com.mycollab.module.project.view.user.ProjectDashboardContainer;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public class CalendarPresenter extends AbstractPresenter<CalendarView> implements ICalendarPresenter {
    public CalendarPresenter() {
        super(CalendarView.class);
    }

    @Override
    protected void postInitView() {
        HasSearchHandlers<ProjectTicketSearchCriteria> searchHandlers = view.getSearchHandlers();
        if (searchHandlers != null) {
            searchHandlers.addSearchHandler(criteria -> view.queryAssignments(criteria));
        }
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canReadAssignments()) {
            ProjectDashboardContainer projectDashboardContainer = (ProjectDashboardContainer) container;
            projectDashboardContainer.setContent(view);
            view.lazyLoadView();

            ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadCrumb.gotoCalendar();
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}

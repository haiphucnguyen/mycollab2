package com.esofthead.mycollab.community.module.project.view.assignments;

import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.assignments.CalendarView;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardContainer;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class CalendarPresenter extends AbstractPresenter<CalendarView> {
    public CalendarPresenter() {
        super(CalendarView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectDashboardContainer projectDashboardContainer = (ProjectDashboardContainer) container;
        projectDashboardContainer.removeAllComponents();
        projectDashboardContainer.addComponent(view.getWidget());
        view.lazyLoadView();

        ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
        breadCrumb.gotoCalendar();
    }
}

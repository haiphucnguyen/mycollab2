package com.mycollab.pro.module.project.view;

import com.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.mycollab.module.project.view.ICalendarDashboardPresenter;
import com.mycollab.module.project.view.ICalendarDashboardView;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.events.SearchHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class CalendarDashboardPresenter extends AbstractPresenter<ICalendarDashboardView> implements ICalendarDashboardPresenter {
    public CalendarDashboardPresenter() {
        super(ICalendarDashboardView.class);
    }

    @Override
    protected void viewAttached() {
        HasSearchHandlers<ProjectGenericTaskSearchCriteria> searchHandlers = view.getSearchHandlers();
        if (searchHandlers != null) {
            searchHandlers.addSearchHandler(new SearchHandler<ProjectGenericTaskSearchCriteria>() {
                @Override
                public void onSearch(ProjectGenericTaskSearchCriteria criteria) {
                    view.queryAssignments(criteria);
                }
            });
        }
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        container.addComponent(view);
        view.display();
    }
}

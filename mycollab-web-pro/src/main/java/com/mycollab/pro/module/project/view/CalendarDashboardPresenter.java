package com.mycollab.pro.module.project.view;

import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.view.ICalendarDashboardPresenter;
import com.mycollab.module.project.view.ICalendarDashboardView;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HasComponents;

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
        HasSearchHandlers<ProjectTicketSearchCriteria> searchHandlers = view.getSearchHandlers();
        if (searchHandlers != null) {
            searchHandlers.addSearchHandler(criteria -> view.queryAssignments(criteria));
        }
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ComponentContainer componentContainer = (ComponentContainer) container;
        componentContainer.removeAllComponents();
        componentContainer.addComponent(view);
        view.display();
    }
}

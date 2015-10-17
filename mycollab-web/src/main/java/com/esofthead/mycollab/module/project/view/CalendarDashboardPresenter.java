package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class CalendarDashboardPresenter extends AbstractPresenter<ICalendarDashboardView> {
    public CalendarDashboardPresenter() {
        super(ICalendarDashboardView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectModule prjContainer = (ProjectModule) container;
        prjContainer.removeAllComponents();
        prjContainer.with(view).withAlign(view, Alignment.TOP_CENTER);
        view.display();

        AppContext.addFragment("project/calendar", "Calendar");
    }
}

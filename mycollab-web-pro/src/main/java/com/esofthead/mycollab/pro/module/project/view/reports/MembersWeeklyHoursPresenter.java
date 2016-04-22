package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.view.user.ProjectDashboardContainer;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public class MembersWeeklyHoursPresenter extends AbstractPresenter<MembersWeeklyHoursView> {
    public MembersWeeklyHoursPresenter() {
        super(MembersWeeklyHoursView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectDashboardContainer dashboardContainer = (ProjectDashboardContainer) container;
        dashboardContainer.removeAllComponents();
        dashboardContainer.addComponent(view.getWidget());
        view.display();
    }
}

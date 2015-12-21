package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
public class ProjectDashboardPresenter extends AbstractPresenter<ProjectDashboardView> {
    public ProjectDashboardPresenter() {
        super(ProjectDashboardView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData data) {
        view.display();
    }
}

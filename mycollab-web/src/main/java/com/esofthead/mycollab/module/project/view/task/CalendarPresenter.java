package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.1.5
 */
public class CalendarPresenter extends AbstractPresenter<CalendarView> {
    public CalendarPresenter() {
        super(CalendarView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.TASKS)) {
            TaskContainer taskContainer = (TaskContainer) container;
            taskContainer.navigateToContainer(ProjectTypeConstants.TASK);
            taskContainer.removeAllComponents();
            taskContainer.addComponent(view.getWidget());
            view.removeAllComponents();
            view.display();

            ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadCrumb.gotoCalendar();
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}

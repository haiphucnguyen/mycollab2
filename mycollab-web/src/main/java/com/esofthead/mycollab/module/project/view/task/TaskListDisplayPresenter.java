package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

public class TaskListDisplayPresenter extends AbstractPresenter<TaskListDisplayView> {

    private static final long serialVersionUID = 1L;

    public TaskListDisplayPresenter() {
        super(TaskListDisplayView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        TaskContainer taskContainer = (TaskContainer) container;
        taskContainer.removeAllComponents();
        
        taskContainer.addComponent(view.getWidget());
        view.displayTakLists();
        
        ProjectBreadcrumb breadCrumb = ViewManager.getView(ProjectBreadcrumb.class);
        breadCrumb.gotoTaskDashboard();
    }
}

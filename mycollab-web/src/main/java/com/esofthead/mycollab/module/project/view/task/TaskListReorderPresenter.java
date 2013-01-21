/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class TaskListReorderPresenter extends AbstractPresenter<TaskListReorderView> {
    public TaskListReorderPresenter() {
        super(TaskListReorderView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        TaskContainer taskContainer = (TaskContainer) container;
        taskContainer.removeAllComponents();
        
        taskContainer.addComponent(view.getWidget());
        view.displayTaskLists();
        
        ProjectBreadcrumb breadCrumb = ViewManager.getView(ProjectBreadcrumb.class);
        breadCrumb.gotoTaskListReorder();
    }
}

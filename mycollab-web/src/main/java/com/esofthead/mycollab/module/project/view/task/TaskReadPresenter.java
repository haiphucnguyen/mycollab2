/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class TaskReadPresenter extends AbstractPresenter<TaskReadView> {
    public TaskReadPresenter() {
        super(TaskReadView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        TaskContainer taskContainer = (TaskContainer) container;
        taskContainer.removeAllComponents();
        
        taskContainer.addComponent(view.getWidget());
        if (data.getParams() instanceof Integer) {
            ProjectTaskService taskService = AppContext
                    .getSpringBean(ProjectTaskService.class);
            SimpleTask task = taskService.findTaskById((Integer) data
                    .getParams());
            view.previewItem(task);
        }
    }
}

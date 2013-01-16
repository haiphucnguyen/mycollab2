/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class TaskListReadPresenter extends AbstractPresenter<TaskListReadView> {

    public TaskListReadPresenter() {
        super(TaskListReadView.class);
        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<TaskList>() {
                    @Override
                    public void onEdit(TaskList data) {
                        EventBus.getInstance().fireEvent(
                                new TaskListEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(TaskList data) {
                        ProjectTaskListService taskListService = AppContext
                                .getSpringBean(ProjectTaskListService.class);
                        taskListService.removeWithSession(data.getId(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new TaskListEvent.GotoTaskListScreen(this, null));
                    }

                    @Override
                    public void onClone(TaskList data) {
                        TaskList cloneData = (TaskList) data.copy();
                        cloneData.setId(null);
                        EventBus.getInstance().fireEvent(
                                new TaskListEvent.GotoEdit(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new TaskListEvent.GotoTaskListScreen(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        TaskContainer taskContainer = (TaskContainer) container;
        taskContainer.removeAllComponents();

        taskContainer.addComponent(view.getWidget());

        if (data.getParams() instanceof Integer) {
            ProjectTaskListService taskService = AppContext
                    .getSpringBean(ProjectTaskListService.class);
            SimpleTaskList task = taskService.findTaskListById((Integer) data
                    .getParams());
            view.previewItem(task);
        }
    }
}

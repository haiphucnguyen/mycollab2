/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent.SaveReoderTaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import java.util.Set;

/**
 *
 * @author haiphucnguyen
 */
public class TaskGroupReorderPresenter extends AbstractPresenter<TaskGroupReorderView> {

    public TaskGroupReorderPresenter() {
        super(TaskGroupReorderView.class);

        EventBus.getInstance().addListener(new ApplicationEventListener<TaskListEvent.SaveReoderTaskList>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TaskListEvent.SaveReoderTaskList.class;
            }

            @Override
            public void handle(SaveReoderTaskList event) {
                Set<SimpleTaskList> changeSet = (Set<SimpleTaskList>) event.getData();
                ProjectTaskListService taskListService = (ProjectTaskListService)AppContext.getSpringBean(ProjectTaskListService.class);
                taskListService.updateTaskListIndex(changeSet.toArray(new TaskList[]{}));
                EventBus.getInstance().fireEvent(new TaskListEvent.GotoTaskListScreen(this, null));
            }
        });
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

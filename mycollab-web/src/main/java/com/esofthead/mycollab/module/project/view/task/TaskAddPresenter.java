/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class TaskAddPresenter extends AbstractPresenter<TaskAddView> {

    public TaskAddPresenter() {
        super(TaskAddView.class);
        
        view.getEditFormHandlers().addFormHandler(new EditFormHandler<Task>() {
            @Override
            public void onSave(final Task item) {
                save(item);
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new TaskListEvent.GotoTaskListScreen(this, null));
                }
            }

            @Override
            public void onCancel() {
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new TaskListEvent.GotoTaskListScreen(this, null));
                }
            }

            @Override
            public void onSaveAndNew(final Task item) {
                save(item);
                EventBus.getInstance().fireEvent(
                        new TaskEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        TaskContainer taskContainer = (TaskContainer) container;
        taskContainer.removeAllComponents();

        taskContainer.addComponent(view.getWidget());
        
        Task task = (Task) data.getParams();
        view.editItem(task);
        
        ProjectBreadcrumb breadCrumb = ViewManager.getView(ProjectBreadcrumb.class);
        if (task.getId() == null) {
            breadCrumb.gotoTaskAdd();
        } else {
            breadCrumb.gotoTaskEdit(task);
        }
    }

    public void save(Task item) {
        ProjectTaskService taskService = AppContext.getSpringBean(ProjectTaskService.class);

        item.setSaccountid(AppContext.getAccountId());
        if (item.getPercentagecomplete() == null) {
            item.setPercentagecomplete(new Double(0));
            item.setStatus("Open");
        } else if (item.getPercentagecomplete().doubleValue() == 100d) {
            item.setStatus("Closed");
        } else {
            item.setStatus("Open");
        }
        
        if (item.getId() == null) {
            taskService.saveWithSession(item, AppContext.getUsername());
        } else {
            taskService.updateWithSession(item, AppContext.getUsername());
        }
    }
}

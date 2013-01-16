/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class TaskListAddPresenter extends AbstractPresenter<TaskListAddView> {
    public TaskListAddPresenter() {
        super(TaskListAddView.class);
        
        view.getEditFormHandlers().addFormHandler(new EditFormHandler<TaskList>() {
            @Override
            public void onSave(final TaskList item) {
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
            public void onSaveAndNew(final TaskList item) {
                save(item);
                EventBus.getInstance().fireEvent(
                        new TaskListEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        TaskContainer taskContainer = (TaskContainer) container;
        taskContainer.removeAllComponents();

        taskContainer.addComponent(view.getWidget());
        view.editItem((TaskList) data.getParams());
    }

    public void save(TaskList item) {
        ProjectTaskListService taskService = AppContext.getSpringBean(ProjectTaskListService.class);

        item.setSaccountid(AppContext.getAccountId());
        if (item.getId() == null) {
            taskService.saveWithSession(item, AppContext.getUsername());
        } else {
            taskService.updateWithSession(item, AppContext.getUsername());
        }

    }
}

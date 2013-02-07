package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.TaskService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class TaskAddPresenter extends CrmGenericPresenter<TaskAddView> {

    private static final long serialVersionUID = 1L;

    public TaskAddPresenter(TaskAddView view) {
        super(TaskAddView.class);
        view.getEditFormHandlers().addFormHandler(new EditFormHandler<Task>() {
            @Override
            public void onSave(final Task item) {
                save(item);
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new ActivityEvent.GotoTodoList(this, null));
                }
            }

            @Override
            public void onCancel() {
                System.out.println("Task add presenter oncancel");
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new ActivityEvent.GotoTodoList(this, null));
                }
            }

            @Override
            public void onSaveAndNew(final Task item) {
                save(item);
                EventBus.getInstance().fireEvent(
                        new ActivityEvent.TaskAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        super.onGo(container, data);
        Task task = (Task) data.getParams();
        view.editItem(task);
        
        if (task.getId() == null) {
            AppContext.addFragment("crm/task/add");
        } else {
            AppContext.addFragment("crm/task/edit/" + UrlEncodeDecoder.encode(task.getId()));
        }
    }

    public void save(Task item) {
        TaskService taskService = AppContext.getSpringBean(TaskService.class);

        item.setSaccountid(AppContext.getAccountId());
        if (item.getId() == null) {
            taskService.saveWithSession(item, AppContext.getUsername());
        } else {
            taskService.updateWithSession(item, AppContext.getUsername());
        }

    }
}

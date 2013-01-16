package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.TaskService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class TaskReadPresenter extends CrmGenericPresenter<TaskReadView> {

	private static final long serialVersionUID = 1L;

	public TaskReadPresenter() {
		super(TaskReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Task>() {
					@Override
					public void onEdit(Task data) {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.TaskEdit(this, data));
					}

					@Override
					public void onDelete(Task data) {
						TaskService taskService = AppContext
								.getSpringBean(TaskService.class);
						taskService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new ActivityEvent.GotoTodoList(this, null));
					}

					@Override
					public void onClone(Task data) {
						Task cloneData = (Task) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new ActivityEvent.TaskEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.GotoTodoList(this, null));
					}

					@Override
					public void gotoNext(Task data) {
						TaskService taskService = AppContext
								.getSpringBean(TaskService.class);
						TaskSearchCriteria criteria = new TaskSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATHER));
						Integer nextId = taskService.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskRead(this, nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the last record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(Task data) {
						TaskService taskService = AppContext
								.getSpringBean(TaskService.class);
						TaskSearchCriteria criteria = new TaskSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = taskService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskRead(this, nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the first record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		if (data.getParams() instanceof Integer) {
			TaskService taskService = AppContext
					.getSpringBean(TaskService.class);
			SimpleTask task = taskService.findTaskById((Integer) data
					.getParams());
			view.previewItem(task);
		}

	}
}

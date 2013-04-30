package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.service.TaskService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class AssignmentAddPresenter extends
		CrmGenericPresenter<AssignmentAddView> {

	private static final long serialVersionUID = 1L;

	public AssignmentAddPresenter() {
		super(AssignmentAddView.class);

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
		if (AppContext.canWrite(RolePermissionCollections.CRM_TASK)) {
			Task task = null;
			if (data.getParams() instanceof Task) {
				task = (Task) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				TaskService taskService = AppContext
						.getSpringBean(TaskService.class);
				task = taskService.findByPrimaryKey((Integer) data
						.getParams());
				if (task == null) {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
					return;
				}
			}else {
				throw new MyCollabException("Do not support param data: "
						+ data);
			}
			
			container.removeAllComponents();
			container.addComponent(view.getWidget());
			view.editItem(task);

			if (task.getId() == null) {
				AppContext.addFragment("crm/activity/task/add/",
						"Add Activity Task");
			} else {
				AppContext.addFragment("crm/activity/task/edit/"
						+ UrlEncodeDecoder.encode(task.getId()),
						"Edit Activity Task: " + task.getSubject());
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
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

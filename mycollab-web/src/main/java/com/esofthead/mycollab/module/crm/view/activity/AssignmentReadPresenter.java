package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.localization.ActivityI18nEnum;
import com.esofthead.mycollab.module.crm.service.TaskService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmLinkGenerator;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class AssignmentReadPresenter extends
		CrmGenericPresenter<AssignmentReadView> {

	private static final long serialVersionUID = 1L;

	public AssignmentReadPresenter() {
		super(AssignmentReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleTask>() {
					@Override
					public void onEdit(SimpleTask data) {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.TaskEdit(this, data));
					}

					@Override
					public void onDelete(final SimpleTask data) {
						ConfirmDialog.show(
								view.getWindow(),
								LocalizationHelper
										.getMessage(
												GenericI18Enum.DELETE_DIALOG_TITLE,
												ApplicationProperties
														.getProperty(ApplicationProperties.SITE_NAME)),
								LocalizationHelper
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											TaskService taskService = AppContext
													.getSpringBean(TaskService.class);
											taskService.removeWithSession(
													data.getId(),
													AppContext.getUsername());
											EventBus.getInstance()
													.fireEvent(
															new ActivityEvent.GotoTodoList(
																	this, null));
										}
									}
								});
					}

					@Override
					public void onClone(SimpleTask data) {
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
					public void gotoNext(SimpleTask data) {
						TaskService taskService = AppContext
								.getSpringBean(TaskService.class);
						TaskSearchCriteria criteria = new TaskSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = taskService.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskRead(this, nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(SimpleTask data) {
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
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_TASK)) {
			SimpleTask task = null;
			if (data.getParams() instanceof Integer) {
				TaskService taskService = AppContext
						.getSpringBean(TaskService.class);
				task = taskService.findTaskById((Integer) data.getParams());
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
			} else {
				throw new MyCollabException("Invalid data " + data);
			}

			container.removeAllComponents();
			container.addComponent(view.getWidget());
			view.previewItem(task);
			AppContext.addFragment(CrmLinkGenerator
					.generateTaskPreviewLink(task.getId()), LocalizationHelper
					.getMessage(ActivityI18nEnum.PREVIEW_TASK_TITLE,
							task.getSubject()));

		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}

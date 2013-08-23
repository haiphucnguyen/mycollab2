/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskReadPresenter extends AbstractPresenter<TaskReadView> {

	private static final long serialVersionUID = 1L;

	public TaskReadPresenter() {
		super(TaskReadView.class);
		this.bind();
	}

	private void bind() {
		this.view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Task>() {

					@Override
					public void onAssign(final Task data) {
						AppContext.getApplication().getMainWindow()
								.addWindow(new AssignTaskWindow(data));
					}

					@Override
					public void onEdit(final Task data) {
						EventBus.getInstance().fireEvent(
								new TaskEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final Task data) {
						ConfirmDialogExt.show(
								AppContext.getApplication().getMainWindow(),
								LocalizationHelper.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										SiteConfiguration.getSiteName()),
								LocalizationHelper
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(
											final ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											final ProjectTaskService taskService = AppContext
													.getSpringBean(ProjectTaskService.class);
											taskService.removeWithSession(
													data.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
											EventBus.getInstance()
													.fireEvent(
															new TaskListEvent.GotoTaskListScreen(
																	this, null));
										}
									}
								});
					}

					@Override
					public void onClone(final Task data) {
						final Task cloneData = (Task) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new TaskEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance()
								.fireEvent(
										new TaskListEvent.GotoTaskListScreen(
												this, null));
					}

					@Override
					public void gotoNext(final Task data) {
						final ProjectTaskService taskService = AppContext
								.getSpringBean(ProjectTaskService.class);

						final TaskSearchCriteria criteria = new TaskSearchCriteria();
						final SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectid(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						final Integer nextId = taskService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new TaskEvent.GotoRead(this, nextId));
						} else {
							TaskReadPresenter.this.view
									.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(final Task data) {
						final ProjectTaskService taskService = AppContext
								.getSpringBean(ProjectTaskService.class);

						final TaskSearchCriteria criteria = new TaskSearchCriteria();
						final SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectid(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						final Integer nextId = taskService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new TaskEvent.GotoRead(this, nextId));
						} else {
							TaskReadPresenter.this.view
									.getWindow()
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
	protected void onGo(final ComponentContainer container,
			final ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS)) {
			final TaskContainer taskContainer = (TaskContainer) container;
			taskContainer.removeAllComponents();

			taskContainer.addComponent(this.view.getWidget());
			if (data.getParams() instanceof Integer) {
				final ProjectTaskService taskService = AppContext
						.getSpringBean(ProjectTaskService.class);
				final SimpleTask task = taskService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				this.view.previewItem(task);

				final ProjectBreadcrumb breadCrumb = ViewManager
						.getView(ProjectBreadcrumb.class);
				breadCrumb.gotoTaskRead(task);
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}

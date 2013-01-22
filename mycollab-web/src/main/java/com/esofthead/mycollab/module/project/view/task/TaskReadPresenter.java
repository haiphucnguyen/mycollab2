/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskReadPresenter extends AbstractPresenter<TaskReadView> {

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
								new TaskEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final Task data) {
						ConfirmDialog.show(
								AppContext.getApplication().getMainWindow(),
								"Please Confirm:",
								"Are you sure to delete this item: "
										+ data.getTaskname() + " ?", "Yes",
								"No", new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											ProjectTaskService taskService = AppContext
													.getSpringBean(ProjectTaskService.class);
											taskService.removeWithSession(
													data.getId(),
													AppContext.getUsername());
											EventBus.getInstance()
													.fireEvent(
															new TaskListEvent.GotoTaskListScreen(
																	this, null));
										}
									}
								});
					}

					@Override
					public void onClone(Task data) {
						Task cloneData = (Task) data.copy();
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
				});
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

			ProjectBreadcrumb breadCrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			breadCrumb.gotoTaskRead(task);
		}
	}
}

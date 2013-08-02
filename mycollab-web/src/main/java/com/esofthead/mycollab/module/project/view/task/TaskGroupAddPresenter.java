/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskGroupAddPresenter extends AbstractPresenter<TaskGroupAddView> {
	private static final long serialVersionUID = 1L;

	public TaskGroupAddPresenter() {
		super(TaskGroupAddView.class);

		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<TaskList>() {
					@Override
					public void onSave(final TaskList item) {
						save(item);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new TaskListEvent.GotoTaskListScreen(this,
											null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new TaskListEvent.GotoTaskListScreen(this,
											null));
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
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS)) {
			TaskContainer taskContainer = (TaskContainer) container;
			taskContainer.removeAllComponents();

			taskContainer.addComponent(view.getWidget());
			TaskList taskList = (TaskList) data.getParams();
			view.editItem(taskList);

			ProjectBreadcrumb breadCrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			if (taskList.getId() == null) {
				breadCrumb.gotoTaskGroupAdd();
			} else {
				breadCrumb.gotoTaskGroupEdit(taskList);
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	public void save(TaskList item) {
		ProjectTaskListService taskService = AppContext
				.getSpringBean(ProjectTaskListService.class);

		item.setSaccountid(AppContext.getAccountId());
		
		if (item.getId() == null) {
			taskService.saveWithSession(item, AppContext.getUsername());
		} else {
			taskService.updateWithSession(item, AppContext.getUsername());
		}

	}
}

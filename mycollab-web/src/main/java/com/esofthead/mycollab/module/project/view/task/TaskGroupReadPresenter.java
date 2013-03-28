/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class TaskGroupReadPresenter extends
		AbstractPresenter<TaskGroupReadView> {

	public TaskGroupReadPresenter() {
		super(TaskGroupReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleTaskList>() {
					@Override
					public void onEdit(SimpleTaskList data) {
						EventBus.getInstance().fireEvent(
								new TaskListEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(SimpleTaskList data) {
						ProjectTaskListService taskListService = AppContext
								.getSpringBean(ProjectTaskListService.class);
						taskListService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance()
								.fireEvent(
										new TaskListEvent.GotoTaskListScreen(
												this, null));
					}

					@Override
					public void onClone(SimpleTaskList data) {
						TaskList cloneData = (TaskList) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new TaskListEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance()
								.fireEvent(
										new TaskListEvent.GotoTaskListScreen(
												this, null));
					}

					@Override
					public void gotoNext(SimpleTaskList data) {
						ProjectTaskListService tasklistService = AppContext
								.getSpringBean(ProjectTaskListService.class);
						TaskListSearchCriteria criteria = new TaskListSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATHER));
						Integer nextId = tasklistService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new TaskListEvent.GotoRead(this, nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the last record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(SimpleTaskList data) {
						ProjectTaskListService tasklistService = AppContext
								.getSpringBean(ProjectTaskListService.class);
						TaskListSearchCriteria criteria = new TaskListSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = tasklistService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new TaskListEvent.GotoRead(this, nextId));
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
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS)) {
			TaskContainer taskContainer = (TaskContainer) container;
			taskContainer.removeAllComponents();

			taskContainer.addComponent(view.getWidget());

			if (data.getParams() instanceof Integer) {
				ProjectTaskListService taskService = AppContext
						.getSpringBean(ProjectTaskListService.class);
				SimpleTaskList taskgroup = taskService
						.findTaskListById((Integer) data.getParams());
				view.previewItem(taskgroup);

				ProjectBreadcrumb breadCrumb = ViewManager
						.getView(ProjectBreadcrumb.class);
				breadCrumb.gotoTaskGroupRead(taskgroup);
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}

/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.Set;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent.SaveReoderTaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskGroupReorderPresenter extends
		AbstractPresenter<TaskGroupReorderView> {
	private static final long serialVersionUID = 1L;

	public TaskGroupReorderPresenter() {
		super(TaskGroupReorderView.class);

		EventBus.getInstance()
				.addListener(
						new ApplicationEventListener<TaskListEvent.SaveReoderTaskList>() {
							private static final long serialVersionUID = 1L;

							@Override
							public Class<? extends ApplicationEvent> getEventType() {
								return TaskListEvent.SaveReoderTaskList.class;
							}

							@Override
							public void handle(SaveReoderTaskList event) {
								Set<SimpleTaskList> changeSet = (Set<SimpleTaskList>) event
										.getData();
								ProjectTaskListService taskListService = (ProjectTaskListService) ApplicationContextUtil
										.getSpringBean(ProjectTaskListService.class);
								taskListService.updateTaskListIndex(
										changeSet.toArray(new TaskList[] {}),
										AppContext.getAccountId());
								EventBus.getInstance().fireEvent(
										new TaskListEvent.GotoTaskListScreen(
												this, null));
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
			view.displayTaskLists();

			ProjectBreadcrumb breadCrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			breadCrumb.gotoTaskListReorder();
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}

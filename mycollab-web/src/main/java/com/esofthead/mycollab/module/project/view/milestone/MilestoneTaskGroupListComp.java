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
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.view.task.TaskDisplayWidget;
import com.esofthead.mycollab.module.project.view.task.TaskGroupAddWindow;
import com.esofthead.mycollab.module.project.view.task.TaskGroupDisplayView;
import com.esofthead.mycollab.module.project.view.task.TaskGroupDisplayWidget;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MilestoneTaskGroupListComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private Milestone milestone;
	private ToggleButtonGroup viewGroup;
	private TaskGroupDisplayWidget taskGroupDisplayWidget;

	public MilestoneTaskGroupListComp() {
		this.setMargin(true);
		TaskGroupListView taskGroup = new TaskGroupListView();
		taskGroup.contructTaskLayout();
	}

	private class TaskGroupListView extends AbstractPageView implements
			TaskGroupDisplayView {
		private static final long serialVersionUID = 1L;
		private VerticalLayout mainLayout;

		public void contructTaskLayout() {
			mainLayout = new VerticalLayout();
			mainLayout.setWidth("100%");

			Button newTaskListBtn = new Button(
					LocalizationHelper
							.getMessage(TaskI18nEnum.NEW_TASKGROUP_ACTION),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							SimpleTaskList taskList = new SimpleTaskList();
							taskList.setMilestoneid(milestone.getId());
							taskList.setMilestoneName(milestone.getName());
							TaskGroupAddWindow taskListWindow = new TaskGroupAddWindow(
									TaskGroupListView.this, taskList);
							UI.getCurrent().addWindow(taskListWindow);
						}
					});
			newTaskListBtn.setEnabled(CurrentProjectVariables
					.canWrite(ProjectRolePermissionCollections.TASKS));
			newTaskListBtn.setIcon(MyCollabResource
					.newResource("icons/16/project/new_task_list.png"));
			newTaskListBtn.setDescription(LocalizationHelper
					.getMessage(TaskI18nEnum.NEW_TASKGROUP_ACTION));
			newTaskListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			mainLayout.addComponent(newTaskListBtn);
			mainLayout
					.setComponentAlignment(newTaskListBtn, Alignment.TOP_LEFT);

			HorizontalLayout header = new HorizontalLayout();
			header.setMargin(new MarginInfo(true, false, false, false));
			header.setSpacing(true);
			header.setWidth("100%");
			Label taskGroupSelection = new Label("Tasks");
			taskGroupSelection.addStyleName(UIConstants.H2_NO_PADDING);
			taskGroupSelection.addStyleName(UIConstants.THEME_NO_BORDER);
			header.addComponent(taskGroupSelection);
			header.setExpandRatio(taskGroupSelection, 1.0f);
			header.setComponentAlignment(taskGroupSelection,
					Alignment.MIDDLE_LEFT);

			viewGroup = new ToggleButtonGroup();

			Button simpleDisplay = new Button(null, new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					displaySimpleView();
				}
			});
			simpleDisplay.setIcon(MyCollabResource
					.newResource("icons/16/project/list_display.png"));

			viewGroup.addButton(simpleDisplay);

			Button advanceDisplay = new Button(null,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							displayAdvancedView();
						}
					});
			advanceDisplay.setIcon(MyCollabResource
					.newResource("icons/16/project/advanced_display.png"));
			viewGroup.addButton(advanceDisplay);

			header.addComponent(viewGroup);
			header.setComponentAlignment(viewGroup, Alignment.MIDDLE_RIGHT);

			mainLayout.addComponent(header);
			MilestoneTaskGroupListComp.this.addComponent(mainLayout);
		}

		@Override
		public ComponentContainer getWidget() {
			return mainLayout;
		}

		@Override
		public void addViewListener(
				ApplicationEventListener<? extends ApplicationEvent> listener) {

		}

		@Override
		public void insertTaskList(SimpleTaskList taskList) {
			taskGroupDisplayWidget.insetItemOnBottom(taskList);
		}

		@Override
		public void displayActiveTakLists() {

		}

	}

	private void displaySimpleView() {
		TaskSearchCriteria criteria = new TaskSearchCriteria();
		criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setMilestoneId(new NumberSearchField(milestone.getId()));

		if (this.getComponentCount() > 1) {
			this.removeComponent(this.getComponent(1));
		}

		TaskDisplayWidget taskDisplayWidget = new TaskDisplayWidget();
		this.addComponent(taskDisplayWidget);
		taskDisplayWidget.setSearchCriteria(criteria);
	}

	private void displayAdvancedView() {

		if (this.getComponentCount() > 1) {
			this.removeComponent(this.getComponent(1));
		}

		TaskListSearchCriteria criteria = createBaseSearchCriteria();

		taskGroupDisplayWidget = new TaskGroupDisplayWidget();
		this.addComponent(taskGroupDisplayWidget);
		taskGroupDisplayWidget.setSearchCriteria(criteria);
	}

	public void displayTakLists(Milestone milestone) {
		this.milestone = milestone;
		//TODO: check default selected index
		displayAdvancedView();
	}

	private TaskListSearchCriteria createBaseSearchCriteria() {
		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setMilestoneIds(new SetSearchField<Integer>(milestone.getId()));
		return criteria;
	}

}

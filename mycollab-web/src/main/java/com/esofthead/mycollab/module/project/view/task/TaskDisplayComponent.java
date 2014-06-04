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

package com.esofthead.mycollab.module.project.view.task;

import java.util.Arrays;
import java.util.Iterator;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.ProgressPercentageIndicator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
class TaskDisplayComponent extends CssLayout {
	private static final long serialVersionUID = 1L;

	private TaskSearchCriteria criteria;
	private TaskTableDisplay taskDisplay;
	private Button createTaskBtn;

	private AdvancedPreviewBeanForm<SimpleTaskList> previewForm;
	private ProgressPercentageIndicator taskListProgress;
	private Label taskNumberLbl;

	private SimpleTaskList taskList;
	private boolean isDisplayTaskListInfo;

	public TaskDisplayComponent(final SimpleTaskList taskList,
			final boolean isDisplayTaskListInfo) {
		this.taskList = taskList;
		this.isDisplayTaskListInfo = isDisplayTaskListInfo;
		this.setStyleName("taskdisplay-component");

		this.showTaskGroupInfo();
		this.setSizeFull();
	}

	private void showTaskGroupInfo() {
		if (this.isDisplayTaskListInfo) {
			previewForm = new AdvancedPreviewBeanForm<SimpleTaskList>();
			previewForm.setWidth("100%");
			previewForm.setFormLayoutFactory(new IFormLayoutFactory() {
				private static final long serialVersionUID = 1L;

				private GridFormLayoutHelper layoutHelper;

				@Override
				public Layout getLayout() {
					this.layoutHelper = new GridFormLayoutHelper(2, 3, "100%",
							"180px", Alignment.TOP_LEFT);
					this.layoutHelper.getLayout().setWidth("100%");
					this.layoutHelper.getLayout().addStyleName(
							"colored-gridlayout");
					this.layoutHelper.getLayout().setMargin(false);
					return this.layoutHelper.getLayout();
				}

				@Override
				public void attachField(Object propertyId, Field<?> field) {
					if ("description".equals(propertyId)) {
						layoutHelper
								.addComponent(field, "Description", 0, 0, 2);
					} else if ("owner".equals(propertyId)) {
						layoutHelper.addComponent(field, "Assignee", 0, 1);
					} else if ("milestoneid".equals(propertyId)) {
						layoutHelper.addComponent(field, "Phase name", 1, 1);
					}
				}
			});
			previewForm
					.setBeanFormFieldFactory(new AbstractBeanFieldGroupViewFieldFactory<SimpleTaskList>(
							previewForm) {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field<?> onCreateField(Object propertyId) {
							if ("description".equals(propertyId)) {
								return new FormViewField(taskList
										.getDescription(), ContentMode.HTML);
							} else if ("owner".equals(propertyId)) {
								return new ProjectUserFormLinkField(taskList
										.getOwner(), taskList
										.getOwnerAvatarId(), taskList
										.getOwnerFullName());
							} else if ("milestoneid".equals(propertyId)) {

								return new DefaultFormViewFieldFactory.FormLinkViewField(
										taskList.getMilestoneName(),
										ProjectLinkBuilder
												.generateMilestonePreviewFullLink(
														taskList.getProjectid(),
														taskList.getMilestoneid()),
										MyCollabResource
												.newResourceLink("icons/16/project/milestone.png"));
							}

							return null;
						}

					});
			this.addComponent(previewForm);

			previewForm.setBean(this.taskList);
		}

		this.taskDisplay = new TaskTableDisplay(TaskTableFieldDef.id,
				Arrays.asList(TaskTableFieldDef.taskname,
						TaskTableFieldDef.startdate, TaskTableFieldDef.duedate,
						TaskTableFieldDef.assignee,
						TaskTableFieldDef.percentagecomplete));
		this.addComponent(this.taskDisplay);

		this.taskDisplay
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleTask task = (SimpleTask) event.getData();
						if ("taskname".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new TaskEvent.GotoRead(
											TaskDisplayComponent.this, task
													.getId()));
						} else if ("closeTask".equals(event.getFieldName())
								|| "reopenTask".equals(event.getFieldName())
								|| "pendingTask".equals(event.getFieldName())
								|| "deleteTask".equals(event.getFieldName())) {
							TaskDisplayComponent.this.removeAllComponents();
							final ProjectTaskListService taskListService = ApplicationContextUtil
									.getSpringBean(ProjectTaskListService.class);
							TaskDisplayComponent.this.taskList = taskListService
									.findById(
											TaskDisplayComponent.this.taskList
													.getId(), AppContext
													.getAccountId());
							TaskDisplayComponent.this.showTaskGroupInfo();
						}
					}
				});

		this.createTaskBtn = new Button(
				AppContext.getMessage(TaskI18nEnum.NEW_TASK_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final Button.ClickEvent event) {
						TaskDisplayComponent.this
								.removeComponent(TaskDisplayComponent.this.createTaskBtn
										.getParent());

						TaskAddPopup taskAddView = new TaskAddPopup(
								TaskDisplayComponent.this,
								TaskDisplayComponent.this.taskList);
						TaskDisplayComponent.this.addComponent(taskAddView);
					}
				});
		this.createTaskBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS));
		this.createTaskBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		this.createTaskBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		final VerticalLayout taskGroupFooter = new VerticalLayout();
		taskGroupFooter.setMargin(true);
		taskGroupFooter.addStyleName("task-list-footer");
		taskGroupFooter.addComponent(this.createTaskBtn);
		taskGroupFooter.setComponentAlignment(this.createTaskBtn,
				Alignment.MIDDLE_RIGHT);
		this.addComponent(taskGroupFooter);

		this.taskDisplay.setItems(this.taskList.getSubTasks());
	}

	public void setSearchCriteria(final TaskSearchCriteria criteria) {
		this.criteria = criteria;
		this.displayTasks();

	}

	private void displayTasks() {
		if (this.criteria == null) {
			final TaskSearchCriteria criteria = new TaskSearchCriteria();
			criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			criteria.setTaskListId(new NumberSearchField(this.taskList.getId()));
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { "Open", "Pending" }));
			this.criteria = criteria;
		}

		this.taskDisplay.setSearchCriteria(this.criteria);

		// Update tasklist progress and number of open task/all task
		if (this.taskNumberLbl != null) {
			this.taskNumberLbl.setValue("("
					+ (this.taskList.getNumOpenTasks() + 1) + "/"
					+ (this.taskList.getNumAllTasks() + 1) + ")");
		}

		final int newAllTasks = this.taskList.getNumAllTasks() + 1;
		final double newProgressTask = (this.taskList.getPercentageComplete() * this.taskList
				.getNumAllTasks()) / newAllTasks;
		if (this.taskListProgress != null) {
			this.taskListProgress.setValue(newProgressTask + "");
		}
	}

	public void saveTaskSuccess(final SimpleTask task) {
		this.displayTasks();
		if (!this.isDisplayTaskListInfo) {
			EventBus.getInstance().fireEvent(
					new TaskListEvent.GotoRead(this, this.taskList.getId()));
		}
	}

	public void closeTaskAdd() {
		final VerticalLayout taskGroupFooter = new VerticalLayout();
		taskGroupFooter.setMargin(true);
		taskGroupFooter.addStyleName("task-list-footer");
		taskGroupFooter.addComponent(this.createTaskBtn);
		taskGroupFooter.setComponentAlignment(this.createTaskBtn,
				Alignment.MIDDLE_RIGHT);
		this.addComponent(taskGroupFooter);

		Iterator<Component> comps = this.iterator();
		while (comps.hasNext()) {
			Component component = comps.next();
			if (component instanceof TaskAddPopup) {
				this.removeComponent(component);
				return;
			}
		}
	}
}

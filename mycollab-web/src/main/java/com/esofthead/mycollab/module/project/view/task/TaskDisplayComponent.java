/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ProgressPercentageIndicator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskDisplayComponent extends CssLayout {
	private static final long serialVersionUID = 1L;

	private TaskSearchCriteria criteria;
	private TaskTableDisplay taskDisplay;
	private Button createTaskBtn;
	private ProgressPercentageIndicator taskListProgress;
	private Label taskNumberLbl;

	private SimpleTaskList taskList;
	private boolean isDisplayTaskListInfo;

	public TaskDisplayComponent(final SimpleTaskList taskList,
			boolean isDisplayTaskListInfo) {
		this.taskList = taskList;
		this.isDisplayTaskListInfo = isDisplayTaskListInfo;
		this.setStyleName("taskdisplay-component");

		showTaskGroupInfo();
	}

	private void showTaskGroupInfo() {
		if (isDisplayTaskListInfo) {
			GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(2, 3);
			layoutHelper.getLayout().setWidth("100%");
			this.addComponent(layoutHelper.getLayout());

			Label descLbl = (Label) layoutHelper.addComponent(new Label(),
					"Description", 0, 0, 2, "100%", Alignment.TOP_RIGHT);
			descLbl.setValue(taskList.getDescription());

			layoutHelper.addComponent(
					new ProjectUserFormLinkField(taskList.getOwner(), taskList
							.getOwnerFullName()), "Responsible User", 0, 1,
					Alignment.TOP_RIGHT);

			DefaultFormViewFieldFactory.FormLinkViewField milestoneLink = new DefaultFormViewFieldFactory.FormLinkViewField(
					taskList.getMilestoneName(), new Button.ClickListener() {

						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(Button.ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new MilestoneEvent.GotoRead(this, taskList
											.getMilestoneid()));
						}
					});
			layoutHelper.addComponent(milestoneLink, "Phase", 1, 1,
					Alignment.TOP_RIGHT);

			taskListProgress = (ProgressPercentageIndicator) layoutHelper
					.addComponent(
							new ProgressPercentageIndicator(taskList
									.getPercentageComplete()), "Progress", 0, 2);
			taskListProgress.setWidth("100px");

			HorizontalLayout taskNumberProgress = new HorizontalLayout();
			taskNumberProgress.setSpacing(true);
			taskNumberProgress = (HorizontalLayout) layoutHelper.addComponent(
					taskNumberProgress, "Number of open tasks", 1, 2);

			taskNumberLbl = new Label("(" + taskList.getNumOpenTasks() + "/"
					+ taskList.getNumAllTasks() + ")");
			taskNumberProgress.addComponent(taskNumberLbl);

			Layout taskInfo = layoutHelper.getLayout();
			taskInfo.setMargin(false, false, true, false);
		}

		taskDisplay = new TaskTableDisplay(new String[] { "id", "taskname",
				"startdate", "deadline", "percentagecomplete",
				"assignUserFullName" }, new String[] { "", "Task Name",
				"Start", "Due", "% Complete", "Owner" });
		this.addComponent(taskDisplay);

		taskDisplay
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleTask task = (SimpleTask) event.getData();
						if ("taskname".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new TaskEvent.GotoRead(
											TaskDisplayComponent.this, task
													.getId()));
						} else if ("closeTask".equals(event.getFieldName())
								|| "reopenTask".equals(event.getFieldName())
								|| "pendingTask".equals(event.getFieldName())
								|| "reopenTask".equals(event.getFieldName())
								|| "deleteTask".equals(event.getFieldName())) {
							TaskDisplayComponent.this.removeAllComponents();
							ProjectTaskListService taskListService = AppContext
									.getSpringBean(ProjectTaskListService.class);
							taskList = taskListService
									.findTaskListById(taskList.getId());
							showTaskGroupInfo();
						}
					}
				});

		createTaskBtn = new Button("Add Task", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				Component comp = TaskDisplayComponent.this.getComponent(0);
				if (!(comp instanceof TaskAddPopup)) {
					TaskAddPopup taskAddView = new TaskAddPopup(
							TaskDisplayComponent.this, taskList);
					TaskDisplayComponent.this.addComponent(taskAddView, 1);
					TaskDisplayComponent.this.removeComponent(createTaskBtn);
				}
			}
		});
		createTaskBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
		createTaskBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createTaskBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		this.addComponent(createTaskBtn);

		taskDisplay.setItems(taskList.getSubTasks());
	}

	public void setSearchCriteria(TaskSearchCriteria criteria) {
		this.criteria = criteria;
		displayTasks();

	}

	private void displayTasks() {
		if (criteria == null) {
			TaskSearchCriteria criteria = new TaskSearchCriteria();
			criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			criteria.setTaskListId(new NumberSearchField(taskList.getId()));
			criteria.setStatus(new StringSearchField("Open"));
			this.criteria = criteria;
		}

		taskDisplay.setSearchCriteria(criteria);

		// Update tasklist progress and number of open task/all task
		if (taskNumberLbl != null) {
			taskNumberLbl.setValue("(" + (taskList.getNumOpenTasks() + 1) + "/"
					+ (taskList.getNumAllTasks() + 1) + ")");
		}

		int newAllTasks = taskList.getNumAllTasks() + 1;
		double newProgressTask = (taskList.getPercentageComplete() * taskList
				.getNumAllTasks()) / newAllTasks;
		if (taskListProgress != null) {
			taskListProgress.setValue(newProgressTask);
		}
	}

	public void saveTaskSuccess(SimpleTask task) {
		displayTasks();
		if (!isDisplayTaskListInfo) {
			EventBus.getInstance().fireEvent(
					new TaskListEvent.GotoRead(this, taskList.getId()));
		}
	}

	public void closeTaskAdd() {
		this.addComponent(createTaskBtn);
		Component comp = this.getComponent(1);
		if (comp instanceof TaskAddPopup) {
			this.removeComponent(comp);
		}
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ProgressPercentageIndicator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

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
	private GridFormLayoutHelper layoutHelper;

	private SimpleTaskList taskList;
	private final boolean isDisplayTaskListInfo;

	public TaskDisplayComponent(final SimpleTaskList taskList,
			final boolean isDisplayTaskListInfo) {
		this.taskList = taskList;
		this.isDisplayTaskListInfo = isDisplayTaskListInfo;
		this.setStyleName("taskdisplay-component");

		this.showTaskGroupInfo();
	}

	private void showTaskGroupInfo() {
		if (this.isDisplayTaskListInfo) {
			this.layoutHelper = new GridFormLayoutHelper(2, 3, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			this.layoutHelper.getLayout().setWidth("100%");
			this.layoutHelper.getLayout().addStyleName("colored-gridlayout");
			this.layoutHelper.getLayout().setMargin(false);
			this.addComponent(this.layoutHelper.getLayout());

			final Label descLbl = (Label) this.layoutHelper.addComponent(
					new Label(), "Description", 0, 0, 2, "100%",
					Alignment.TOP_RIGHT);
			descLbl.setContentMode(Label.CONTENT_XHTML);
			descLbl.setValue(StringUtil.preStringFormat(this.taskList
					.getDescription()));

			this.layoutHelper.addComponent(new ProjectUserFormLinkField(
					this.taskList.getOwner(), this.taskList.getOwnerAvatarId(),
					this.taskList.getOwnerFullName()), "Responsible User", 0,
					1, Alignment.TOP_RIGHT);

			final DefaultFormViewFieldFactory.FormLinkViewField milestoneLink = new DefaultFormViewFieldFactory.FormLinkViewField(
					this.taskList.getMilestoneName(),
					new Button.ClickListener() {

						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new MilestoneEvent.GotoRead(this,
											TaskDisplayComponent.this.taskList
													.getMilestoneid()));
						}
					});
			this.layoutHelper.addComponent(milestoneLink, "Phase", 1, 1,
					Alignment.TOP_RIGHT);

			this.taskListProgress = (ProgressPercentageIndicator) this.layoutHelper
					.addComponent(
							new ProgressPercentageIndicator(this.taskList
									.getPercentageComplete()), "Progress", 0, 2);
			this.taskListProgress.setWidth("100px");

			HorizontalLayout taskNumberProgress = new HorizontalLayout();
			taskNumberProgress.setSpacing(true);
			taskNumberProgress = (HorizontalLayout) this.layoutHelper
					.addComponent(taskNumberProgress, "Number of open tasks",
							1, 2);

			this.taskNumberLbl = new Label("("
					+ this.taskList.getNumOpenTasks() + "/"
					+ this.taskList.getNumAllTasks() + ")");
			taskNumberProgress.addComponent(this.taskNumberLbl);
		}

		if (ScreenSize.hasSupport1024Pixels()) {
			this.taskDisplay = new TaskTableDisplay(
					new String[] { "id", "taskname", "deadline",
							"percentagecomplete", "assignUserFullName" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_TASK_NAME_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_DUE_DATE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_PER_COMPLETE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_ASSIGNEE_HEADER) });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			this.taskDisplay = new TaskTableDisplay(
					new String[] { "id", "taskname", "startdate", "deadline",
							"percentagecomplete", "assignUserFullName" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_TASK_NAME_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_DUE_DATE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_PER_COMPLETE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_ASSIGNEE_HEADER) });
		}
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
								|| "reopenTask".equals(event.getFieldName())
								|| "deleteTask".equals(event.getFieldName())) {
							TaskDisplayComponent.this.removeAllComponents();
							final ProjectTaskListService taskListService = AppContext
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

		this.createTaskBtn = new Button("Add Task", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final Button.ClickEvent event) {

				TaskDisplayComponent.this.removeAllComponents();

				final TaskAddPopup taskAddView = new TaskAddPopup(
						TaskDisplayComponent.this,
						TaskDisplayComponent.this.taskList);
				if (TaskDisplayComponent.this.layoutHelper != null) {
					TaskDisplayComponent.this.addComponent(
							TaskDisplayComponent.this.layoutHelper.getLayout(),
							0);
					TaskDisplayComponent.this.addComponent(
							TaskDisplayComponent.this.taskDisplay, 1);
					TaskDisplayComponent.this.addComponent(taskAddView, 2);
				} else {
					TaskDisplayComponent.this.addComponent(
							TaskDisplayComponent.this.taskDisplay, 0);
					TaskDisplayComponent.this.addComponent(taskAddView, 1);
				}
				TaskDisplayComponent.this
						.removeComponent(TaskDisplayComponent.this.createTaskBtn);
			}
		});
		this.createTaskBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS));
		this.createTaskBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		this.createTaskBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
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
			this.taskListProgress.setValue(newProgressTask);
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
		Component comp;
		if (this.layoutHelper != null) {
			comp = this.getComponent(2);
		} else {
			comp = this.getComponent(1);
		}
		if (comp instanceof TaskAddPopup) {
			this.removeComponent(comp);
		}
	}
}

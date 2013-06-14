package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.file.ExportTaskStreamResource;
import com.esofthead.mycollab.module.file.FieldExportColumn;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class TaskGroupDisplayViewImpl extends AbstractView implements
		TaskGroupDisplayView {

	private PopupButton taskGroupSelection;
	private TaskGroupDisplayWidget taskLists;
	private Button reOrderBtn;

	private static final FieldExportColumn[] EXPORT_COLUMNS = new FieldExportColumn[] {
			new FieldExportColumn("taskkey",
					LocalizationHelper
							.getMessage(TaskI18nEnum.TABLE_KEY_HEADER)),
			new FieldExportColumn("taskname",
					LocalizationHelper
							.getMessage(TaskI18nEnum.TABLE_TASK_NAME_HEADER),
					40),
			new FieldExportColumn("startdate",
					LocalizationHelper
							.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER)),
			new FieldExportColumn("deadline",
					LocalizationHelper
							.getMessage(TaskI18nEnum.TABLE_DUE_DATE_HEADER)),
			new FieldExportColumn("percentagecomplete",
					LocalizationHelper
							.getMessage(TaskI18nEnum.TABLE_PER_COMPLETE_HEADER)),
			new FieldExportColumn("assignUserFullName",
					LocalizationHelper
							.getMessage(TaskI18nEnum.TABLE_ASSIGNEE_HEADER)) };

	public TaskGroupDisplayViewImpl() {
		super();
		this.setMargin(true);

		this.constructHeader();
	}

	private void constructHeader() {
		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.addStyleName("taskgroup-header");
		final VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSpacing(true);
		headerWrapper.addComponent(mainLayout);

		final HorizontalLayout header = new HorizontalLayout();
		header.setMargin(false);
		header.setSpacing(true);
		header.setWidth("100%");
		this.taskGroupSelection = new PopupButton("Active Tasks");
		this.taskGroupSelection.setEnabled(CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS));
		this.taskGroupSelection.addStyleName("link");
		this.taskGroupSelection.addStyleName("h2");
		final Embedded icon = new Embedded();
		icon.setSource(new ThemeResource("icons/24/project/task.png"));
		header.addComponent(icon);
		header.addComponent(this.taskGroupSelection);
		header.setExpandRatio(this.taskGroupSelection, 1.0f);
		header.setComponentAlignment(this.taskGroupSelection,
				Alignment.MIDDLE_LEFT);

		final VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("200px");

		final Button allTasksFilterBtn = new Button(
				LocalizationHelper
						.getMessage(TaskI18nEnum.FILTER_ALL_TASK_GROUPS_TITLE),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						TaskGroupDisplayViewImpl.this.taskGroupSelection
								.setPopupVisible(false);
						TaskGroupDisplayViewImpl.this.taskGroupSelection.setCaption(LocalizationHelper
								.getMessage(TaskI18nEnum.FILTER_ALL_TASK_GROUPS_TITLE));
						TaskGroupDisplayViewImpl.this.displayAllTaskGroups();
					}
				});
		allTasksFilterBtn.setStyleName("link");
		filterBtnLayout.addComponent(allTasksFilterBtn);

		final Button activeTasksFilterBtn = new Button(
				LocalizationHelper
						.getMessage(TaskI18nEnum.FILTER_ACTIVE_TASK_GROUPS_TITLE),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						TaskGroupDisplayViewImpl.this.taskGroupSelection
								.setPopupVisible(false);
						TaskGroupDisplayViewImpl.this.taskGroupSelection.setCaption(LocalizationHelper
								.getMessage(TaskI18nEnum.FILTER_ACTIVE_TASK_GROUPS_TITLE));
						TaskGroupDisplayViewImpl.this.displayActiveTakLists();
					}
				});
		activeTasksFilterBtn.setStyleName("link");
		filterBtnLayout.addComponent(activeTasksFilterBtn);

		final Button archievedTasksFilterBtn = new Button(
				LocalizationHelper
						.getMessage(TaskI18nEnum.FILTER_ARCHIEVED_TASK_GROUPS_TITLE),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						TaskGroupDisplayViewImpl.this.taskGroupSelection.setCaption(LocalizationHelper
								.getMessage(TaskI18nEnum.FILTER_ARCHIEVED_TASK_GROUPS_TITLE));
						TaskGroupDisplayViewImpl.this.taskGroupSelection
								.setPopupVisible(false);
						TaskGroupDisplayViewImpl.this
								.displayInActiveTaskGroups();
					}
				});
		archievedTasksFilterBtn.setStyleName("link");
		filterBtnLayout.addComponent(archievedTasksFilterBtn);

		this.taskGroupSelection.addComponent(filterBtnLayout);

		final Button newTaskListBtn = new Button(
				LocalizationHelper
						.getMessage(TaskI18nEnum.NEW_TASKGROUP_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						final TaskGroupAddWindow taskListWindow = new TaskGroupAddWindow(
								TaskGroupDisplayViewImpl.this);
						TaskGroupDisplayViewImpl.this.getWindow().addWindow(
								taskListWindow);
					}
				});
		newTaskListBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS));
		newTaskListBtn.setIcon(new ThemeResource(
				"icons/16/project/new_task_list.png"));
		newTaskListBtn.setDescription(LocalizationHelper
				.getMessage(TaskI18nEnum.NEW_TASKGROUP_ACTION));
		newTaskListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(newTaskListBtn);
		header.setComponentAlignment(newTaskListBtn, Alignment.MIDDLE_RIGHT);

		this.reOrderBtn = new Button(null, new Button.ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new TaskListEvent.ReoderTaskList(this, null));
			}
		});
		this.reOrderBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS));
		this.reOrderBtn.setIcon(new ThemeResource(
				"icons/16/project/reorder.png"));
		this.reOrderBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		this.reOrderBtn.setDescription(LocalizationHelper
				.getMessage(TaskI18nEnum.REODER_TASKGROUP_ACTION));
		header.addComponent(this.reOrderBtn);
		header.setComponentAlignment(this.reOrderBtn, Alignment.MIDDLE_RIGHT);

		final Button showGanttChartBtn = new Button(null,
				new Button.ClickListener() {

					@Override
					public void buttonClick(final ClickEvent event) {
						final TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
						searchCriteria.setProjectid(new NumberSearchField(
								CurrentProjectVariables.getProjectId()));
						EventBus.getInstance()
								.fireEvent(
										new TaskListEvent.GotoGanttChartView(
												this,
												new TaskGroupScreenData.DisplayGanttChartRequest(
														searchCriteria)));
					}
				});
		showGanttChartBtn.setEnabled(CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS));
		showGanttChartBtn.setDescription(LocalizationHelper
				.getMessage(TaskI18nEnum.DISPLAY_GANTT_CHART_ACTION));
		showGanttChartBtn.setIcon(new ThemeResource(
				"icons/16/project/gantt_chart.png"));
		showGanttChartBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(showGanttChartBtn);
		header.setComponentAlignment(showGanttChartBtn, Alignment.MIDDLE_RIGHT);

		mainLayout.addComponent(header);

		// final HorizontalLayout layoutExport = new HorizontalLayout();
		// layoutExport.setSpacing(true);
		// layoutExport.setWidth("100%");
		//
		// final Label lbEmpty = new Label("");
		// layoutExport.addComponent(lbEmpty);
		// layoutExport.setExpandRatio(lbEmpty, 1.0f);

		final Button exportBtn = new Button("Export",
				new Button.ClickListener() {

					@Override
					public void buttonClick(final ClickEvent event) {
						final String title = "Task report of Project "
								+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
										.getProject().getName() != null) ? CurrentProjectVariables
										.getProject().getName() : "");
						final TaskListSearchCriteria tasklistSearchCriteria = new TaskListSearchCriteria();
						tasklistSearchCriteria
								.setProjectId(new NumberSearchField(
										SearchField.AND,
										CurrentProjectVariables.getProject()
												.getId()));
						final Resource res = new StreamResource(
								new ExportTaskStreamResource(
										title,
										TaskGroupDisplayViewImpl.EXPORT_COLUMNS,
										AppContext
												.getSpringBean(ProjectTaskListService.class),
										tasklistSearchCriteria),
								"task_list.xls", TaskGroupDisplayViewImpl.this
										.getApplication());
						TaskGroupDisplayViewImpl.this.getWindow().open(res,
								"_blank");
					}
				});
		exportBtn.setIcon(new ThemeResource("icons/16/export_excel.png"));
		exportBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		exportBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));
		header.addComponent(exportBtn);
		header.setComponentAlignment(exportBtn, Alignment.MIDDLE_RIGHT);

		// mainLayout.addComponent(layoutExport);
		mainLayout.setWidth("100%");
		this.addComponent(headerWrapper);
		this.taskLists = new TaskGroupDisplayWidget();
		this.addComponent(this.taskLists);
	}

	private TaskListSearchCriteria createBaseSearchCriteria() {
		final TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		return criteria;
	}

	@Override
	public void displayActiveTakLists() {
		final TaskListSearchCriteria criteria = this.createBaseSearchCriteria();
		criteria.setStatus(new StringSearchField("Open"));
		this.taskLists.setSearchCriteria(criteria);
	}

	private void displayInActiveTaskGroups() {
		final TaskListSearchCriteria criteria = this.createBaseSearchCriteria();
		criteria.setStatus(new StringSearchField("Closed"));
		this.taskLists.setSearchCriteria(criteria);
	}

	private void displayAllTaskGroups() {
		final TaskListSearchCriteria criteria = this.createBaseSearchCriteria();
		this.taskLists.setSearchCriteria(criteria);
	}

	@Override
	public void insertTaskList(final SimpleTaskList taskList) {
		this.taskLists.insetItemOnBottom(taskList);
	}
}

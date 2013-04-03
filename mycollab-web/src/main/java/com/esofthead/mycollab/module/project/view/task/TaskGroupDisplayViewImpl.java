package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class TaskGroupDisplayViewImpl extends AbstractView implements
		TaskGroupDisplayView {

	private PopupButton taskGroupSelection;
	private TaskGroupDisplayWidget taskLists;
	private Button reOrderBtn;

	public TaskGroupDisplayViewImpl() {
		super();
		this.setMargin(false, true, true, true);

		constructHeader();
	}

	private void constructHeader() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSpacing(true);
		
		HorizontalLayout header = new HorizontalLayout();
		header.setMargin(true, false, false, false);
		header.setSpacing(true);
		header.setWidth("100%");
		taskGroupSelection = new PopupButton("Active Tasks");
		taskGroupSelection.setEnabled(CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS));
		taskGroupSelection.addStyleName("link");
		taskGroupSelection.addStyleName("h2");
		header.addComponent(taskGroupSelection);
		header.setExpandRatio(taskGroupSelection, 1.0f);
		header.setComponentAlignment(taskGroupSelection, Alignment.MIDDLE_LEFT);

		VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("200px");

		Button allTasksFilterBtn = new Button("All Task Groups",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						taskGroupSelection.setPopupVisible(false);
						taskGroupSelection.setCaption("All Task Groups");
						displayAllTaskGroups();
					}
				});
		allTasksFilterBtn.setStyleName("link");
		filterBtnLayout.addComponent(allTasksFilterBtn);

		Button activeTasksFilterBtn = new Button("Active Task Groups Only",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						taskGroupSelection.setPopupVisible(false);
						taskGroupSelection.setCaption("Active Task Groups");
						displayActiveTakLists();
					}
				});
		activeTasksFilterBtn.setStyleName("link");
		filterBtnLayout.addComponent(activeTasksFilterBtn);

		Button archievedTasksFilterBtn = new Button(
				"Archieved Task Groups Only", new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						taskGroupSelection.setCaption("Archieved Task Groups");
						taskGroupSelection.setPopupVisible(false);
						displayInActiveTaskGroups();
					}
				});
		archievedTasksFilterBtn.setStyleName("link");
		filterBtnLayout.addComponent(archievedTasksFilterBtn);

		taskGroupSelection.addComponent(filterBtnLayout);

		// Search button
		// HorizontalLayout basicSearchBody = new HorizontalLayout();
		// basicSearchBody.setSpacing(true);
		// basicSearchBody.setMargin(true);

		// final TextField nameField = new TextField();
		// nameField.addListener(new TextChangeListener() {
		// @Override
		// public void textChange(TextChangeEvent event) {
		//
		// String textSearch = event.getText().toString().trim();
		// }
		// });
		//
		// nameField.setTextChangeEventMode(TextChangeEventMode.LAZY);
		// nameField.setTextChangeTimeout(200);
		// nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
		// basicSearchBody.addComponent(nameField);

		// Button searchBtn = new Button();
		// searchBtn.addListener(new Button.ClickListener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void buttonClick(Button.ClickEvent event) {
		//
		// }
		// });
		// searchBtn.setIcon(new ThemeResource("icons/22/search.png"));
		// searchBtn.setStyleName("link");
		// basicSearchBody.addComponent(searchBtn);
		// header.addComponent(basicSearchBody);
		// header.setComponentAlignment(basicSearchBody,
		// Alignment.MIDDLE_RIGHT);

		Button newTaskListBtn = new Button(
				LocalizationHelper
						.getMessage(TaskI18nEnum.NEW_TASKGROUP_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						TaskGroupAddWindow taskListWindow = new TaskGroupAddWindow(
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

		reOrderBtn = new Button(null, new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new TaskListEvent.ReoderTaskList(this, null));
			}
		});
		reOrderBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS));
		reOrderBtn.setIcon(new ThemeResource("icons/16/project/reorder.png"));
		reOrderBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		reOrderBtn.setDescription(LocalizationHelper
				.getMessage(TaskI18nEnum.REODER_TASKGROUP_ACTION));
		header.addComponent(reOrderBtn);
		header.setComponentAlignment(reOrderBtn, Alignment.MIDDLE_RIGHT);

		Button showGanttChartBtn = new Button(null, new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
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
		
		HorizontalLayout layoutExport = new HorizontalLayout();
		layoutExport.setSpacing(true);
		layoutExport.setWidth("100%");
		
		Label lbEmpty = new Label("");
		layoutExport.addComponent(lbEmpty);
		layoutExport.setExpandRatio(lbEmpty, 1.0f);
		
		Button exportBtn = new Button("Export");
		exportBtn.setIcon(new ThemeResource(
		"icons/16/export_excel.png"));
		exportBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		exportBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));
		layoutExport.addComponent(exportBtn);
		
		mainLayout.addComponent(layoutExport);
		this.addComponent(mainLayout);
		taskLists = new TaskGroupDisplayWidget();
		this.addComponent(taskLists);
	}

	private TaskListSearchCriteria createBaseSearchCriteria() {
		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		return criteria;
	}

	@Override
	public void displayActiveTakLists() {
		TaskListSearchCriteria criteria = createBaseSearchCriteria();
		criteria.setStatus(new StringSearchField("Open"));
		taskLists.setSearchCriteria(criteria);
	}

	private void displayInActiveTaskGroups() {
		TaskListSearchCriteria criteria = createBaseSearchCriteria();
		criteria.setStatus(new StringSearchField("Closed"));
		taskLists.setSearchCriteria(criteria);
	}

	private void displayAllTaskGroups() {
		TaskListSearchCriteria criteria = createBaseSearchCriteria();
		taskLists.setSearchCriteria(criteria);
	}

	@Override
	public void insertTaskList(SimpleTaskList taskList) {
		taskLists.insetItemOnBottom(taskList);
	}
}

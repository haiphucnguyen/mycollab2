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

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.file.resource.ExportTaskListStreamResource;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Sizeable;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class TaskGroupDisplayViewImpl extends AbstractPageView implements
		TaskGroupDisplayView {
	private static final long serialVersionUID = 1L;

	private PopupButton taskGroupSelection;
	private TaskGroupDisplayWidget taskLists;
	private Button reOrderBtn;
	private Button viewGanttChartBtn;

	private PopupButton exportButtonControl;
	private VerticalLayout rightColumn;
	private VerticalLayout leftColumn;

	public TaskGroupDisplayViewImpl() {
		super();
		this.setMargin(new MarginInfo(false, true, true, true));
		this.setSpacing(true);

		this.constructUI();
	}

	private void constructUI() {
		final HorizontalLayout header = new HorizontalLayout();
		header.setMargin(new MarginInfo(true, false, true, false));
		header.setStyleName("hdr-view");
		header.setSpacing(true);
		header.setWidth("100%");
		header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		this.taskGroupSelection = new PopupButton("Active Tasks");
		this.taskGroupSelection.setEnabled(CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.TASKS));
		this.taskGroupSelection.addStyleName("link");
		this.taskGroupSelection.addStyleName("hdr-text");
		final Image icon = new Image(null,
				MyCollabResource.newResource("icons/24/project/task.png"));
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
						TaskGroupDisplayViewImpl.this.displayActiveTaskGroups();
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

		this.taskGroupSelection.setContent(filterBtnLayout);

		final Button newTaskListBtn = new Button(
				LocalizationHelper
						.getMessage(TaskI18nEnum.NEW_TASKGROUP_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						final TaskGroupAddWindow taskListWindow = new TaskGroupAddWindow(
								TaskGroupDisplayViewImpl.this);
						UI.getCurrent().addWindow(taskListWindow);
					}
				});
		newTaskListBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS));
		newTaskListBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		newTaskListBtn.setDescription(LocalizationHelper
				.getMessage(TaskI18nEnum.NEW_TASKGROUP_ACTION));
		newTaskListBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		header.addComponent(newTaskListBtn);
		header.setComponentAlignment(newTaskListBtn, Alignment.MIDDLE_RIGHT);

		this.reOrderBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new TaskListEvent.ReoderTaskList(this, null));
			}
		});
		this.reOrderBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS));
		this.reOrderBtn.setIcon(MyCollabResource
				.newResource("icons/16/project/reorder.png"));
		this.reOrderBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
		this.reOrderBtn.setDescription(LocalizationHelper
				.getMessage(TaskI18nEnum.REODER_TASKGROUP_ACTION));
		header.addComponent(this.reOrderBtn);
		header.setComponentAlignment(this.reOrderBtn, Alignment.MIDDLE_RIGHT);

		this.addComponent(header);

		exportButtonControl = new PopupButton();
		exportButtonControl.addStyleName(UIConstants.THEME_GRAY_LINK);
		exportButtonControl.setIcon(MyCollabResource
				.newResource("icons/16/export.png"));
		exportButtonControl.setDescription("Export to file");

		VerticalLayout popupButtonsControl = new VerticalLayout();
		exportButtonControl.setContent(popupButtonsControl);
		exportButtonControl.setWidth(Sizeable.SIZE_UNDEFINED, Unit.PIXELS);

		Button exportPdfBtn = new Button("Pdf");
		FileDownloader pdfDownloader = new FileDownloader(
				constructStreamResource(ReportExportType.PDF));
		pdfDownloader.extend(exportPdfBtn);
		exportPdfBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/pdf.png"));
		exportPdfBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportPdfBtn);

		Button exportExcelBtn = new Button("Excel");
		FileDownloader excelDownloader = new FileDownloader(
				constructStreamResource(ReportExportType.EXCEL));
		excelDownloader.extend(exportExcelBtn);
		exportExcelBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/excel.png"));
		exportExcelBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportExcelBtn);

		header.addComponent(exportButtonControl);
		header.setComponentAlignment(exportButtonControl,
				Alignment.MIDDLE_RIGHT);

		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		this.taskLists = new TaskGroupDisplayWidget();

		this.leftColumn = new VerticalLayout();
		this.leftColumn.addComponent(taskLists);
		this.leftColumn.setMargin(new MarginInfo(false, true, false, false));

		this.rightColumn = new VerticalLayout();
		this.rightColumn.setWidth("300px");

		mainLayout.addComponent(leftColumn);
		mainLayout.addComponent(rightColumn);
		mainLayout.setExpandRatio(leftColumn, 1.0f);
		this.addComponent(mainLayout);

	}

	private StreamResource constructStreamResource(ReportExportType exportType) {
		final String title = "Task report of Project "
				+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
						.getProject().getName() != null) ? CurrentProjectVariables
						.getProject().getName() : "");

		final TaskListSearchCriteria tasklistSearchCriteria = new TaskListSearchCriteria();
		tasklistSearchCriteria.setProjectId(new NumberSearchField(
				SearchField.AND, CurrentProjectVariables.getProject().getId()));

		StreamResource res = null;
		if (exportType.equals(ReportExportType.PDF)) {
			res = new StreamResource(new ExportTaskListStreamResource(title,
					exportType,
					ApplicationContextUtil
							.getSpringBean(ProjectTaskListService.class),
					tasklistSearchCriteria, null), "task_list.pdf");
		} else if (exportType.equals(ReportExportType.CSV)) {
			res = new StreamResource(new ExportTaskListStreamResource(title,
					exportType,
					ApplicationContextUtil
							.getSpringBean(ProjectTaskListService.class),
					tasklistSearchCriteria, null), "task_list.csv");
		} else {
			res = new StreamResource(new ExportTaskListStreamResource(title,
					exportType,
					ApplicationContextUtil
							.getSpringBean(ProjectTaskListService.class),
					tasklistSearchCriteria, null), "task_list.xls");
		}

		return res;
	}

	private TaskListSearchCriteria createBaseSearchCriteria() {
		final TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		return criteria;
	}

	@Override
	public void displayTaskList() {
		displayActiveTaskGroups();
		displayTaskStatistic();
	}

	private void displayTaskStatistic() {
		rightColumn.removeAllComponents();

		UnresolvedTaskByAssigneeWidget unresolvedTaskByAssigneeWidget = new UnresolvedTaskByAssigneeWidget();
		rightColumn.addComponent(unresolvedTaskByAssigneeWidget);

		TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
		searchCriteria.setProjectid(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		searchCriteria.setStatuses(new SetSearchField<String>(SearchField.AND,
				new String[] { "Open" }));

		unresolvedTaskByAssigneeWidget.setSearchCriteria(searchCriteria);

		UnresolvedTaskByPriorityWidget unresolvedTaskByPriorityWidget = new UnresolvedTaskByPriorityWidget();
		rightColumn.addComponent(unresolvedTaskByPriorityWidget);
		unresolvedTaskByPriorityWidget.setSearchCriteria(searchCriteria);
	}

	private void displayActiveTaskGroups() {
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

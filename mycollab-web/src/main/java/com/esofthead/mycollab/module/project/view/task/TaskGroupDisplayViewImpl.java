package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
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
		HorizontalLayout header = new HorizontalLayout();
		header.setMargin(true, false, false, false);
		header.setSpacing(true);
		header.setWidth("100%");
		taskGroupSelection = new PopupButton("Active Tasks");
		taskGroupSelection.addStyleName("link");
		taskGroupSelection.addStyleName("h2");
		header.addComponent(taskGroupSelection);
		header.setExpandRatio(taskGroupSelection, 1.0f);

		VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("200px");

		Button allTasksFilterBtn = new Button("All Tasks",
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

		Button activeTasksFilterBtn = new Button("Active Tasks Only",
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

		Button archievedTasksFilterBtn = new Button("Archieved Tasks Only",
				new Button.ClickListener() {
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

		reOrderBtn = new Button("Reorder", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new TaskListEvent.ReoderTaskList(this, null));
			}
		});
		reOrderBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(reOrderBtn);
		header.setComponentAlignment(reOrderBtn, Alignment.MIDDLE_RIGHT);

		Button newTaskListBtn = new Button("New Task List",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						TaskGroupAddWindow taskListWindow = new TaskGroupAddWindow(
								TaskGroupDisplayViewImpl.this);
						TaskGroupDisplayViewImpl.this.getWindow().addWindow(
								taskListWindow);
					}
				});
		newTaskListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(newTaskListBtn);
		header.setComponentAlignment(newTaskListBtn, Alignment.MIDDLE_RIGHT);

		this.addComponent(header);
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
		taskLists.insertItemOnTop(taskList);
	}
}

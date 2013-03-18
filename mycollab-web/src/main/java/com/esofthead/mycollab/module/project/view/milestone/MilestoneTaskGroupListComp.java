package com.esofthead.mycollab.module.project.view.milestone;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.view.task.TaskGroupDisplayWidget;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class MilestoneTaskGroupListComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private Milestone milestone;
	private PopupButton taskGroupSelection;
	private TaskGroupDisplayWidget taskGroupDisplayWidget;

	public MilestoneTaskGroupListComp() {
		this.setMargin(true);
		constructHeader();
		taskGroupDisplayWidget = new TaskGroupDisplayWidget();
		this.addComponent(taskGroupDisplayWidget);
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
		this.addComponent(header);
	}

	public void displayTakLists(Milestone milestone) {
		this.milestone = milestone;
		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setStatus(new StringSearchField("Open"));
		criteria.setMilestoneIds(new SetSearchField<Integer>(milestone.getId()));

		taskGroupDisplayWidget.setSearchCriteria(criteria);
	}

	private TaskListSearchCriteria createBaseSearchCriteria() {
		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setMilestoneIds(new SetSearchField<Integer>(milestone.getId()));
		return criteria;
	}

	public void displayActiveTakLists() {
		TaskListSearchCriteria criteria = createBaseSearchCriteria();
		criteria.setStatus(new StringSearchField("Open"));
		taskGroupDisplayWidget.setSearchCriteria(criteria);
	}

	private void displayInActiveTaskGroups() {
		TaskListSearchCriteria criteria = createBaseSearchCriteria();
		criteria.setStatus(new StringSearchField("Closed"));
		taskGroupDisplayWidget.setSearchCriteria(criteria);
	}

	private void displayAllTaskGroups() {
		TaskListSearchCriteria criteria = createBaseSearchCriteria();
		taskGroupDisplayWidget.setSearchCriteria(criteria);
	}

}

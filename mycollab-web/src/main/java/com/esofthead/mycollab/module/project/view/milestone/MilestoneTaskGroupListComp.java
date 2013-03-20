package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.view.task.TaskDisplayWidget;
import com.esofthead.mycollab.module.project.view.task.TaskGroupDisplayWidget;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MilestoneTaskGroupListComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private Milestone milestone;

	public MilestoneTaskGroupListComp() {
		this.setMargin(true);
		constructHeader();
	}

	private void constructHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.setMargin(true, false, false, false);
		header.setSpacing(true);
		header.setWidth("100%");
		Label taskGroupSelection = new Label("Tasks");
		taskGroupSelection.addStyleName("h2");
		header.addComponent(taskGroupSelection);
		header.setExpandRatio(taskGroupSelection, 1.0f);
		header.setComponentAlignment(taskGroupSelection, Alignment.MIDDLE_LEFT);

		ToggleButtonGroup viewGroup = new ToggleButtonGroup();

		Button simpleDisplay = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				displaySimpleView();
			}
		});
		simpleDisplay.setIcon(new ThemeResource(
				"icons/16/project/list_display.png"));

		viewGroup.addButton(simpleDisplay);

		Button advanceDisplay = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				displayAdvancedView();
			}
		});
		advanceDisplay.setIcon(new ThemeResource(
				"icons/16/project/advanced_display.png"));
		viewGroup.addButton(advanceDisplay);
		header.addComponent(viewGroup);
		header.setComponentAlignment(viewGroup, Alignment.MIDDLE_RIGHT);
		this.addComponent(header);
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

		TaskGroupDisplayWidget taskGroupDisplayWidget = new TaskGroupDisplayWidget();
		this.addComponent(taskGroupDisplayWidget);
		taskGroupDisplayWidget.setSearchCriteria(criteria);
	}

	public void displayTakLists(Milestone milestone) {
		this.milestone = milestone;
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

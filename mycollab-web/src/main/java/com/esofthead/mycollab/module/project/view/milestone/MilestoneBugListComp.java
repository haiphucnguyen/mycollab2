package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.view.bug.BugChartComponent;
import com.esofthead.mycollab.module.project.view.bug.BugListWidget;
import com.esofthead.mycollab.module.project.view.bug.BugSimpleDisplayWidget;
import com.esofthead.mycollab.module.project.view.bug.IBugReportDisplayContainer;
import com.esofthead.mycollab.module.project.view.bug.UnresolvedBugsByAssigneeWidget;
import com.esofthead.mycollab.module.project.view.bug.UnresolvedBugsByPriorityWidget;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MilestoneBugListComp extends VerticalLayout implements
		IBugReportDisplayContainer {
	private static final long serialVersionUID = 1L;

	private Milestone milestone;
	private ToggleButtonGroup viewGroup;

	public MilestoneBugListComp() {
		this.setMargin(true);
		constructHeader();
	}

	private void constructHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.setMargin(true, false, false, false);
		header.setSpacing(true);
		header.setWidth("100%");
		Label taskGroupSelection = new Label("Related Bugs");
		taskGroupSelection.addStyleName("h2");
		taskGroupSelection.addStyleName(UIConstants.THEME_NO_BORDER);
		header.addComponent(taskGroupSelection);
		header.setExpandRatio(taskGroupSelection, 1.0f);
		header.setComponentAlignment(taskGroupSelection, Alignment.MIDDLE_LEFT);

		viewGroup = new ToggleButtonGroup();

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
				"icons/16/project/bug_advanced_display.png"));
		viewGroup.addButton(advanceDisplay);
		header.addComponent(viewGroup);
		header.setComponentAlignment(viewGroup, Alignment.MIDDLE_RIGHT);
		this.addComponent(header);
	}

	public void displayBugs(Milestone milestone) {
		this.milestone = milestone;
		displayBugReports();
	}

	private void displaySimpleView() {
		if (this.getComponentCount() > 1) {
			this.removeComponent(this.getComponent(1));
		}

		BugSearchCriteria criteria = new BugSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setMilestoneIds(new SetSearchField<Integer>(milestone.getId()));

		BugSimpleDisplayWidget displayWidget = new BugSimpleDisplayWidget();
		this.addComponent(new LazyLoadWrapper(displayWidget));
		displayWidget.setSearchCriteria(criteria);
	}

	private void displayAdvancedView() {

		if (this.getComponentCount() > 1) {
			this.removeComponent(this.getComponent(1));
		}

		HorizontalLayout bodyLayout = new HorizontalLayout();
		bodyLayout.setSpacing(true);
		VerticalLayout leftColumn = new VerticalLayout();
		bodyLayout.addComponent(leftColumn);
		VerticalLayout rightColumn = new VerticalLayout();
		bodyLayout.addComponent(rightColumn);

		UnresolvedBugsByPriorityWidget unresolvedBugWidget = new UnresolvedBugsByPriorityWidget(
				this);
		unresolvedBugWidget.setWidth("400px");
		leftColumn.addComponent(unresolvedBugWidget);

		BugSearchCriteria unresolvedByPrioritySearchCriteria = new BugSearchCriteria();
		unresolvedByPrioritySearchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		unresolvedByPrioritySearchCriteria
				.setMilestoneIds(new SetSearchField<Integer>(milestone.getId()));
		unresolvedByPrioritySearchCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		unresolvedBugWidget
				.setSearchCriteria(unresolvedByPrioritySearchCriteria);

		UnresolvedBugsByAssigneeWidget unresolvedByAssigneeWidget = new UnresolvedBugsByAssigneeWidget(
				this);
		unresolvedByAssigneeWidget.setWidth("400px");
		leftColumn.addComponent(unresolvedByAssigneeWidget);

		BugSearchCriteria unresolvedByAssigneeSearchCriteria = new BugSearchCriteria();
		unresolvedByAssigneeSearchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		unresolvedByAssigneeSearchCriteria
				.setMilestoneIds(new SetSearchField<Integer>(milestone.getId()));
		unresolvedByAssigneeSearchCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		unresolvedByAssigneeWidget
				.setSearchCriteria(unresolvedByAssigneeSearchCriteria);

		BugSearchCriteria chartSearchCriteria = new BugSearchCriteria();
		chartSearchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		chartSearchCriteria.setMilestoneIds(new SetSearchField<Integer>(
				milestone.getId()));
		BugChartComponent bugChartComponent = new BugChartComponent(
				chartSearchCriteria);
		rightColumn.addComponent(bugChartComponent);

		this.addComponent(bodyLayout);
	}

	@Override
	public void displayBugReports() {
		viewGroup.setDefaultSelectionByIndex(1);
		displayAdvancedView();
	}

	@Override
	public void displayBugListWidget(String title, BugSearchCriteria criteria) {
		if (this.getComponentCount() > 1) {
			this.removeComponent(this.getComponent(1));
		}
		BugListWidget bugListWidget = new BugListWidget(title,
				"Back to milestone dashboard", criteria, this);
		bugListWidget.setWidth("100%");
		this.addComponent(bugListWidget);
	}

}

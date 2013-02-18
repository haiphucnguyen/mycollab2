package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.view.bug.BugListWidget;
import com.esofthead.mycollab.module.project.view.bug.BugTableDisplay;
import com.esofthead.mycollab.module.project.view.bug.IBugReportDisplayContainer;
import com.esofthead.mycollab.module.project.view.bug.UnresolvedBugsByAssigneeWidget;
import com.esofthead.mycollab.module.project.view.bug.UnresolvedBugsByPriorityWidget;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class MilestoneBugListComp extends VerticalLayout implements
		IBugReportDisplayContainer {
	private static final long serialVersionUID = 1L;

	private Milestone milestone;
	private BugTableDisplay bugTableDisplay;

	public MilestoneBugListComp() {
		bugTableDisplay = new BugTableDisplay(new String[] { "summary",
				"severity", "resolution", "duedate", "assignuserFullName" },
				new String[] { "Summary", "Severity", "Resolution", "Due",
						"Assignee" });
		this.addComponent(bugTableDisplay);
	}

	public void displayBugs(Milestone milestone) {
		this.milestone = milestone;
		BugSearchCriteria criteria = new BugSearchCriteria();
		criteria.setProjectId(new NumberSearchField(milestone.getProjectid()));
		criteria.setMilestoneIds(new SetSearchField<Integer>(milestone.getId()));

		bugTableDisplay.setSearchCriteria(criteria);
		displayBugReports();
	}

	@Override
	public void displayBugReports() {
		this.removeAllComponents();
		HorizontalLayout bodyLayout = new HorizontalLayout();
		VerticalLayout leftColumn = new VerticalLayout();
		bodyLayout.addComponent(leftColumn);
		UnresolvedBugsByPriorityWidget unresolvedBugWidget = new UnresolvedBugsByPriorityWidget(
				this);
		unresolvedBugWidget.setWidth("450px");
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

		VerticalLayout rightColumn = new VerticalLayout();
		bodyLayout.addComponent(rightColumn);

		UnresolvedBugsByAssigneeWidget unresolvedByAssigneeWidget = new UnresolvedBugsByAssigneeWidget(
				this);
		unresolvedByAssigneeWidget.setWidth("450px");
		rightColumn.addComponent(unresolvedByAssigneeWidget);

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
		this.addComponent(bodyLayout);
	}

	@Override
	public void displayBugListWidget(String title, BugSearchCriteria criteria) {
		this.removeAllComponents();
		BugListWidget bugListWidget = new BugListWidget(title,
				"Back to milestone dashboard", criteria, this);
		bugListWidget.setWidth("100%");
		this.addComponent(bugListWidget);
	}

}

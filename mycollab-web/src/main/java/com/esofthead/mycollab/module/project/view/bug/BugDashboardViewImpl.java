package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import org.vaadin.hene.splitbutton.SplitButtonExt;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class BugDashboardViewImpl extends AbstractView implements
		BugDashboardView {

	private VerticalLayout leftColumn, rightColumn;

	public BugDashboardViewImpl() {
		super();
		this.setMargin(true);
		initUI();
	}

	private void initUI() {
		HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");

		Label title = new Label("Bug Dashboard");
		title.setStyleName("h2");
		header.addComponent(title);
		header.setExpandRatio(title, 0.5f);

		ButtonGroup navButton = new ButtonGroup();
		Button bugListBtn = new Button("Bugs", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new BugEvent.GotoList(this, null));
			}
		});

		navButton.addButton(bugListBtn);

		Button componentListBtn = new Button("Components",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoList(this, null));
					}
				});

		navButton.addButton(componentListBtn);

		Button versionListBtn = new Button("Versions",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoList(this, null));
					}
				});

		navButton.addButton(versionListBtn);

		header.addComponent(navButton);
		header.setExpandRatio(navButton, 0.5f);

		Button createBugBtn = new Button("Create Bug",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoAdd(this, null));
					}
				});
		createBugBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));

		final SplitButtonExt controlsBtn = new SplitButtonExt(createBugBtn);
		controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
		controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);

		VerticalLayout btnControlsLayout = new VerticalLayout();
		btnControlsLayout.setWidth("150px");
		Button createComponentBtn = new Button("Create Component",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoAdd(this, null));
					}
				});
		createComponentBtn.setStyleName("link");
		createComponentBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.COMPONENTS));
		btnControlsLayout.addComponent(createComponentBtn);

		Button createVersionBtn = new Button("Create Version",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoAdd(this, null));
					}
				});
		createVersionBtn.setStyleName("link");
		createVersionBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.VERSIONS));
		btnControlsLayout.addComponent(createVersionBtn);
		controlsBtn.addComponent(btnControlsLayout);

		header.addComponent(controlsBtn);

		header.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);

		this.addComponent(header);

		HorizontalLayout body = new HorizontalLayout();
		body.setWidth("100%");
		body.setSpacing(true);

		leftColumn = new VerticalLayout();
		leftColumn.setSpacing(true);
		body.addComponent(leftColumn);
		body.setExpandRatio(leftColumn, 1.0f);

		rightColumn = new VerticalLayout();
		rightColumn.setSpacing(true);
		if (ScreenSize.hasSupport1024Pixels()) {
			rightColumn.setWidth("310px");
		} else if (ScreenSize.hasSupport1280Pixels()) {
			rightColumn.setWidth("400px");
		}
		body.addComponent(rightColumn);
		body.setComponentAlignment(rightColumn, Alignment.TOP_RIGHT);

		this.addComponent(body);
	}

	@Override
	public void attach() {
		leftColumn.removeAllComponents();
		rightColumn.removeAllComponents();

		SimpleProject project = CurrentProjectVariables.getProject();

		MyBugListWidget myBugListWidget = new MyBugListWidget();
		LazyLoadWrapper myBugsWidgetWrapper = new LazyLoadWrapper(
				myBugListWidget);
		leftColumn.addComponent(myBugsWidgetWrapper);
		BugSearchCriteria myBugsSearchCriteria = new BugSearchCriteria();
		myBugsSearchCriteria
				.setProjectId(new NumberSearchField(project.getId()));
		myBugsSearchCriteria.setStatuses(new SetSearchField<String>(
				SearchField.AND, new String[] { BugStatusConstants.INPROGRESS,
						BugStatusConstants.OPEN, BugStatusConstants.REOPENNED,
						BugStatusConstants.TESTPENDING }));
		myBugsSearchCriteria.setAssignuser(new StringSearchField(AppContext
				.getUsername()));

		myBugListWidget.setSearchCriteria(myBugsSearchCriteria);

		DueBugWidget dueBugWidget = new DueBugWidget();
		LazyLoadWrapper dueBugWidgetWrapper = new LazyLoadWrapper(dueBugWidget);
		leftColumn.addComponent(dueBugWidgetWrapper);
		BugSearchCriteria dueDefectsCriteria = new BugSearchCriteria();
		dueDefectsCriteria.setProjectId(new NumberSearchField(project.getId()));
		dueDefectsCriteria.setDueDate(new DateTimeSearchField(SearchField.AND,
				DateTimeSearchField.LESSTHANEQUAL, new GregorianCalendar()
						.getTime()));
		dueDefectsCriteria.setStatuses(new SetSearchField<String>(
				SearchField.AND, new String[] { BugStatusConstants.INPROGRESS,
						BugStatusConstants.OPEN, BugStatusConstants.REOPENNED,
						BugStatusConstants.TESTPENDING }));
		dueBugWidget.setSearchCriteria(dueDefectsCriteria);

		RecentBugUpdateWidget updateBugWidget = new RecentBugUpdateWidget();
		LazyLoadWrapper updateBugWidgetWrapper = new LazyLoadWrapper(
				updateBugWidget);
		leftColumn.addComponent(updateBugWidgetWrapper);

		// Unresolved by assignee
		UnresolvedBugsByAssigneeWidget2 unresolvedByAssigneeWidget = new UnresolvedBugsByAssigneeWidget2();
		BugSearchCriteria unresolvedByAssigneeSearchCriteria = new BugSearchCriteria();
		unresolvedByAssigneeSearchCriteria.setProjectId(new NumberSearchField(
				project.getId()));
		unresolvedByAssigneeSearchCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		unresolvedByAssigneeWidget
				.setSearchCriteria(unresolvedByAssigneeSearchCriteria);
		rightColumn
				.addComponent(new LazyLoadWrapper(unresolvedByAssigneeWidget));

		// Unresolve by priority widget
		UnresolvedBugsByPriorityWidget2 unresolvedByPriorityWidget = new UnresolvedBugsByPriorityWidget2();
		BugSearchCriteria unresolvedByPrioritySearchCriteria = new BugSearchCriteria();
		unresolvedByPrioritySearchCriteria.setProjectId(new NumberSearchField(
				project.getId()));
		unresolvedByPrioritySearchCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		unresolvedByPriorityWidget
				.setSearchCriteria(unresolvedByPrioritySearchCriteria);
		rightColumn
				.addComponent(new LazyLoadWrapper(unresolvedByPriorityWidget));

		// bug chart
		BugSearchCriteria recentDefectsCriteria = new BugSearchCriteria();
		recentDefectsCriteria.setProjectId(new NumberSearchField(project
				.getId()));
		updateBugWidget.setSearchCriteria(recentDefectsCriteria);

		BugSearchCriteria chartSearchCriteria = new BugSearchCriteria();
		chartSearchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		BugChartComponent bugChartComponent = null;
		if (ScreenSize.hasSupport1024Pixels()) {
			bugChartComponent = new BugChartComponent(chartSearchCriteria, 300, 200);
		} else if (ScreenSize.hasSupport1280Pixels()) {
			bugChartComponent = new BugChartComponent(chartSearchCriteria, 400, 200);
		}
		rightColumn.addComponent(bugChartComponent);
	}
}

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
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
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
		this.initUI();
	}

	private void initUI() {
		final VerticalLayout headerWrapper = new VerticalLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.addStyleName("bugdashboard-header");
		final CssLayout headerTop = new CssLayout();
		headerTop.setWidth("100%");
		headerTop.addStyleName("bugdashboard-header-top");
		final HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");

		final Label title = new Label(
				LocalizationHelper.getMessage(BugI18nEnum.BUG_DASHBOARD_TITLE));
		title.setStyleName("h2");
		final Embedded icon = new Embedded();
		icon.setSource(MyCollabResource.newResource("icons/24/project/bug.png"));
		header.addComponent(icon);
		header.addComponent(title);
		header.setExpandRatio(title, 1.0f);
		header.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
		header.setSpacing(true);

		final Button createBugBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.NEW_BUG_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoAdd(this, null));
					}
				});
		createBugBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));

		final SplitButtonExt controlsBtn = new SplitButtonExt(createBugBtn);
		controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
		controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);

		final VerticalLayout btnControlsLayout = new VerticalLayout();
		btnControlsLayout.setWidth("150px");
		final Button createComponentBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.NEW_COMPONENT_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoAdd(this, null));
					}
				});
		createComponentBtn.setStyleName("link");
		createComponentBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.COMPONENTS));
		btnControlsLayout.addComponent(createComponentBtn);

		final Button createVersionBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.NEW_VERSION_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
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

		headerTop.addComponent(header);
		headerWrapper.addComponent(headerTop);

		final VerticalLayout headerContent = new VerticalLayout();
		headerContent.setWidth("100%");
		headerContent.setMargin(true);
		final ButtonGroup navButton = new ButtonGroup();
		final Button bugListBtn = new Button("Bugs",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoList(this, null));
					}
				});

		navButton.addButton(bugListBtn);

		final Button componentListBtn = new Button("Components",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoList(this, null));
					}
				});

		navButton.addButton(componentListBtn);

		final Button versionListBtn = new Button("Versions",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoList(this, null));
					}
				});

		navButton.addButton(versionListBtn);

		headerContent.addComponent(navButton);
		headerContent.setComponentAlignment(navButton, Alignment.MIDDLE_CENTER);

		headerWrapper.addComponent(headerContent);

		this.addComponent(headerWrapper);

		final HorizontalLayout body = new HorizontalLayout();
		body.setWidth("100%");
		body.setSpacing(true);

		this.leftColumn = new VerticalLayout();
		this.leftColumn.setSpacing(true);
		body.addComponent(this.leftColumn);
		body.setExpandRatio(this.leftColumn, 1.0f);

		this.rightColumn = new VerticalLayout();
		this.rightColumn.setSpacing(true);

		body.addComponent(this.rightColumn);
		body.setComponentAlignment(this.rightColumn, Alignment.TOP_RIGHT);

		this.addComponent(body);
	}

	@Override
	public void displayDashboard() {
		this.leftColumn.removeAllComponents();
		this.rightColumn.removeAllComponents();

		if (ScreenSize.hasSupport1024Pixels()) {
			this.rightColumn.setWidth("310px");
		} else if (ScreenSize.hasSupport1280Pixels()) {
			this.rightColumn.setWidth("400px");
		}

		final SimpleProject project = CurrentProjectVariables.getProject();

		final MyBugListWidget myBugListWidget = new MyBugListWidget();
		final LazyLoadWrapper myBugsWidgetWrapper = new LazyLoadWrapper(
				myBugListWidget);
		this.leftColumn.addComponent(myBugsWidgetWrapper);
		final BugSearchCriteria myBugsSearchCriteria = new BugSearchCriteria();
		myBugsSearchCriteria
				.setProjectId(new NumberSearchField(project.getId()));
		myBugsSearchCriteria.setStatuses(new SetSearchField<String>(
				SearchField.AND, new String[] { BugStatusConstants.INPROGRESS,
						BugStatusConstants.OPEN, BugStatusConstants.REOPENNED,
						BugStatusConstants.TESTPENDING }));
		myBugsSearchCriteria.setAssignuser(new StringSearchField(AppContext
				.getUsername()));

		myBugListWidget.setSearchCriteria(myBugsSearchCriteria);

		final DueBugWidget dueBugWidget = new DueBugWidget();
		final LazyLoadWrapper dueBugWidgetWrapper = new LazyLoadWrapper(
				dueBugWidget);
		this.leftColumn.addComponent(dueBugWidgetWrapper);
		final BugSearchCriteria dueDefectsCriteria = new BugSearchCriteria();
		dueDefectsCriteria.setProjectId(new NumberSearchField(project.getId()));
		dueDefectsCriteria.setDueDate(new DateTimeSearchField(SearchField.AND,
				DateTimeSearchField.LESSTHANEQUAL, new GregorianCalendar()
						.getTime()));
		dueDefectsCriteria.setStatuses(new SetSearchField<String>(
				SearchField.AND, new String[] { BugStatusConstants.INPROGRESS,
						BugStatusConstants.OPEN, BugStatusConstants.REOPENNED,
						BugStatusConstants.TESTPENDING }));
		dueBugWidget.setSearchCriteria(dueDefectsCriteria);

		final RecentBugUpdateWidget updateBugWidget = new RecentBugUpdateWidget();
		final LazyLoadWrapper updateBugWidgetWrapper = new LazyLoadWrapper(
				updateBugWidget);
		this.leftColumn.addComponent(updateBugWidgetWrapper);

		// Unresolved by assignee
		final UnresolvedBugsByAssigneeWidget2 unresolvedByAssigneeWidget = new UnresolvedBugsByAssigneeWidget2();
		final BugSearchCriteria unresolvedByAssigneeSearchCriteria = new BugSearchCriteria();
		unresolvedByAssigneeSearchCriteria.setProjectId(new NumberSearchField(
				project.getId()));
		unresolvedByAssigneeSearchCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		unresolvedByAssigneeWidget
				.setSearchCriteria(unresolvedByAssigneeSearchCriteria);
		this.rightColumn.addComponent(new LazyLoadWrapper(
				unresolvedByAssigneeWidget));

		// Unresolve by priority widget
		final UnresolvedBugsByPriorityWidget2 unresolvedByPriorityWidget = new UnresolvedBugsByPriorityWidget2();
		final BugSearchCriteria unresolvedByPrioritySearchCriteria = new BugSearchCriteria();
		unresolvedByPrioritySearchCriteria.setProjectId(new NumberSearchField(
				project.getId()));
		unresolvedByPrioritySearchCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		unresolvedByPriorityWidget
				.setSearchCriteria(unresolvedByPrioritySearchCriteria);
		this.rightColumn.addComponent(new LazyLoadWrapper(
				unresolvedByPriorityWidget));

		// bug chart
		final BugSearchCriteria recentDefectsCriteria = new BugSearchCriteria();
		recentDefectsCriteria.setProjectId(new NumberSearchField(project
				.getId()));
		updateBugWidget.setSearchCriteria(recentDefectsCriteria);

		final BugSearchCriteria chartSearchCriteria = new BugSearchCriteria();
		chartSearchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		BugChartComponent bugChartComponent = null;
		if (ScreenSize.hasSupport1024Pixels()) {
			bugChartComponent = new BugChartComponent(chartSearchCriteria, 300,
					200);
		} else if (ScreenSize.hasSupport1280Pixels()) {
			bugChartComponent = new BugChartComponent(chartSearchCriteria, 400,
					200);
		} else {
			bugChartComponent = new BugChartComponent(chartSearchCriteria, 400,
					200);
		}
		this.rightColumn.addComponent(bugChartComponent);

	}
}

package com.esofthead.mycollab.module.project.view;

import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.hene.splitbutton.SplitButtonExt;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.view.bug.BugPresenter;
import com.esofthead.mycollab.module.project.view.message.MessagePresenter;
import com.esofthead.mycollab.module.project.view.milestone.MilestonePresenter;
import com.esofthead.mycollab.module.project.view.parameters.MilestoneScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProblemScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectMemberScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.RiskScreenData;
import com.esofthead.mycollab.module.project.view.parameters.StandupScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TimeTrackingScreenData;
import com.esofthead.mycollab.module.project.view.people.UserGroupPresenter;
import com.esofthead.mycollab.module.project.view.problem.ProblemPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskPresenter;
import com.esofthead.mycollab.module.project.view.standup.StandupPresenter;
import com.esofthead.mycollab.module.project.view.task.TaskPresenter;
import com.esofthead.mycollab.module.project.view.time.TimeTrackingPresenter;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectViewImpl extends AbstractView implements ProjectView {

	private static Logger log = LoggerFactory.getLogger(ProjectViewImpl.class);
	private final HorizontalLayout root;
	private final DetachedTabs myProjectTab;
	private final CssLayout mySpaceArea = new CssLayout();
	private final HorizontalLayout topPanel;
	private ProjectDashboardPresenter dashboardPresenter;
	private MessagePresenter messagePresenter;
	private MilestonePresenter milestonesPresenter;
	private TaskPresenter taskPresenter;
	private BugPresenter bugPresenter;
	private ProblemPresenter problemPresenter;
	private RiskPresenter riskPresenter;
	private TimeTrackingPresenter timePresenter;
	private UserGroupPresenter userPresenter;
	private StandupPresenter standupPresenter;
	private final ProjectBreadcrumb breadCrumb;

	public ProjectViewImpl() {
		this.setStyleName("projectDashboardView");
		this.setMargin(false);

		breadCrumb = ViewManager.getView(ProjectBreadcrumb.class);

		topPanel = new HorizontalLayout();
		topPanel.setWidth("100%");
		topPanel.setMargin(true);
		this.addComponent(topPanel);

		root = new HorizontalLayout();
		root.setStyleName("menuContent");

		myProjectTab = new DetachedTabs.Vertical(mySpaceArea);
		myProjectTab.setSizeFull();
		myProjectTab.setHeight(null);

		CssLayout menu = new CssLayout();
		menu.setWidth("150px");
		menu.setStyleName("sidebar-menu");
		menu.addComponent(myProjectTab);

		root.addComponent(menu);
		mySpaceArea.setStyleName("projectTabContent");
		mySpaceArea.setWidth("100%");
		mySpaceArea.setHeight(null);
		root.addComponent(mySpaceArea);
		root.setExpandRatio(mySpaceArea, 1.0f);
		root.setWidth("100%");
		buildComponents();
		showWelcomeScreen();
		this.addComponent(root);
	}

	private void showWelcomeScreen() {
		mySpaceArea.addComponent(new Label("Welcome"));

	}

	private static class MenuButton extends Button {
		public MenuButton(String caption, String iconResource) {
			super(caption);
			this.setIcon(new ThemeResource("icons/22/project/" + iconResource));
			this.setStyleName("link");
		}
	}

	private void buildComponents() {
		myProjectTab.addTab(constructProjectDashboardComponent(),
				new MenuButton("Dashboard", "menu_dashboard.png"));
		myProjectTab.addTab(constructProjectMessageComponent(), new MenuButton(
				"Messages", "menu_message.png"));
		myProjectTab.addTab(constructProjectMilestoneComponent(),
				new MenuButton("Phases", "menu_milestone.png"));
		myProjectTab.addTab(constructTaskDashboardComponent(), new MenuButton(
				"Tasks", "menu_task.png"));
		myProjectTab.addTab(constructProjectBugComponent(), new MenuButton(
				"Bugs", "menu_bug.png"));
		myProjectTab.addTab(constructProjectRiskComponent(), new MenuButton(
				"Risks", "menu_risk.png"));
		myProjectTab.addTab(constructProjectProblemComponent(), new MenuButton(
				"Problems", "menu_problem.png"));
		myProjectTab.addTab(constructTimeTrackingComponent(), new MenuButton(
				"Time", "menu_time.png"));
		myProjectTab.addTab(constructProjectStandupMeeting(), new MenuButton(
				"StandUp", "menu_standup.png"));
		myProjectTab.addTab(constructProjectUsers(), new MenuButton(
				"Users & Group", "menu_user.png"));

		myProjectTab
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {
					@Override
					public void tabChanged(TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();
						mySpaceArea.setStyleName("projectTabContent");
						if ("Messages".equals(caption)) {
							messagePresenter.go(ProjectViewImpl.this, null);
						} else if ("Phases".equals(caption)) {
							mySpaceArea.addStyleName("Phases");
							MilestoneSearchCriteria searchCriteria = new MilestoneSearchCriteria();
							searchCriteria.setProjectId(new NumberSearchField(
									SearchField.AND, CurrentProjectVariables
											.getProjectId()));
							gotoMilestoneView(new MilestoneScreenData.Search(
									searchCriteria));
						} else if ("Tasks".equals(caption)) {
							taskPresenter.go(ProjectViewImpl.this, null);
						} else if ("Bugs".equals(caption)) {
							gotoBugView(null);
						} else if ("Risks".equals(caption)) {
							RiskSearchCriteria searchCriteria = new RiskSearchCriteria();
							searchCriteria.setProjectId(new NumberSearchField(
									SearchField.AND, CurrentProjectVariables
											.getProjectId()));
							gotoRiskView(new RiskScreenData.Search(
									searchCriteria));
						} else if ("Problems".equals(caption)) {
							ProblemSearchCriteria searchCriteria = new ProblemSearchCriteria();
							searchCriteria.setProjectId(new NumberSearchField(
									SearchField.AND, CurrentProjectVariables
											.getProjectId()));
							problemPresenter
									.go(ProjectViewImpl.this,
											new ProblemScreenData.Search(
													searchCriteria));
						} else if ("Dashboard".equals(caption)) {
							dashboardPresenter.go(ProjectViewImpl.this, null);
						} else if ("Users & Group".equals(caption)) {
							ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
							criteria.setProjectId(new NumberSearchField(
									CurrentProjectVariables.getProjectId()));
							gotoUsersAndGroup(new ProjectMemberScreenData.Search(
									criteria));
						} else if ("Time".equals(caption)) {
							ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
							searchCriteria.setProjectId(new NumberSearchField(
									CurrentProjectVariables.getProjectId()));
							searchCriteria.setRangeDate(ItemTimeLoggingSearchCriteria
									.getCurrentRangeDateOfWeekSearchField());
							gotoTimeTrackingView(new TimeTrackingScreenData.Search(
									searchCriteria));
						} else if ("StandUp".equals(caption)) {
							StandupReportSearchCriteria criteria = new StandupReportSearchCriteria();
							criteria.setProjectId(new NumberSearchField(
									CurrentProjectVariables.getProjectId()));
							criteria.setOnDate(new DateSearchField(
									SearchField.AND, new GregorianCalendar()
											.getTime()));
							standupPresenter.go(ProjectViewImpl.this,
									new StandupScreenData.Search(criteria));
						}
					}
				});
	}

	@Override
	public void gotoUsersAndGroup(ScreenData<?> data) {
		userPresenter.go(ProjectViewImpl.this, data);
	}

	@Override
	public void gotoTaskList(ScreenData data) {
		taskPresenter.go(ProjectViewImpl.this, data);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void gotoRiskView(ScreenData data) {
		riskPresenter.go(ProjectViewImpl.this, data);
	}

	public void gotoTimeTrackingView(ScreenData data) {
		timePresenter.go(ProjectViewImpl.this, data);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void gotoBugView(ScreenData data) {
		bugPresenter.go(ProjectViewImpl.this, data);
	}

	@Override
	public void gotoMilestoneView(ScreenData data) {
		milestonesPresenter.go(ProjectViewImpl.this, data);
	}

	@Override
	public void gotoStandupReportView(ScreenData<?> data) {
		standupPresenter.go(ProjectViewImpl.this, data);
	}

	private Component constructProjectDashboardComponent() {
		dashboardPresenter = PresenterResolver
				.getPresenter(ProjectDashboardPresenter.class);
		return dashboardPresenter.getView();
	}

	private Component constructProjectUsers() {
		userPresenter = PresenterResolver
				.getPresenter(UserGroupPresenter.class);
		return userPresenter.getView();
	}

	private Component constructProjectMessageComponent() {
		messagePresenter = PresenterResolver
				.getPresenter(MessagePresenter.class);
		return messagePresenter.getView();
	}

	private Component constructProjectMilestoneComponent() {
		milestonesPresenter = PresenterResolver
				.getPresenter(MilestonePresenter.class);
		return milestonesPresenter.getView();
	}

	private Component constructProjectRiskComponent() {
		riskPresenter = PresenterResolver.getPresenter(RiskPresenter.class);
		return riskPresenter.getView();
	}

	private Component constructProjectProblemComponent() {
		problemPresenter = PresenterResolver
				.getPresenter(ProblemPresenter.class);
		return problemPresenter.getView();
	}

	private Component constructTimeTrackingComponent() {
		timePresenter = PresenterResolver
				.getPresenter(TimeTrackingPresenter.class);
		return timePresenter.getView();
	}

	private Component constructProjectStandupMeeting() {
		standupPresenter = PresenterResolver
				.getPresenter(StandupPresenter.class);
		return standupPresenter.getView();
	}

	private Component constructTaskDashboardComponent() {
		taskPresenter = PresenterResolver.getPresenter(TaskPresenter.class);
		return taskPresenter.getView();
	}

	private Component constructProjectBugComponent() {
		bugPresenter = PresenterResolver.getPresenter(BugPresenter.class);
		return bugPresenter.getView();
	}

	@Override
	public void constructProjectHeaderPanel(final SimpleProject project,
			PageActionChain pageActionChain) {
		topPanel.removeAllComponents();

		topPanel.addComponent(breadCrumb);
		topPanel.setComponentAlignment(breadCrumb, Alignment.BOTTOM_CENTER);
		topPanel.setExpandRatio(breadCrumb, 1.0f);

		breadCrumb.setProject(project);
		breadCrumb.initBreadcrumb();

		Button editProjectBtn = new Button(
				LocalizationHelper
						.getMessage(ProjectCommonI18nEnum.EDIT_PROJECT_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						dashboardPresenter.go(ProjectViewImpl.this,
								new ProjectScreenData.Edit(project));
					}
				});
		editProjectBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.PROJECT));
		SplitButtonExt controlsBtn = new SplitButtonExt(editProjectBtn);
		controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
		controlsBtn.setIcon(new ThemeResource("icons/16/edit.png"));

		Button selectBtn = new Button(
				LocalizationHelper
						.getMessage(ProjectCommonI18nEnum.VIEW_PROJECT_DETAIL_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						dashboardPresenter.go(ProjectViewImpl.this, null);
					}
				});
		selectBtn.setEnabled(CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.PROJECT));
		selectBtn.setIcon(new ThemeResource("icons/16/view.png"));
		selectBtn.setStyleName("link");
		controlsBtn.addComponent(selectBtn);

		topPanel.addComponent(controlsBtn);
		topPanel.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);
	}

	@Override
	public Component gotoSubView(String name) {
		log.debug("Project: Go to tab view name " + name);
		View component = (View) myProjectTab.selectTab(name);
		return component;
	}
}

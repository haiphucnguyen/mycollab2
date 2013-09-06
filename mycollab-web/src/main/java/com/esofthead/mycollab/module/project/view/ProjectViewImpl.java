package com.esofthead.mycollab.module.project.view;

import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButtonExt;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectStatusConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.bug.TrackerPresenter;
import com.esofthead.mycollab.module.project.view.file.FilePresenter;
import com.esofthead.mycollab.module.project.view.message.MessagePresenter;
import com.esofthead.mycollab.module.project.view.milestone.MilestonePresenter;
import com.esofthead.mycollab.module.project.view.parameters.FileScreenData;
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
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

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
	private TrackerPresenter trackerPresenter;
	private FilePresenter filePresenter;
	private ProblemPresenter problemPresenter;
	private RiskPresenter riskPresenter;
	private TimeTrackingPresenter timePresenter;
	private UserGroupPresenter userPresenter;
	private StandupPresenter standupPresenter;
	private final ProjectBreadcrumb breadCrumb;
	private SplitButtonExt controlsBtn;

	public ProjectViewImpl() {
		this.setWidth("100%");

		final CssLayout contentWrapper = new CssLayout();
		contentWrapper.setStyleName("projectDashboardView");
		contentWrapper.addStyleName("main-content-wrapper");
		contentWrapper.setWidth("100%");
		this.addComponent(contentWrapper);

		breadCrumb = ViewManager.getView(ProjectBreadcrumb.class);

		topPanel = new HorizontalLayout();
		topPanel.setWidth("100%");
		topPanel.setMargin(true);
		contentWrapper.addComponent(topPanel);

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
		contentWrapper.addComponent(root);
	}

	private static class MenuButton extends Button {
		public MenuButton(String caption, String iconResource) {
			super(caption);
			this.setIcon(MyCollabResource.newResource("icons/22/project/"
					+ iconResource));
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
		myProjectTab.addTab(constructProjectFileComponent(), new MenuButton(
				"Files", "menu_file.png"));
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
						} else if ("Files".equals(caption)) {
							filePresenter.go(ProjectViewImpl.this,
									new FileScreenData.GotoDashboard());
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
							criteria.setStatus(new StringSearchField(
									ProjectMemberStatusConstants.ACTIVE));
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
		trackerPresenter.go(ProjectViewImpl.this, data);
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
		trackerPresenter = PresenterResolver
				.getPresenter(TrackerPresenter.class);
		return trackerPresenter.getView();
	}

	private Component constructProjectFileComponent() {
		filePresenter = PresenterResolver.getPresenter(FilePresenter.class);
		return filePresenter.getView();
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

		Button quickActionBtn = new Button("Quick Action",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(true);
					}
				});
		controlsBtn = new SplitButtonExt(quickActionBtn);
		controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
		controlsBtn.setIcon(MyCollabResource
				.newResource("icons/16/project/quick_action.png"));

		VerticalLayout popupButtonsControl = new VerticalLayout();
		popupButtonsControl.setWidth("150px");

		Button createPhaseBtn = new Button("Create Phase",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new MilestoneEvent.GotoAdd(
										ProjectViewImpl.this, null));
					}
				});
		createPhaseBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.MILESTONES));
		createPhaseBtn.setIcon(MyCollabResource
				.newResource("icons/16/project/milestone.png"));
		createPhaseBtn.setStyleName("link");
		popupButtonsControl.addComponent(createPhaseBtn);

		Button createBugBtn = new Button("Create Bug",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoAdd(this, null));
					}
				});
		createBugBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));
		createBugBtn.setIcon(MyCollabResource
				.newResource("icons/16/project/bug.png"));
		createBugBtn.setStyleName("link");
		popupButtonsControl.addComponent(createBugBtn);

		Button createRiskBtn = new Button("Create Risk",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new RiskEvent.GotoAdd(this, null));
					}
				});
		createRiskBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.RISKS));
		createRiskBtn.setIcon(MyCollabResource
				.newResource("icons/16/project/risk.png"));
		createRiskBtn.setStyleName("link");
		popupButtonsControl.addComponent(createRiskBtn);

		Button createProblemBtn = new Button("Create Problem",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new ProblemEvent.GotoAdd(this, null));
					}
				});
		createProblemBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.PROBLEMS));
		createProblemBtn.setIcon(MyCollabResource
				.newResource("icons/16/project/problem.png"));
		createProblemBtn.setStyleName("link");
		popupButtonsControl.addComponent(createProblemBtn);

		Button editProjectBtn = new Button(
				LocalizationHelper
						.getMessage(ProjectCommonI18nEnum.EDIT_PROJECT_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						dashboardPresenter.go(ProjectViewImpl.this,
								new ProjectScreenData.Edit(project));
					}
				});
		editProjectBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.PROJECT));
		editProjectBtn.setIcon(MyCollabResource
				.newResource("icons/16/project/edit_project.png"));
		editProjectBtn.setStyleName("link");
		popupButtonsControl.addComponent(editProjectBtn);

		// if (CurrentProjectVariables
		// .canAccess(ProjectRolePermissionCollections.PROJECT)) {
		// Button archievedProjectBtn = new Button(
		// LocalizationHelper
		// .getMessage(ProjectCommonI18nEnum.ARCHIVE_PROJECT_ACTION),
		// new Button.ClickListener() {
		// @Override
		// public void buttonClick(ClickEvent event) {
		// controlsBtn.setPopupVisible(false);
		// ConfirmDialogExt.show(
		// ProjectViewImpl.this.getWindow(),
		// LocalizationHelper
		// .getMessage(
		// ProjectCommonI18nEnum.DIALOG_ARCHIVE_PROJECT_TITLE,
		// SiteConfiguration
		// .getSiteName()),
		// LocalizationHelper
		// .getMessage(ProjectCommonI18nEnum.CONFIRM_PROJECT_ARCHIVE_MESSAGE),
		// LocalizationHelper
		// .getMessage(GenericI18Enum.BUTTON_YES_LABEL),
		// LocalizationHelper
		// .getMessage(GenericI18Enum.BUTTON_NO_LABEL),
		// new ConfirmDialog.Listener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void onClose(ConfirmDialog dialog) {
		// if (dialog.isConfirmed()) {
		// ProjectService projectService = AppContext
		// .getSpringBean(ProjectService.class);
		// SimpleProject project = CurrentProjectVariables
		// .getProject();
		// project.setProjectstatus(ProjectStatusConstants.ARCHIVE);
		// projectService
		// .updateWithSession(
		// project,
		// AppContext
		// .getUsername());
		// EventBus.getInstance()
		// .fireEvent(
		// new ShellEvent.GotoProjectModule(
		// this,
		// null));
		// }
		// }
		// });
		// }
		// });
		// archievedProjectBtn.setEnabled(CurrentProjectVariables
		// .canAccess(ProjectRolePermissionCollections.PROJECT));
		// archievedProjectBtn.setIcon(MyCollabResource
		// .newResource("icons/16/project/delete_project.png"));
		// archievedProjectBtn.setStyleName("link");
		// popupButtonsControl.addComponent(archievedProjectBtn);
		// }

		if (CurrentProjectVariables
				.canAccess(ProjectRolePermissionCollections.PROJECT)) {
			Button deleteProjectBtn = new Button(
					LocalizationHelper
							.getMessage(ProjectCommonI18nEnum.DELETE_PROJECT_ACTION),
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							controlsBtn.setPopupVisible(false);
							ConfirmDialogExt.show(
									ProjectViewImpl.this.getWindow(),
									LocalizationHelper.getMessage(
											GenericI18Enum.DELETE_DIALOG_TITLE,
											SiteConfiguration.getSiteName()),
									LocalizationHelper
											.getMessage(ProjectCommonI18nEnum.CONFIRM_PROJECT_DELETE_MESSAGE),
									LocalizationHelper
											.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
									LocalizationHelper
											.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
									new ConfirmDialog.Listener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void onClose(ConfirmDialog dialog) {
											if (dialog.isConfirmed()) {
												ProjectService projectService = AppContext
														.getSpringBean(ProjectService.class);
												projectService.removeWithSession(
														CurrentProjectVariables
																.getProjectId(),
														AppContext
																.getUsername(),
														AppContext
																.getAccountId());
												EventBus.getInstance()
														.fireEvent(
																new ShellEvent.GotoProjectModule(
																		this,
																		null));
											}
										}
									});
						}
					});
			deleteProjectBtn.setEnabled(CurrentProjectVariables
					.canAccess(ProjectRolePermissionCollections.PROJECT));
			deleteProjectBtn.setIcon(MyCollabResource
					.newResource("icons/16/project/delete_project.png"));
			deleteProjectBtn.setStyleName("link");
			popupButtonsControl.addComponent(deleteProjectBtn);
		}

		controlsBtn.addComponent(popupButtonsControl);

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

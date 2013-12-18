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
package com.esofthead.mycollab.module.project.view;

import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
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
import com.esofthead.mycollab.module.project.view.file.IFilePresenter;
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
import com.esofthead.mycollab.module.project.view.problem.IProblemPresenter;
import com.esofthead.mycollab.module.project.view.risk.IRiskPresenter;
import com.esofthead.mycollab.module.project.view.settings.UserSettingPresenter;
import com.esofthead.mycollab.module.project.view.standup.IStandupPresenter;
import com.esofthead.mycollab.module.project.view.task.TaskPresenter;
import com.esofthead.mycollab.module.project.view.time.ITimeTrackingPresenter;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardPresenter;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.VerticalTabsheet;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectViewImpl extends AbstractPageView implements ProjectView {

	private static Logger log = LoggerFactory.getLogger(ProjectViewImpl.class);
	private final VerticalTabsheet myProjectTab;
	private final HorizontalLayout topPanel;
	private ProjectDashboardPresenter dashboardPresenter;
	private MessagePresenter messagePresenter;
	private MilestonePresenter milestonesPresenter;
	private TaskPresenter taskPresenter;
	private TrackerPresenter trackerPresenter;
	private IFilePresenter filePresenter;
	private IProblemPresenter problemPresenter;
	private IRiskPresenter riskPresenter;
	private ITimeTrackingPresenter timePresenter;
	private UserSettingPresenter userPresenter;
	private IStandupPresenter standupPresenter;
	private final ProjectBreadcrumb breadCrumb;
	private SplitButton controlsBtn;

	public ProjectViewImpl() {
		this.setWidth("100%");

		final CssLayout contentWrapper = new CssLayout();
		contentWrapper.setStyleName("projectDashboardView");
		contentWrapper.setWidth("100%");
		this.addStyleName("main-content-wrapper");
		this.addComponent(contentWrapper);

		breadCrumb = ViewManager.getView(ProjectBreadcrumb.class);

		topPanel = new HorizontalLayout();
		topPanel.setWidth("100%");
		topPanel.setMargin(true);
		contentWrapper.addComponent(topPanel);

		myProjectTab = new VerticalTabsheet();
		myProjectTab.setSizeFull();
		myProjectTab.setNavigatorWidth("170px");
		myProjectTab.setNavigatorStyleName("sidebar-menu");
		myProjectTab.setContainerStyleName("tab-content");
		myProjectTab.setHeight(null);
		
		buildComponents();
		contentWrapper.addComponent(myProjectTab);
	}

	private void buildComponents() {
		myProjectTab.addTab(constructProjectDashboardComponent(), "Dashboard",
				MyCollabResource
						.newResource("icons/22/project/menu_dashboard.png"));

		myProjectTab.addTab(constructProjectMessageComponent(), "Messages",
				MyCollabResource
						.newResource("icons/22/project/menu_message.png"));

		myProjectTab.addTab(constructProjectMilestoneComponent(), "Phases",
				MyCollabResource
						.newResource("icons/22/project/menu_milestone.png"));

		myProjectTab.addTab(constructTaskDashboardComponent(), "Tasks",
				MyCollabResource.newResource("icons/22/project/menu_task.png"));

		myProjectTab.addTab(constructProjectBugComponent(), "Bugs",
				MyCollabResource.newResource("icons/22/project/menu_bug.png"));

		myProjectTab.addTab(constructProjectFileComponent(), "Files",
				MyCollabResource.newResource("icons/22/project/menu_file.png"));

		myProjectTab.addTab(constructProjectRiskComponent(), "Risks",
				MyCollabResource.newResource("icons/22/project/menu_risk.png"));

		myProjectTab.addTab(constructProjectProblemComponent(), "Problems",
				MyCollabResource
						.newResource("icons/22/project/menu_problem.png"));

		myProjectTab.addTab(constructTimeTrackingComponent(), "Time",
				MyCollabResource.newResource("icons/22/project/menu_time.png"));

		myProjectTab.addTab(constructProjectStandupMeeting(), "StandUp",
				MyCollabResource
						.newResource("icons/22/project/menu_standup.png"));

		myProjectTab.addTab(constructProjectUsers(), "Users & Settings",
				MyCollabResource.newResource("icons/22/project/menu_user.png"));

		myProjectTab
				.addSelectedTabChangeListener(new SelectedTabChangeListener() {

					@Override
					public void selectedTabChange(SelectedTabChangeEvent event) {
						Tab tab = ((VerticalTabsheet) event.getSource())
								.getSelectedTab();
						String caption = tab.getCaption();
						if ("Messages".equals(caption)) {
							messagePresenter.go(ProjectViewImpl.this, null);
						} else if ("Phases".equals(caption)) {
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
						} else if ("Users & Settings".equals(caption)) {
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
				.getPresenter(UserSettingPresenter.class);
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
		riskPresenter = PresenterResolver.getPresenter(IRiskPresenter.class);
		return riskPresenter.getView();
	}

	private Component constructProjectProblemComponent() {
		problemPresenter = PresenterResolver
				.getPresenter(IProblemPresenter.class);
		return problemPresenter.getView();
	}

	private Component constructTimeTrackingComponent() {
		timePresenter = PresenterResolver
				.getPresenter(ITimeTrackingPresenter.class);
		return timePresenter.getView();
	}

	private Component constructProjectStandupMeeting() {
		standupPresenter = PresenterResolver
				.getPresenter(IStandupPresenter.class);
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
		filePresenter = PresenterResolver.getPresenter(IFilePresenter.class);
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
		controlsBtn = new SplitButton(quickActionBtn);
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
									UI.getCurrent(),
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
												ProjectService projectService = ApplicationContextUtil
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

		controlsBtn.setContent(popupButtonsControl);

		topPanel.addComponent(controlsBtn);
		topPanel.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);
	}

	@Override
	public Component gotoSubView(String name) {
		log.debug("Project: Go to tab view name " + name);
		PageView component = (PageView) myProjectTab.selectTab(name);
		return component;
	}
}

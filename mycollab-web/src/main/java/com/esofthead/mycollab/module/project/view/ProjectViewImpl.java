package com.esofthead.mycollab.module.project.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.bug.BugPresenter;
import com.esofthead.mycollab.module.project.view.message.ProjectMessageListPresenter;
import com.esofthead.mycollab.module.project.view.milestone.ProjectMilestonePresenter;
import com.esofthead.mycollab.module.project.view.problem.ProblemPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskPresenter;
import com.esofthead.mycollab.module.project.view.task.ProjectTaskPresenter;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectViewImpl extends AbstractView implements ProjectView {

	private static Logger log = LoggerFactory.getLogger(ProjectViewImpl.class);

	private final HorizontalLayout root;
	private final DetachedTabs myProjectTab;
	private final CssLayout mySpaceArea = new CssLayout();

	private final HorizontalLayout topPanel;

	private ProjectMessageListPresenter messagePresenter;
	private ProjectMilestonePresenter milestonesPresenter;
	private ProjectTaskPresenter taskPresenter;
	private BugPresenter bugPresenter;
	private ProblemPresenter problemPresenter;
	private RiskPresenter riskPresenter;

	private SimpleProject project;

	public ProjectViewImpl() {
		this.setStyleName("projectDashboardView");
		this.setMargin(false);

		topPanel = new HorizontalLayout();
		this.addComponent(topPanel);

		root = new HorizontalLayout();
		root.setStyleName("menuContent");

		myProjectTab = new DetachedTabs.Vertical(mySpaceArea);
		myProjectTab.setSizeFull();
		myProjectTab.setHeight(null);

		VerticalLayout menu = new VerticalLayout();
		menu.setWidth("200px");
		menu.setStyleName("sidebar-menu");
		menu.addComponent(myProjectTab);

		root.addComponent(menu);
		mySpaceArea.setStyleName("projectTabContent");
		root.addComponent(mySpaceArea);
		root.setExpandRatio(mySpaceArea, 1.0f);

		buildComponents();
		this.addComponent(root);
	}

	private void buildComponents() {
		myProjectTab.addTab(constructProjectDashboardComponent(), "Dashboard");
		myProjectTab.addTab(constructProjectMessageComponent(), "Messages");
		myProjectTab.addTab(constructProjectMilestoneComponent(), "Milestones");
		myProjectTab.addTab(constructTaskDashboardComponent(), "Tasks");
		myProjectTab.addTab(constructProjectBugComponent(), "Bugs");
		myProjectTab.addTab(constructProjectRiskComponent(), "Risks");
		myProjectTab.addTab(constructProjectProblemComponent(), "Problems");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Calendar");

		myProjectTab
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {

					@Override
					public void tabChanged(TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();
						if ("Messages".equals(caption)) {
							messagePresenter.go(ProjectViewImpl.this,
									new ScreenData<SimpleProject>(project));
						} else if ("Milestones".equals(caption)) {
							milestonesPresenter.go(ProjectViewImpl.this,
									new ScreenData<SimpleProject>(project));
						} else if ("Tasks".equals(caption)) {
							taskPresenter.go(ProjectViewImpl.this,
									new ScreenData<SimpleProject>(project));
						} else if ("Bugs".equals(caption)) {
							gotoBugView(null);
						} else if ("Risks".equals(caption)) {
							RiskSearchCriteria searchCriteria = new RiskSearchCriteria();
							SimpleProject project = (SimpleProject) AppContext
									.getVariable(ProjectContants.PROJECT_NAME);
							searchCriteria.setProjectId(new NumberSearchField(
									SearchField.AND, project.getId()));
							gotoRiskView(new ScreenData.Search<RiskSearchCriteria>(
									searchCriteria));
						} else if ("Problems".equals(caption)) {
							ProblemSearchCriteria searchCriteria = new ProblemSearchCriteria();
							SimpleProject project = (SimpleProject) AppContext
									.getVariable(ProjectContants.PROJECT_NAME);
							searchCriteria.setProjectId(new NumberSearchField(
									SearchField.AND, project.getId()));
							problemPresenter
									.go(ProjectViewImpl.this,
											new ScreenData.Search<ProblemSearchCriteria>(
													searchCriteria));
						}
					}
				});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void gotoRiskView(ScreenData data) {
		riskPresenter.go(ProjectViewImpl.this, data);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void gotoProblemView(ScreenData data) {
		problemPresenter.go(ProjectViewImpl.this, data);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void gotoBugView(ScreenData data) {
		bugPresenter.go(ProjectViewImpl.this, data);
	}

	private Component constructProjectDashboardComponent() {
		return new ProjectDashboardViewImpl();
	}

	private Component constructProjectMessageComponent() {
		messagePresenter = PresenterResolver
				.getPresenter(ProjectMessageListPresenter.class);
		return messagePresenter.getView();
	}

	private Component constructProjectMilestoneComponent() {
		milestonesPresenter = PresenterResolver
				.getPresenter(ProjectMilestonePresenter.class);
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

	private Component constructTaskDashboardComponent() {
		taskPresenter = PresenterResolver
				.getPresenter(ProjectTaskPresenter.class);
		return taskPresenter.getView();
	}

	private Component constructProjectBugComponent() {
		bugPresenter = PresenterResolver.getPresenter(BugPresenter.class);
		return bugPresenter.getView();
	}

	@Override
	public void displayProject(SimpleProject project) {
		this.project = project;

		topPanel.removeAllComponents();
		topPanel.addComponent(new Button("Home", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoProjectPage(this, null));
			}
		}));

		PopupButton projectPopupBtn = new PopupButton(project.getName());
		BeanList<ProjectService, ProjectSearchCriteria, SimpleProject> projectList = new BeanList<ProjectService, ProjectSearchCriteria, SimpleProject>(
				AppContext.getSpringBean(ProjectService.class),
				new BeanList.RowDisplayHandler<SimpleProject>() {

					@Override
					public Component generateRow(SimpleProject obj, int rowIndex) {
						return new Label(obj.getName());
					}
				});
		projectList.setWidth("200px");

		ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		projectList.setSearchCriteria(searchCriteria);
		projectPopupBtn.addComponent(projectList);

		topPanel.addComponent(projectPopupBtn);
	}

	@Override
	public Component gotoSubView(String name) {
		log.debug("Project: Go to tab view name " + name);
		View component = (View) myProjectTab.selectTab(name);
		return component;
	}
}

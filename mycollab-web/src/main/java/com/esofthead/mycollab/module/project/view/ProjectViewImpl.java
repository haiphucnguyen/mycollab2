package com.esofthead.mycollab.module.project.view;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.defect.DefectDashboardPresenter;
import com.esofthead.mycollab.module.project.view.message.ProjectMessageListPresenter;
import com.esofthead.mycollab.module.project.view.milestone.ProjectMilestonePresenter;
import com.esofthead.mycollab.module.project.view.problem.ProblemListPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskPresenter;
import com.esofthead.mycollab.module.project.view.task.ProjectTaskPresenter;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectViewImpl extends AbstractView implements ProjectView {

	private HorizontalSplitPanel root;
	private DetachedTabs myProjectTab;
	private CssLayout mySpaceArea = new CssLayout();

	private HorizontalLayout topPanel;

	private ProjectMessageListPresenter messagePresenter;
	private ProjectMilestonePresenter milestonesPresenter;
	private ProjectTaskPresenter taskPresenter;
	private DefectDashboardPresenter defectPresenter;
	private ProblemListPresenter problemListPresenter;
	private RiskPresenter riskPresenter;

	private SimpleProject project;

	public ProjectViewImpl() {
		this.setStyleName("projectDashboardView");
		this.setMargin(false);

		topPanel = new HorizontalLayout();
		this.addComponent(topPanel);

		root = new HorizontalSplitPanel();
		root.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		root.setStyleName("menuContent");
		root.setWidth("100%");

		mySpaceArea.setWidth("100%");
		myProjectTab = new DetachedTabs.Vertical(mySpaceArea);
		myProjectTab.setSizeFull();
		myProjectTab.setHeight(null);

		VerticalLayout menu = new VerticalLayout();
		menu.setWidth("200px");
		menu.setStyleName("sidebar-menu");
		menu.addComponent(myProjectTab);

		root.setFirstComponent(menu);
		root.addComponent(mySpaceArea);

		buildComponents();
		this.addComponent(root);
	}

	private void buildComponents() {
		myProjectTab.addTab(constructProjectDashboardComponent(), "Dashboard");
		myProjectTab.addTab(constructProjectMessageComponent(), "Messages");
		myProjectTab.addTab(constructProjectMilestoneComponent(), "Milestones");
		myProjectTab.addTab(constructTaskDashboardComponent(), "Tasks");
		myProjectTab.addTab(constructProjectDefectComponent(), "Bugs");
		myProjectTab.addTab(constructProjectRiskComponent(), "Risks");
		myProjectTab.addTab(constructProjectDefectComponent(), "Problems");
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
						} else if ("Defects".equals(caption)) {
							defectPresenter.go(ProjectViewImpl.this,
									new ScreenData<SimpleProject>(project));
						} else if ("Risks".equals(caption)) {
							RiskSearchCriteria searchCriteria = new RiskSearchCriteria();
							SimpleProject project = (SimpleProject) AppContext
									.getVariable(ProjectContants.PROJECT_NAME);
							searchCriteria.setProjectId(new NumberSearchField(
									SearchField.AND, project.getId()));
							riskPresenter.go(ProjectViewImpl.this,
									new ScreenData.Search<RiskSearchCriteria>(
											searchCriteria));
						}
					}
				});
	}

	@Override
	public void gotoRiskView(ScreenData data) {
		riskPresenter.go(ProjectViewImpl.this, data);
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

	private Component constructTaskDashboardComponent() {
		taskPresenter = PresenterResolver
				.getPresenter(ProjectTaskPresenter.class);
		return taskPresenter.getView();
	}

	private Component constructProjectDefectComponent() {
		defectPresenter = PresenterResolver
				.getPresenter(DefectDashboardPresenter.class);
		return defectPresenter.getView();
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
		ProjectAbstractView component = (ProjectAbstractView) myProjectTab
				.selectTab(name);
		component.setProject(project);
		return component;
	}
}

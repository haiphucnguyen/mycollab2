package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.bug.BugPresenter;
import com.esofthead.mycollab.module.project.view.message.MessagePresenter;
import com.esofthead.mycollab.module.project.view.milestone.MilestonePresenter;
import com.esofthead.mycollab.module.project.view.problem.ProblemPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskPresenter;
import com.esofthead.mycollab.module.project.view.task.TaskPresenter;
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
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.hene.popupbutton.PopupButton;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectViewImpl extends AbstractView implements ProjectView {

    private static Logger log = LoggerFactory.getLogger(ProjectViewImpl.class);
    private final HorizontalLayout root;
    private final DetachedTabs myProjectTab;
    private final CssLayout mySpaceArea = new CssLayout();
    private final HorizontalLayout topPanel;
    private MessagePresenter messagePresenter;
    private MilestonePresenter milestonesPresenter;
    private TaskPresenter taskPresenter;
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
                    messagePresenter.go(ProjectViewImpl.this, null);
                } else if ("Milestones".equals(caption)) {
                    MilestoneSearchCriteria searchCriteria = new MilestoneSearchCriteria();
                    SimpleProject project = (SimpleProject) AppContext
                            .getVariable(ProjectContants.PROJECT_NAME);
                    searchCriteria.setProjectId(new NumberSearchField(
                            SearchField.AND, project.getId()));
                    gotoMilestoneView(new ScreenData.Search<MilestoneSearchCriteria>(
                            searchCriteria));
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

    public void gotoMilestoneView(ScreenData data) {
        milestonesPresenter.go(ProjectViewImpl.this, data);
    }

    private Component constructProjectDashboardComponent() {
        return new ProjectDashboardViewImpl();
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

    private Component constructTaskDashboardComponent() {
        taskPresenter = PresenterResolver
                .getPresenter(TaskPresenter.class);
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
        Button homeBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(
                        new ShellEvent.GotoProjectPage(this, null));
            }
        });
        homeBtn.setIcon(new ThemeResource("icons/24/project/home.png"));
        homeBtn.setStyleName("link");
        topPanel.addComponent(homeBtn);

        PopupButton projectPopupBtn = new PopupButton(project.getName());
        BeanList<ProjectService, ProjectSearchCriteria, SimpleProject> projectList = new BeanList<ProjectService, ProjectSearchCriteria, SimpleProject>(
                AppContext.getSpringBean(ProjectService.class), ProjectRowDisplayHandler.class);
        projectList.setWidth("200px");

        ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        projectList.setSearchCriteria(searchCriteria);
        projectPopupBtn.addComponent(projectList);

        topPanel.addComponent(projectPopupBtn);
    }

    public static class ProjectRowDisplayHandler implements BeanList.RowDisplayHandler<SimpleProject> {

        @Override
        public Component generateRow(final SimpleProject obj, int rowIndex) {
            Button projectLink = new Button(obj.getName(), new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, obj));
                }
            });
            projectLink.setStyleName("link");
            return projectLink;
        }
    }

    @Override
    public Component gotoSubView(String name) {
        log.debug("Project: Go to tab view name " + name);
        View component = (View) myProjectTab.selectTab(name);
        return component;
    }

    @Override
    public void gotoMessageView(ScreenData data) {
        messagePresenter.go(ProjectViewImpl.this, data);
    }
}

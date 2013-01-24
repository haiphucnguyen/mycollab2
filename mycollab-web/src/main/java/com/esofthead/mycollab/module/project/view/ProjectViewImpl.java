package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.view.bug.BugPresenter;
import com.esofthead.mycollab.module.project.view.message.MessagePresenter;
import com.esofthead.mycollab.module.project.view.milestone.MilestonePresenter;
import com.esofthead.mycollab.module.project.view.people.UserPresenter;
import com.esofthead.mycollab.module.project.view.problem.ProblemPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskPresenter;
import com.esofthead.mycollab.module.project.view.task.TaskPresenter;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.hene.splitbutton.SplitButton;

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
    private UserPresenter userPresenter;
    private SimpleProject project;
    private ProjectBreadcrumb breadCrumb;

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
        menu.setWidth("200px");
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
        myProjectTab.addTab(constructProjectUsers(), "Users & Group");

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
                } else if ("Dashboard".equals(caption)) {
                    gotoDashboard(null);
                } else if ("Users & Group".equals(caption)) {
                    gotoUsersAndGroup();
                }
            }
        });
    }

    @Override
    public void gotoDashboard(ScreenData data) {
        dashboardPresenter.go(ProjectViewImpl.this, data);
    }

    @Override
    public void gotoUsersAndGroup() {
        userPresenter.go(ProjectViewImpl.this, null);
    }

    @Override
    public void gotoTaskList(ScreenData data) {
        taskPresenter.go(ProjectViewImpl.this,
                data);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void gotoRiskView(ScreenData data) {
        riskPresenter.go(ProjectViewImpl.this, data);
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

    private Component constructProjectDashboardComponent() {
        dashboardPresenter = PresenterResolver
                .getPresenter(ProjectDashboardPresenter.class);
        return dashboardPresenter.getView();
    }

    private Component constructProjectUsers() {
        userPresenter = PresenterResolver.getPresenter(UserPresenter.class);
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

    private Component constructTaskDashboardComponent() {
        taskPresenter = PresenterResolver.getPresenter(TaskPresenter.class);
        return taskPresenter.getView();
    }

    private Component constructProjectBugComponent() {
        bugPresenter = PresenterResolver.getPresenter(BugPresenter.class);
        return bugPresenter.getView();
    }

    @Override
    public void displayProject(final SimpleProject project, PageActionChain pageActionChain) {
        this.project = project;
        topPanel.removeAllComponents();

        topPanel.addComponent(breadCrumb);
        topPanel.setComponentAlignment(breadCrumb, Alignment.MIDDLE_LEFT);
        topPanel.setExpandRatio(breadCrumb, 1.0f);
        
        breadCrumb.setProject(project);
        breadCrumb.select(0);
        breadCrumb.addLink(new Button(project.getName(), new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                gotoDashboard(null);
            }
        }));
        breadCrumb.setLinkEnabled(true, 1);

        SplitButton controlsBtn = new SplitButton();
        controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
        controlsBtn.setCaption("Edit Project");
        controlsBtn.setIcon(new ThemeResource("icons/16/edit.png"));
        controlsBtn
                .addClickListener(new SplitButton.SplitButtonClickListener() {
            @Override
            public void splitButtonClick(
                    SplitButton.SplitButtonClickEvent event) {
                gotoDashboard(new ScreenData.Edit<Project>(project));
            }
        });
        Button selectBtn = new Button("View Project Detail",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        gotoDashboard(null);
                    }
                });
        selectBtn.setIcon(new ThemeResource("icons/16/view.png"));
        selectBtn.setStyleName("link");
        controlsBtn.addComponent(selectBtn);

        topPanel.addComponent(controlsBtn);
        topPanel.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);
        
        if (pageActionChain == null || !pageActionChain.hasNext()) {
            gotoDashboard(null);
        }
    }

    @Override
    public Component gotoSubView(String name) {
        log.debug("Project: Go to tab view name " + name);
        View component = (View) myProjectTab.selectTab(name);
        return component;
    }
}

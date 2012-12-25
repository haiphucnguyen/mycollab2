package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.MessageEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.events.ProjectEvent.SaveProjectSucess;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;

public class ProjectController {

    private ProjectMainContainer container;

    public ProjectController(ProjectMainContainer container) {
        this.container = container;
        bindProjectEvents();
        bindRiskEvents();
        bindProblemEvents();
        bindBugEvents();
        bindMessageEvents();
    }

    @SuppressWarnings("serial")
    private void bindProjectEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<ProjectEvent.SaveProjectSucess>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ProjectEvent.SaveProjectSucess.class;
                    }

                    @Override
                    public void handle(SaveProjectSucess event) {
                        // TODO Auto-generated method stub
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ProjectEvent.GotoMyProject>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ProjectEvent.GotoMyProject.class;
                    }

                    @Override
                    public void handle(ProjectEvent.GotoMyProject event) {
                        ProjectViewPresenter presenter = PresenterResolver
                                .getPresenter(ProjectViewPresenter.class);
                        SimpleProject project = (SimpleProject) event.getData();
                        AppContext.putVariable(ProjectContants.PROJECT_NAME,
                                project);
                        presenter.go(container,
                                new ScreenData<Project>(project));
                    }
                });
    }

    @SuppressWarnings("serial")
    private void bindRiskEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<RiskEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return RiskEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(RiskEvent.GotoAdd event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);
                        ScreenData.Add<Risk> data = new ScreenData.Add<Risk>(
                                new Risk());
                        projectView.gotoRiskView(data);
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<RiskEvent.GotoEdit>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return RiskEvent.GotoEdit.class;
                    }

                    @Override
                    public void handle(RiskEvent.GotoEdit event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);
                        ScreenData.Edit<Risk> data = new ScreenData.Edit<Risk>(
                                (Risk) event.getData());
                        projectView.gotoRiskView(data);
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<RiskEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return RiskEvent.GotoRead.class;
                    }

                    @Override
                    public void handle(RiskEvent.GotoRead event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);
                        ScreenData.Preview<Integer> data = new ScreenData.Preview<Integer>(
                                (Integer) event.getData());
                        projectView.gotoRiskView(data);
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<RiskEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return RiskEvent.GotoList.class;
                    }

                    @Override
                    public void handle(RiskEvent.GotoList event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);

                        SimpleProject project = (SimpleProject) AppContext
                                .getVariable(ProjectContants.PROJECT_NAME);

                        RiskSearchCriteria criteria = new RiskSearchCriteria();

                        criteria.setProjectId(new NumberSearchField(
                                SearchField.AND, project.getId()));
                        projectView
                                .gotoRiskView(new ScreenData.Search<RiskSearchCriteria>(
                                criteria));
                    }
                });
    }

    @SuppressWarnings("serial")
    private void bindProblemEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<ProblemEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ProblemEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(ProblemEvent.GotoAdd event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);
                        ScreenData.Add<Problem> data = new ScreenData.Add<Problem>(
                                new Problem());
                        projectView.gotoProblemView(data);
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ProblemEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ProblemEvent.GotoRead.class;
                    }

                    @Override
                    public void handle(ProblemEvent.GotoRead event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);
                        ScreenData.Preview<Integer> data = new ScreenData.Preview<Integer>(
                                (Integer) event.getData());
                        projectView.gotoProblemView(data);
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ProblemEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ProblemEvent.GotoList.class;
                    }

                    @Override
                    public void handle(ProblemEvent.GotoList event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);

                        SimpleProject project = (SimpleProject) AppContext
                                .getVariable(ProjectContants.PROJECT_NAME);

                        ProblemSearchCriteria criteria = new ProblemSearchCriteria();

                        criteria.setProjectId(new NumberSearchField(
                                SearchField.AND, project.getId()));
                        projectView
                                .gotoProblemView(new ScreenData.Search<ProblemSearchCriteria>(
                                criteria));
                    }
                });
    }

    @SuppressWarnings("serial")
    private void bindBugEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<BugEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return BugEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(BugEvent.GotoAdd event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);
                        ScreenData.Add<SimpleBug> data = new ScreenData.Add<SimpleBug>(
                                new SimpleBug());
                        projectView.gotoBugView(data);
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<BugEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return BugEvent.GotoRead.class;
                    }

                    @Override
                    public void handle(BugEvent.GotoRead event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);
                        ScreenData.Preview<Integer> data = new ScreenData.Preview<Integer>(
                                (Integer) event.getData());
                        projectView.gotoBugView(data);
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<BugEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return BugEvent.GotoList.class;
                    }

                    @Override
                    public void handle(BugEvent.GotoList event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);

                        SimpleProject project = (SimpleProject) AppContext
                                .getVariable(ProjectContants.PROJECT_NAME);

                        BugSearchCriteria criteria = new BugSearchCriteria();

                        criteria.setProjectid(new NumberSearchField(
                                SearchField.AND, project.getId()));
                        projectView
                                .gotoBugView(new ScreenData.Search<BugSearchCriteria>(
                                criteria));
                    }
                });
    }

    private void bindMessageEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<MessageEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return MessageEvent.GotoRead.class;
                    }

                    @Override
                    public void handle(MessageEvent.GotoRead event) {
                        ProjectView projectView = ViewManager
                                .getView(ProjectView.class);
                        ScreenData.Preview<Integer> data = new ScreenData.Preview<Integer>(
                                (Integer) event.getData());
                        projectView.gotoMessageView(data);
                    }
                });
    }
}

package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.bug.BugPresenter;
import com.esofthead.mycollab.module.project.view.bug.BugReadPageAction;
import com.esofthead.mycollab.module.project.view.bug.ComponentReadPageAction;
import com.esofthead.mycollab.module.project.view.bug.VersionReadPageAction;
import com.esofthead.mycollab.module.project.view.message.MessagePresenter;
import com.esofthead.mycollab.module.project.view.message.MessageReadPageAction;
import com.esofthead.mycollab.module.project.view.milestone.MilestonePresenter;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneReadPageAction;
import com.esofthead.mycollab.module.project.view.problem.ProblemPresenter;
import com.esofthead.mycollab.module.project.view.problem.ProblemReadPageAction;
import com.esofthead.mycollab.module.project.view.risk.RiskPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskReadPageAction;
import com.esofthead.mycollab.module.project.view.task.TaskGroupReadPageAction;
import com.esofthead.mycollab.module.project.view.task.TaskPresenter;
import com.esofthead.mycollab.module.project.view.task.TaskReadPageAction;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageAction;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class ProjectViewPresenter extends AbstractPresenter<ProjectView> {

    private static final long serialVersionUID = 1L;

    public ProjectViewPresenter() {
        super(ProjectView.class);
    }

    @Override
    public void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectContainer prjContainer = (ProjectContainer) container;
        prjContainer.removeAllComponents();
        prjContainer.addComponent((Component) view);
        prjContainer.setComponentAlignment((Component) view,
                Alignment.TOP_CENTER);
        
        if (data.getParams() instanceof Integer) {
            ProjectService projectService = (ProjectService)AppContext.getSpringBean(ProjectService.class);
            SimpleProject project = (SimpleProject)projectService.findProjectById((Integer)data.getParams());
            
            if (project == null) {
                AppContext.getApplication().getMainWindow().showNotification("Information", "The record is not existed", Window.Notification.TYPE_HUMANIZED_MESSAGE);
            } else {
                AppContext.putVariable(ProjectContants.PROJECT_NAME, project);
                view.constructProjectHeaderPanel(project, null);
            }
        }
    }

    @Override
    protected void onDefaultStopChain() {
        ProjectDashboardPresenter presenter = PresenterResolver.getPresenter(ProjectDashboardPresenter.class);
        presenter.go(this.view, null);
    }

    @Override
    protected void onHandleChain(ComponentContainer container, PageActionChain pageActionChain) {
        PageAction pageAction = pageActionChain.peek();
        
        AbstractPresenter presenter = null;
        
        if (pageAction instanceof MilestoneReadPageAction) {
            presenter = PresenterResolver.getPresenter(MilestonePresenter.class);
        } else if (pageAction instanceof MessageReadPageAction) {
            presenter = PresenterResolver.getPresenter(MessagePresenter.class);
        } else if (pageAction instanceof ProblemReadPageAction) {
            presenter = PresenterResolver.getPresenter(ProblemPresenter.class);
        } else if (pageAction instanceof RiskReadPageAction) {
            presenter = PresenterResolver.getPresenter(RiskPresenter.class);
        } else if (pageAction instanceof TaskReadPageAction) {
            presenter = PresenterResolver.getPresenter(TaskPresenter.class);
        } else if (pageAction instanceof TaskGroupReadPageAction) {
            presenter = PresenterResolver.getPresenter(TaskPresenter.class);
        } else if (pageAction instanceof BugReadPageAction) {
            presenter = PresenterResolver.getPresenter(BugPresenter.class);
        } else if (pageAction instanceof ComponentReadPageAction) {
            presenter = PresenterResolver.getPresenter(BugPresenter.class);
        } else if (pageAction instanceof VersionReadPageAction) {
            presenter = PresenterResolver.getPresenter(BugPresenter.class);
        }
        else {
            throw new UnsupportedOperationException("Not support page action chain " + pageAction);
        } 
        
        presenter.handleChain(view, pageActionChain);
    }
    
    
}

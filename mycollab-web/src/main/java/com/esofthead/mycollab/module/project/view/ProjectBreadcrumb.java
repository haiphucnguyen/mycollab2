/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.events.MessageEvent;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.lexaden.breadcrumb.Breadcrumb;
import com.lexaden.breadcrumb.BreadcrumbLayout;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectBreadcrumb extends Breadcrumb implements View {
    
    private SimpleProject project;
    
    public ProjectBreadcrumb() {
        this.setShowAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
        this.setHideAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
        this.setUseDefaultClickBehaviour(false);
        this.addLink(new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new ShellEvent.GotoProjectPage(this, null));
            }
        }));
        
        this.setHeight(35, Sizeable.UNITS_PIXELS);
    }
    
    public void setProject(SimpleProject project) {
        this.project = project;
    }
    
    public void gotoMessageList() {
        this.select(1);
        this.addLink(new Button("Messages"));
        AppContext.addFragment("project/message/list/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoMessage(Message message) {
        this.select(1);
        this.addLink(new Button("Messages", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new MessageEvent.GotoList(this, null));
            }
        }));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(message.getTitle()));
        AppContext.addFragment("project/message/preview/" + UrlEncodeDecoder.encode(project.getId() + "/" + message.getId()));
    }
    
    public void gotoRiskList() {
        this.select(1);
        this.addLink(new Button("Risks"));
        AppContext.addFragment("project/risk/list/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoRiskRead(Risk risk) {
        this.select(1);
        this.addLink(new Button("Risks", new GotoRiskListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(risk.getRiskname()));
        AppContext.addFragment("project/risk/preview/" + UrlEncodeDecoder.encode(project.getId() + "/" + risk.getId()));
    }
    
    public void gotoRiskEdit(final Risk risk) {
        this.select(1);
        this.addLink(new Button("Risks", new GotoRiskListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(risk.getRiskname(), new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new RiskEvent.GotoRead(this, risk.getId()));
            }
        }));
        this.addLink(new Button("Edit"));
        AppContext.addFragment("project/risk/edit/" + UrlEncodeDecoder.encode(project.getId() + "/" + risk.getId()));
    }
    
    public void gotoRiskAdd() {
        this.select(1);
        this.addLink(new Button("Risks", new GotoRiskListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Add"));
        AppContext.addFragment("project/risk/add/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    private static class GotoRiskListListener implements Button.ClickListener {
        
        @Override
        public void buttonClick(ClickEvent event) {
            EventBus.getInstance().fireEvent(new RiskEvent.GotoList(this, null));
        }
    }
    
    public void gotoMilestoneList() {
        this.select(1);
        this.addLink(new Button("Milestones"));
        AppContext.addFragment("project/milestone/list/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoMilestoneRead(Milestone milestone) {
        this.select(1);
        this.addLink(new Button("Milestones", new GotoMilestoneListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(milestone.getName()));
        AppContext.addFragment("project/milestone/preview/" + UrlEncodeDecoder.encode(project.getId() + "/" + milestone.getId()));
    }
    
    public void gotoMilestoneEdit(final Milestone milestone) {
        this.select(1);
        this.addLink(new Button("Milestones", new GotoMilestoneListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(milestone.getName(), new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new MilestoneEvent.GotoRead(this, milestone.getId()));
            }
        }));
        this.addLink(new Button("Edit"));
        AppContext.addFragment("project/milestone/edit/" + UrlEncodeDecoder.encode(project.getId() + "/" + milestone.getId()));
    }
    
    public void gotoMilestoneAdd() {
        this.select(1);
        this.addLink(new Button("Milestones", new GotoMilestoneListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Add"));
        AppContext.addFragment("project/milestone/add/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    private static class GotoMilestoneListListener implements Button.ClickListener {
        
        @Override
        public void buttonClick(ClickEvent event) {
            EventBus.getInstance().fireEvent(new MilestoneEvent.GotoList(this, null));
        }
    }
    
    public void gotoProblemList() {
        this.select(1);
        this.addLink(new Button("Problems"));
        AppContext.addFragment("project/problem/list/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoProblemRead(Problem problem) {
        this.select(1);
        this.addLink(new Button("Problems", new GotoProblemListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(problem.getIssuename()));
        AppContext.addFragment("project/problem/preview/" + UrlEncodeDecoder.encode(project.getId() + "/" + problem.getId()));
    }
    
    public void gotoProblemEdit(final Problem problem) {
        this.select(1);
        this.addLink(new Button("Problems", new GotoProblemListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(problem.getIssuename(), new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new ProblemEvent.GotoRead(this, problem.getId()));
            }
        }));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button("Edit"));
        AppContext.addFragment("project/problem/edit/" + UrlEncodeDecoder.encode(project.getId() + "/" + problem.getId()));
    }
    
    public void gotoProblemAdd() {
        this.select(1);
        this.addLink(new Button("Problems", new GotoProblemListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Add"));
        AppContext.addFragment("project/problem/add/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    private static class GotoProblemListListener implements Button.ClickListener {
        
        @Override
        public void buttonClick(ClickEvent event) {
            EventBus.getInstance().fireEvent(new ProblemEvent.GotoList(this, null));
        }
    }
    
    public void gotoTaskDashboard() {
        this.select(1);
        this.addLink(new Button("Task Assignments"));
        AppContext.addFragment("project/task/dashboard/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoTaskListReorder() {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task Group: Reorder"));
        AppContext.addFragment("project/task/dashboard/reorder/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoTaskGroupAdd() {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task Group: Add"));
        AppContext.addFragment("project/task/taskgroup/add/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoTaskGroupRead(TaskList taskList) {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task Group: " + taskList.getName()));
        AppContext.addFragment("project/task/taskgroup/preview/" + UrlEncodeDecoder.encode(project.getId() + "/" + taskList.getId()));
    }
    
    public void gotoTaskGroupEdit(final TaskList taskList) {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task Group: " + taskList.getName(), new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new TaskListEvent.GotoRead(this, taskList.getId()));
            }
        }));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button("Edit"));
        AppContext.addFragment("project/task/taskgroup/edit/" + UrlEncodeDecoder.encode(project.getId() + "/" + taskList.getId()));
    }
    
    public void gotoTaskAdd() {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task: Add"));
        AppContext.addFragment("project/task/task/add/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoTaskRead(Task task) {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task: " + task.getTaskname()));
        AppContext.addFragment("project/task/task/preview/" + UrlEncodeDecoder.encode(project.getId() + "/" + task.getId()));
    }
    
    public void gotoTaskEdit(final Task task) {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task: " + task.getTaskname(), new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new TaskEvent.GotoRead(this, task.getId()));
            }
        }));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button("Edit"));
        AppContext.addFragment("project/task/task/edit/" + UrlEncodeDecoder.encode(project.getId() + "/" + task.getId()));
    }
    
    public class GotoTaskAssignmentDashboard implements Button.ClickListener {
        
        @Override
        public void buttonClick(ClickEvent event) {
            EventBus.getInstance().fireEvent(new TaskListEvent.GotoTaskListScreen(this, null));
        }
    }
    
    public void gotoBugDashboard() {
        this.select(1);
        this.addLink(new Button("Bugs"));
        AppContext.addFragment("project/bug/dashboard/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoBugList() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("List"));
        AppContext.addFragment("project/bug/list/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoBugAdd() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Add"));
        AppContext.addFragment("project/bug/add/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoBugEdit(final Bug bug) {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(bug.getSummary(), new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new BugEvent.GotoRead(this, bug.getId()));
            }
        }));
        this.addLink(new Button("Edit"));
        AppContext.addFragment("project/bug/edit/" + UrlEncodeDecoder.encode(project.getId() + "/" + bug.getId()));
    }
    
    public void gotoBugRead(Bug bug) {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(bug.getSummary()));
        AppContext.addFragment("project/bug/preview/" + UrlEncodeDecoder.encode(project.getId() + "/" + bug.getId()));
    }
    
    public void gotoVersionList() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Versions"));
        AppContext.addFragment("project/bug/version/list/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoVersionAdd() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Versions", new GotoVersionListener()));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button("Add"));
        AppContext.addFragment("project/bug/version/add/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoVersionEdit(final Version version) {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Versions", new GotoVersionListener()));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button(version.getVersionname(), new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new BugVersionEvent.GotoRead(this, version.getId()));
            }
        }));
        this.addLink(new Button("Edit"));
        AppContext.addFragment("project/bug/version/edit/" + UrlEncodeDecoder.encode(project.getId() + "/" + version.getId()));
    }
    
    public void gotoVersionRead(Version version) {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Versions", new GotoVersionListener()));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button(version.getVersionname()));
        AppContext.addFragment("project/bug/version/preview/" + UrlEncodeDecoder.encode(project.getId() + "/" + version.getId()));
    }
    
    private class GotoVersionListener implements Button.ClickListener {
        
        @Override
        public void buttonClick(ClickEvent event) {
            EventBus.getInstance().fireEvent(new BugVersionEvent.GotoList(this, null));
        }
    }
    
    public void gotoComponentnList() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Components"));
        AppContext.addFragment("project/bug/component/list/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoComponentAdd() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Components", new GotoComponentListener()));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button("Add"));
        AppContext.addFragment("project/bug/component/add/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoComponentEdit(final Component component) {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Components", new GotoComponentListener()));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button(component.getComponentname(), new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(new BugComponentEvent.GotoRead(this, component.getId()));
            }
        }));
        this.setLinkEnabled(true, 4);
        this.addLink(new Button("Edit"));
        AppContext.addFragment("project/bug/component/edit/" + UrlEncodeDecoder.encode(project.getId() + "/" + component.getId()));
    }
    
    public void gotoComponentRead(Component component) {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Components", new GotoComponentListener()));
        this.addLink(new Button(component.getComponentname()));
        AppContext.addFragment("project/bug/component/preview/" + UrlEncodeDecoder.encode(project.getId() + "/" + component.getId()));
    }
    
    private static class GotoComponentListener implements Button.ClickListener {
        
        @Override
        public void buttonClick(ClickEvent event) {
            EventBus.getInstance().fireEvent(new BugComponentEvent.GotoList(this, null));
        }
    }
    
    private static class GotoBugDashboardListener implements Button.ClickListener {
        
        @Override
        public void buttonClick(ClickEvent event) {
            EventBus.getInstance().fireEvent(new BugEvent.GotoDashboard(this, null));
        }
    }
    
    public void gotoProjectDashboard() {
        this.select(1);
        AppContext.addFragment("project/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    public void gotoProjectEdit() {
        this.select(1);
        this.addLink(new Button("Edit"));
        AppContext.addFragment("project/edit/" + UrlEncodeDecoder.encode(project.getId()));
    }
    
    @Override
    public int getComponentCount() {
        if (getCompositionRoot() != null) {
            final BreadcrumbLayout compositionRoot = (BreadcrumbLayout) getCompositionRoot();
            return compositionRoot.getComponentCount();
        }
        return super.getComponentCount();
    }
    
    @Override
    public ComponentContainer getWidget() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void addViewListener(ApplicationEventListener<? extends ApplicationEvent> listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

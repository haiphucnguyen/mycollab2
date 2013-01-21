/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.Risk;
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

    public void gotoMessageList() {
        this.select(1);
        this.addLink(new Button("Messages"));
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
    }

    public void gotoRiskList() {
        this.select(1);
        this.addLink(new Button("Risks"));
    }

    public void gotoRiskRead(Risk risk) {
        this.select(1);
        this.addLink(new Button("Risks", new GotoRiskListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(risk.getRiskname()));
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
    }

    public void gotoRiskAdd() {
        this.select(1);
        this.addLink(new Button("Risks", new GotoRiskListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Add"));
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
    }

    public void gotoMilestoneRead(Milestone milestone) {
        this.select(1);
        this.addLink(new Button("Milestones", new GotoMilestoneListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(milestone.getName()));
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
    }

    public void gotoMilestoneAdd() {
        this.select(1);
        this.addLink(new Button("Milestones", new GotoMilestoneListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Add"));
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
    }

    public void gotoProblemRead(Problem problem) {
        this.select(1);
        this.addLink(new Button("Problems", new GotoProblemListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(problem.getIssuename()));
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
    }

    public void gotoProblemAdd() {
        this.select(1);
        this.addLink(new Button("Problems", new GotoProblemListListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Add"));
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
    }
    
    public void gotoTaskListReorder() {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task Group: Reorder"));
    }
    
    public void gotoTaskGroupAdd() {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task Group: Add"));
    }
    
    public void gotoTaskGroupRead(TaskList taskList) {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task Group: " + taskList.getName()));
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
    }
    
    public void gotoTaskAdd() {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task: Add"));
    }
    
    public void gotoTaskRead(Task task) {
        this.select(1);
        this.addLink(new Button("Task Assignments", new GotoTaskAssignmentDashboard()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Task: " + task.getTaskname()));
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
    }

    public void gotoBugList() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("List"));
    }

    public void gotoBugAdd() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Add"));
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
    }

    public void gotoBugRead(Bug bug) {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(bug.getSummary()));
    }

    public void gotoVersionList() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Versions"));
    }

    public void gotoVersionAdd() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Versions", new GotoVersionListener()));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button("Add"));
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
    }

    public void gotoVersionRead(Version version) {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Versions", new GotoVersionListener()));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button(version.getVersionname()));
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
    }

    public void gotoComponentAdd() {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Components", new GotoComponentListener()));
        this.setLinkEnabled(true, 3);
        this.addLink(new Button("Add"));
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
    }

    public void gotoComponentRead(Component component) {
        this.select(1);
        this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button("Components", new GotoComponentListener()));
        this.addLink(new Button(component.getComponentname()));
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
    }

    public void gotoProjectEdit() {
        this.select(1);
        this.addLink(new Button("Edit"));
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

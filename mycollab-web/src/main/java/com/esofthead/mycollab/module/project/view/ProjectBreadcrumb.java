/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.Risk;
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
        this.addLink(new Button("Messages"));
        this.addLink(new Button(message.getTitle()));
    }
    
    public void gotoRiskList() {
        this.select(1);
        this.addLink(new Button("Risks"));
    }
    
    public void gotoRiskRead(Risk risk) {
        this.select(1);
        this.addLink(new Button("Risks"));
        this.addLink(new Button(risk.getRiskname()));
    }
    
    public void gotoRiskEdit(Risk risk) {
        this.select(1);
        this.addLink(new Button("Risks"));
        this.addLink(new Button(risk.getRiskname()));
        this.addLink(new Button("Edit"));
    }
    
    public void gotoRiskAdd() {
        this.select(1);
        this.addLink(new Button("Risks"));
        this.addLink(new Button("Add"));
    }
    
    public void gotoMilestoneList() {
        this.select(1);
        this.addLink(new Button("Milestones"));
    }
    
    public void gotoMilestoneRead(Milestone milestone) {
        this.select(1);
        this.addLink(new Button("Milestones"));
        this.addLink(new Button(milestone.getName()));
    }
    
    public void gotoMilestoneEdit(Milestone milestone) {
        this.select(1);
        this.addLink(new Button("Milestones"));
        this.addLink(new Button(milestone.getName()));
        this.addLink(new Button("Edit"));
    }
    
    public void gotoMilestoneAdd() {
        this.select(1);
        this.addLink(new Button("Milestones"));
        this.addLink(new Button("Add"));
    }
    
    public void gotoProblemList() {
        this.select(1);
        this.addLink(new Button("Problems"));
    }
    
    public void gotoProblemRead(Problem problem) {
        this.select(1);
        this.addLink(new Button("Problems"));
        this.addLink(new Button(problem.getIssuename()));
    }
    
    public void gotoProblemEdit(Problem problem) {
        this.select(1);
        this.addLink(new Button("Problems"));
        this.addLink(new Button(problem.getIssuename()));
        this.addLink(new Button("Edit"));
    }
    
    public void gotoProblemAdd() {
        this.select(1);
        this.addLink(new Button("Problems"));
        this.addLink(new Button("Add"));
    }
    
    public void gotoBugDashboard() {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Dashboard"));
    }
    
    public void gotoBugList() {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("List"));
    }
    
    public void gotoBugAdd() {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Add"));
    }
    
    public void gotoBugEdit(Bug bug) {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button(bug.getSummary()));
        this.addLink(new Button("Edit"));
    }
    
    public void gotoBugRead(Bug bug) {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button(bug.getSummary()));
        this.addLink(new Button("Read"));
    }
    
    public void gotoVersionList() {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Versions"));
    }
    
    public void gotoVersionAdd() {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Versions"));
        this.addLink(new Button("Add"));
    }
    
    public void gotoVersionEdit(Version version) {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Versions"));
        this.addLink(new Button(version.getVersionname()));
        this.addLink(new Button("Edit"));
    }
    
    public void gotoVersionRead(Version version) {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Versions"));
        this.addLink(new Button(version.getVersionname()));
        this.addLink(new Button("Read"));
    }
    
    public void gotoComponentnList() {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Components"));
    }
    
    public void gotoComponentAdd() {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Components"));
        this.addLink(new Button("Add"));
    }
    
    public void gotoComponentEdit(Component component) {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Components"));
        this.addLink(new Button(component.getComponentname()));
        this.addLink(new Button("Edit"));
    }
    
    public void gotoComponentRead(Component component) {
        this.select(1);
        this.addLink(new Button("Bugs"));
        this.addLink(new Button("Components"));
        this.addLink(new Button(component.getComponentname()));
        this.addLink(new Button("Read"));
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

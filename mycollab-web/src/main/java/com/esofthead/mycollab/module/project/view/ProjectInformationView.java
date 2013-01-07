/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectInformationView extends VerticalLayout {

    private SimpleProject project;

    public ProjectInformationView() {
        project = (SimpleProject) AppContext.getVariable(ProjectContants.PROJECT_NAME);

        HorizontalLayout header = new HorizontalLayout();
        header.addComponent(new Label(project.getName()));

        TabSheet container = new TabSheet();
        //Project information
        container.addTab(constructProjectInformationView(), "Project Information");

        //Financial information
        VerticalLayout financialLayout = new VerticalLayout();
        container.addTab(financialLayout, "Financial");

        //More information
        VerticalLayout moreLayout = new VerticalLayout();
        container.addTab(moreLayout, "Charts");

        ActivityStreamPanel activityChannel = new ActivityStreamPanel();
        container.addTab(activityChannel, "Activities Stream");

        this.addComponent(header);
        this.addComponent(container);
    }

    private Component constructProjectInformationView() {
        VerticalLayout projectLayout = new VerticalLayout();
        GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(2, 5);
        gridLayout.getLayout().setWidth("100%");

        Label descLbl = new Label();
        if (project.getDescription() != null) {
            descLbl.setValue(project.getDescription());
        }
        gridLayout.addComponent(descLbl, "Description", 0, 0, 2, "100%");
        
        Label priorityLbl = new Label();
        if (project.getPriority() != null) {
            priorityLbl.setValue(project.getPriority());
        }
        gridLayout.addComponent(priorityLbl, "Priority", 0, 1);
        
        Label statusLbl = new Label();
        if (project.getProjectstatus() != null) {
            statusLbl.setValue(project.getProjectstatus());
        }
        gridLayout.addComponent(statusLbl, "Status", 0, 2);
        
        Label typeLbl = new Label();
        if (project.getProjecttype() != null) {
            typeLbl.setValue(project.getProjecttype());
        }
        gridLayout.addComponent(typeLbl, "Type", 1, 2);

        Label planStartDate = new Label(AppContext.formatDate(project.getPlanstartdate()));
        gridLayout.addComponent(planStartDate, "Plan Start Date", 0, 3);

        Label actualStartDate = new Label(AppContext.formatDate(project.getActualstartdate()));
        gridLayout.addComponent(actualStartDate, "Actual Start Date", 1, 3);

        Label planEndDate = new Label(AppContext.formatDate(project.getPlanenddate()));
        gridLayout.addComponent(planEndDate, "Plan End Date", 0, 4);


        Label actualEndDate = new Label(AppContext.formatDate(project.getActualenddate()));
        gridLayout.addComponent(actualEndDate, "Actual End Date", 1, 4);

        projectLayout.addComponent(gridLayout.getLayout());
        return projectLayout;
    }
}

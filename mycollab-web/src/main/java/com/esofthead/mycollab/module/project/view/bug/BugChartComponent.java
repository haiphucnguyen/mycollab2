/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class BugChartComponent extends Depot {

    private String[] reportDashboard = {"BugsByPriority", "BugsByStatus", "BugByResolution"};
    private int currentReportIndex = 0;
    private SimpleProject project;

    public BugChartComponent() {
        super("Bugs Dashboard", new HorizontalLayout(), new VerticalLayout());
        project = (SimpleProject) AppContext.getVariable(ProjectContants.PROJECT_NAME);
        initUI();
    }

    private void initUI() {
        HorizontalLayout headerContainer = (HorizontalLayout) this.headerContent;
        headerContainer.setWidth("100%");
        headerContainer.setSpacing(true);
        Label emptySpace = new Label();
        headerContainer.addComponent(emptySpace);
        headerContainer.setExpandRatio(emptySpace, 1.0f);

        Button prevButton = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (currentReportIndex == 0) {
                    currentReportIndex = reportDashboard.length - 1;
                } else {
                    currentReportIndex--;
                }

                displayReport();
            }
        });
        prevButton.setIcon(new ThemeResource("icons/16/previousBlue.png"));
        prevButton.setStyleName("link");
        headerContainer.addComponent(prevButton);

        Button nextBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (currentReportIndex >= (reportDashboard.length - 1)) {
                    currentReportIndex = 0;
                } else {
                    currentReportIndex++;
                }
                displayReport();
            }
        });
        nextBtn.setIcon(new ThemeResource("icons/16/nextBlue.png"));
        nextBtn.setStyleName("link");
        headerContainer.addComponent(nextBtn);

        displayReport();
    }

    public void displayReport() {
        String reportName = reportDashboard[currentReportIndex];

        VerticalLayout bodyContent = (VerticalLayout) this.bodyContent;
        bodyContent.removeAllComponents();;

        if ("BugsByPriority".equals(reportName)) {
            PrioritySummaryWidget prioritySummaryWidget = new PrioritySummaryWidget();
            LazyLoadWrapper lazyComp = new LazyLoadWrapper(prioritySummaryWidget);
            bodyContent.addComponent(lazyComp);
            bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

            BugSearchCriteria prioritySearchCriteria = new BugSearchCriteria();
            prioritySearchCriteria.setProjectid(new NumberSearchField(project.getId()));
            prioritySummaryWidget.setSearchCriteria(prioritySearchCriteria);
        } else if ("BugsByStatus".equals(reportName)) {
            StatusSummaryWidget statusSummaryWidget = new StatusSummaryWidget();
            LazyLoadWrapper lazyComp = new LazyLoadWrapper(statusSummaryWidget);
            bodyContent.addComponent(lazyComp);
            bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

            BugSearchCriteria statusSearchCriteria = new BugSearchCriteria();
            statusSearchCriteria.setProjectid(new NumberSearchField(project.getId()));
            statusSummaryWidget.setSearchCriteria(statusSearchCriteria);
        } else if ("BugByResolution".equals(reportName)) {
            BugResolutionSummaryWidget resolutionSummaryWdiget = new BugResolutionSummaryWidget();
            LazyLoadWrapper lazyComp = new LazyLoadWrapper(resolutionSummaryWdiget);
            bodyContent.addComponent(lazyComp);
            bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

            BugSearchCriteria statusSearchCriteria = new BugSearchCriteria();
            statusSearchCriteria.setProjectid(new NumberSearchField(project.getId()));
            resolutionSummaryWdiget.setSearchCriteria(statusSearchCriteria);
        }
    }
}

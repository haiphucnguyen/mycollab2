/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityLeadSourceDashboard;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunitySalesStageDashboard;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class SalesDashboardView extends Depot {
    
    private String[] reportDashboard = {"OpportunitySalesStage", "OpportunityLeadSource"};
    private int currentReportIndex = 0;
    
    public SalesDashboardView() {
        super("Sales Dashboard", new HorizontalLayout(), new VerticalLayout());
        
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
            public void buttonClick(ClickEvent event) {
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
            public void buttonClick(ClickEvent event) {
                if (currentReportIndex >= (reportDashboard.length - 1)) {
                    currentReportIndex = 0;
                } else {
                    currentReportIndex ++;
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
        
        if ("OpportunitySalesStage".equals(reportName)) {
            OpportunitySalesStageDashboard salesStageDashboard = new OpportunitySalesStageDashboard();
            LazyLoadWrapper lazyComp = new LazyLoadWrapper(salesStageDashboard);
            bodyContent.addComponent(lazyComp);
            bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);
            
            OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
            criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
            salesStageDashboard.setSearchCriteria(criteria);
        } else if ("OpportunityLeadSource".equals(reportName)) {
            OpportunityLeadSourceDashboard leadSourceDashboard = new OpportunityLeadSourceDashboard();
            LazyLoadWrapper lazyComp = new LazyLoadWrapper(leadSourceDashboard);
            bodyContent.addComponent(lazyComp);
            bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);
            
            OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
            criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
            leadSourceDashboard.setSearchCriteria(criteria);
        }
    }
}

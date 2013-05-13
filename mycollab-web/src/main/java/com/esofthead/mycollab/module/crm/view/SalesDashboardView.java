/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityLeadSourceDashboard;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunitySalesStageDashboard;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
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
	private static final long serialVersionUID = 1L;
	private final String[] reportDashboard = { "OpportunitySalesStage",
			"OpportunityLeadSource" };
	private int currentReportIndex = 0;

	public SalesDashboardView() {
		super("Sales Dashboard", new HorizontalLayout(), new VerticalLayout(),
				"390px", "200px");

		initUI();
	}

	private void initUI() {
		HorizontalLayout headerContainer = (HorizontalLayout) this.headerContent;
		headerContainer.setWidth("100%");
		headerContainer.setSpacing(true);
		Label emptySpace = new Label();
		headerContainer.addComponent(emptySpace);
		headerContainer.setExpandRatio(emptySpace, 1.0f);

		final PopupButton saleChartPopup = new PopupButton("Opportunity Sales Stage");
		saleChartPopup.addStyleName("link");

		VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("200px");

		Button btnOpportunitySales = new Button("Opportunity Sales Stage",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						saleChartPopup.setPopupVisible(false);
						saleChartPopup.setCaption("Opportunity Sales Stage");
						currentReportIndex = 0;
						displayReport();
					}
				});
		btnOpportunitySales.setStyleName("link");
		filterBtnLayout.addComponent(btnOpportunitySales);

		Button btnOpportunityLead = new Button("Opportunity Lead Source",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						saleChartPopup.setPopupVisible(false);
						saleChartPopup.setCaption("Opportunity Lead Source");
						currentReportIndex = 1;
						displayReport();
					}
				});
		btnOpportunityLead.setStyleName("link");
		filterBtnLayout.addComponent(btnOpportunityLead);

		displayReport();

		saleChartPopup.addComponent(filterBtnLayout);
		headerContainer.addComponent(saleChartPopup);
	}

	public void displayReport() {
		String reportName = reportDashboard[currentReportIndex];

		VerticalLayout bodyContent = (VerticalLayout) this.bodyContent;
		bodyContent.removeAllComponents();

		if ("OpportunitySalesStage".equals(reportName)) {
			OpportunitySalesStageDashboard salesStageDashboard = new OpportunitySalesStageDashboard();
			LazyLoadWrapper lazyComp = new LazyLoadWrapper(salesStageDashboard);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
			criteria.setSaccountid(new NumberSearchField(AppContext
					.getAccountId()));
			salesStageDashboard.setSearchCriteria(criteria);
		} else if ("OpportunityLeadSource".equals(reportName)) {
			OpportunityLeadSourceDashboard leadSourceDashboard = new OpportunityLeadSourceDashboard();
			LazyLoadWrapper lazyComp = new LazyLoadWrapper(leadSourceDashboard);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
			criteria.setSaccountid(new NumberSearchField(AppContext
					.getAccountId()));
			leadSourceDashboard.setSearchCriteria(criteria);
		}
	}
}

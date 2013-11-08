/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
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
		super("Sales Dashboard", null, new CssLayout(), "100%", "200px");
		this.bodyContent.setSizeFull();
		this.initUI();
		this.setHeaderColor(true);
		this.setContentBorder(true);
	}

	public void displayReport() {
		final String reportName = this.reportDashboard[this.currentReportIndex];

		final CssLayout bodyContent = (CssLayout) this.bodyContent;
		bodyContent.removeAllComponents();

		if ("OpportunitySalesStage".equals(reportName)) {
			final OpportunitySalesStageDashboard salesStageDashboard = new OpportunitySalesStageDashboard();
			final LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					salesStageDashboard);
			bodyContent.addComponent(lazyComp);

			final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
			criteria.setSaccountid(new NumberSearchField(AppContext
					.getAccountId()));
			salesStageDashboard.setSearchCriteria(criteria);
		} else if ("OpportunityLeadSource".equals(reportName)) {
			final OpportunityLeadSourceDashboard leadSourceDashboard = new OpportunityLeadSourceDashboard();
			final LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					leadSourceDashboard);
			bodyContent.addComponent(lazyComp);

			final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
			criteria.setSaccountid(new NumberSearchField(AppContext
					.getAccountId()));
			leadSourceDashboard.setSearchCriteria(criteria);
		}
	}

	private void initUI() {

		final PopupButton saleChartPopup = new PopupButton(
				"Opportunity Sales Stage");
		saleChartPopup.addStyleName("link");

		final VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("200px");

		final Button btnOpportunitySales = new Button(
				"Opportunity Sales Stage", new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						saleChartPopup.setPopupVisible(false);
						saleChartPopup.setCaption("Opportunity Sales Stage");
						SalesDashboardView.this.currentReportIndex = 0;
						SalesDashboardView.this.displayReport();
					}
				});
		btnOpportunitySales.setStyleName("link");
		filterBtnLayout.addComponent(btnOpportunitySales);

		final Button btnOpportunityLead = new Button("Opportunity Lead Source",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						saleChartPopup.setPopupVisible(false);
						saleChartPopup.setCaption("Opportunity Lead Source");
						SalesDashboardView.this.currentReportIndex = 1;
						SalesDashboardView.this.displayReport();
					}
				});
		btnOpportunityLead.setStyleName("link");
		filterBtnLayout.addComponent(btnOpportunityLead);

		this.displayReport();

		saleChartPopup.addComponent(filterBtnLayout);
		this.addHeaderElement(saleChartPopup);
	}
}

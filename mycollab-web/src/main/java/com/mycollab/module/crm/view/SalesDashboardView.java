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

package com.mycollab.module.crm.view;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.mycollab.module.crm.view.opportunity.IOpportunityLeadSourceDashboard;
import com.mycollab.module.crm.view.opportunity.IOpportunitySalesStageDashboard;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.Depot;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hene.popupbutton.PopupButton;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SalesDashboardView extends Depot {
    private static final long serialVersionUID = 1L;
    private final String[] reportDashboard = {"OpportunitySalesStage", "OpportunityLeadSource"};
    private int currentReportIndex = 0;

    public SalesDashboardView() {
        super("Sales Dashboard", new VerticalLayout());
        this.bodyContent.setSizeFull();
        this.initUI();
        this.setContentBorder(true);
    }

    public void displayReport() {
        final String reportName = this.reportDashboard[this.currentReportIndex];

        final VerticalLayout bodyContent = (VerticalLayout) this.bodyContent;
        bodyContent.removeAllComponents();
        bodyContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        if ("OpportunitySalesStage".equals(reportName)) {
            this.setTitle("Opportunity Sales Stage");
            IOpportunitySalesStageDashboard salesStageDashboard = ViewManager.getCacheComponent(IOpportunitySalesStageDashboard.class);
            bodyContent.addComponent(salesStageDashboard);

            final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
            criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
            salesStageDashboard.displayChart(criteria);
        } else if ("OpportunityLeadSource".equals(reportName)) {
            this.setTitle("Opportunity Lead Source");
            IOpportunityLeadSourceDashboard leadSourceDashboard = ViewManager.getCacheComponent(IOpportunityLeadSourceDashboard.class);
            bodyContent.addComponent(leadSourceDashboard);

            final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
            criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
            leadSourceDashboard.displayChart(criteria);
        }
    }

    private void initUI() {
        final PopupButton saleChartPopup = new PopupButton("");
        saleChartPopup.setIcon(FontAwesome.CARET_SQUARE_O_DOWN);
        saleChartPopup.setStyleName(UIConstants.BUTTON_ICON_ONLY);

        final OptionPopupContent filterBtnLayout = new OptionPopupContent();

        final Button btnOpportunitySales = new Button("Opportunity Sales Stage", clickEvent -> {
            saleChartPopup.setPopupVisible(false);
            currentReportIndex = 0;
            displayReport();
        });
        filterBtnLayout.addOption(btnOpportunitySales);

        final Button btnOpportunityLead = new Button("Opportunity Lead Source", clickEvent -> {
            saleChartPopup.setPopupVisible(false);
            currentReportIndex = 1;
            displayReport();
        });
        filterBtnLayout.addOption(btnOpportunityLead);

        this.displayReport();
        saleChartPopup.setContent(filterBtnLayout);
        this.addHeaderElement(saleChartPopup);
    }
}

package com.esofthead.mycollab.pro.module.crm.view.opportunity;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.opportunity.IOpportunityLeadSourceDashboard;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0.0
 */
@SuppressWarnings("serial")
@ViewComponent
public class OpportunityLeadSourceDashboard extends CssLayout implements IOpportunityLeadSourceDashboard {

    public OpportunityLeadSourceDashboard() {
        this.setSizeFull();
    }

    public void displayChart(OpportunitySearchCriteria searchCriteria) {
        this.removeAllComponents();

        final OpportunityService opportunityService = ApplicationContextUtil.getSpringBean(OpportunityService.class);

        List<GroupItem> groupItems = opportunityService.getLeadSourcesSummary(searchCriteria);
        DataSeries series = new DataSeries("Lead Source");

        final String[] leadSources = CrmDataTypeFactory.getLeadSourceList();
        for (final String status : leadSources) {
            boolean isFound = false;
            for (final GroupItem item : groupItems) {
                if (status.equals(item.getGroupid())) {
                    series.add(new DataSeriesItem(status, item.getValue()));
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                series.add(new DataSeriesItem(status, 0));
            }
        }

        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Sales Stage");
        conf.setCredits(new Credits(""));

        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(1);
        tooltip.setPointFormat("{series.name}: {point.percentage}%");
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);

        conf.setPlotOptions(plotOptions);

        conf.setSeries(series);
        chart.drawChart(conf);

        this.addComponent(chart);
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(ViewListener listener) {
    }
}

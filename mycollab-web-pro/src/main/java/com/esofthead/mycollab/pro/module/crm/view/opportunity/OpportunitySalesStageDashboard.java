package com.esofthead.mycollab.pro.module.crm.view.opportunity;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.opportunity.IOpportunitySalesStageDashboard;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.charts.*;
import com.vaadin.addon.charts.model.*;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0.0
 */
@ViewComponent
public class OpportunitySalesStageDashboard extends CssLayout implements
        IOpportunitySalesStageDashboard {
    private static final long serialVersionUID = 1L;

    public OpportunitySalesStageDashboard() {
        this.setSizeFull();
    }

    public void displayChart(OpportunitySearchCriteria searchCriteria) {
        this.removeAllComponents();

        final OpportunityService opportunityService = ApplicationContextUtil.getSpringBean(OpportunityService.class);
        final List<GroupItem> groupItems = opportunityService.getSalesStageSummary(searchCriteria);

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

        DataSeries series = new DataSeries("Sales Stage");

        final String[] salesStages = CrmDataTypeFactory
                .getOpportunitySalesStageList();
        for (final String status : salesStages) {
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

        chart.addChartSelectionListener(new ChartSelectionListener() {
            public void onSelection(ChartSelectionEvent e) {
            }
        });
        chart.addLegendItemClickListener(new LegendItemClickListener() {
            public void onClick(LegendItemClickEvent e) {
            }
        });
        chart.addChartClickListener(new ChartClickListener() {
            public void onClick(ChartClickEvent e) {
            }
        });

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

package com.esofthead.mycollab.premium.ui.chart;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.IInteractiveChartComponent;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.LegendItemClickEvent;
import com.vaadin.addon.charts.LegendItemClickListener;
import com.vaadin.addon.charts.model.*;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public abstract class PieChartWrapper<S extends SearchCriteria> extends CssLayout implements IInteractiveChartComponent {
    protected S searchCriteria;

    public PieChartWrapper() {
        setSizeFull();
    }

    public void setSearchCriteria(S searchCriteria) {
        removeAllComponents();
        this.searchCriteria = searchCriteria;
        Chart chart = createPieChart();
        this.addComponent(chart);
    }

    abstract protected DataSeries getSeries();

    protected Chart createPieChart() {
        final DataSeries series = getSeries();
        Chart chart = new Chart(ChartType.PIE);
        chart.addLegendItemClickListener(new LegendItemClickListener() {
            @Override
            public void onClick(LegendItemClickEvent legendItemClickEvent) {
                DataSeriesItem dataSeries = series.get(legendItemClickEvent.getSeriesItemIndex());
                String key = dataSeries.getName();
                clickLegendItem(key);
            }
        });
        Configuration conf = chart.getConfiguration();

        conf.setTitle("");
        conf.setCredits(new Credits(""));

        Tooltip tooltip = new Tooltip();
        tooltip.setPointFormat("{point.y}");
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);

        conf.setPlotOptions(plotOptions);

        conf.setSeries(series);
        chart.drawChart(conf);
        return chart;
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(ViewListener listener) {

    }
}

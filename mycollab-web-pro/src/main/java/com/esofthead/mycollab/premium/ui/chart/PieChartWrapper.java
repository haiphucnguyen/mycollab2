package com.esofthead.mycollab.premium.ui.chart;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.LegendItemClickEvent;
import com.vaadin.addon.charts.LegendItemClickListener;
import com.vaadin.addon.charts.model.*;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public abstract class PieChartWrapper<S extends SearchCriteria> extends CssLayout implements PageView {
    protected S searchCriteria;
    protected List<GroupItem> groupItems;

    public PieChartWrapper() {
        setSizeFull();
    }

    public void displayChart(S searchCriteria) {
        removeAllComponents();
        this.searchCriteria = searchCriteria;
        groupItems = loadGroupItems();
        Chart chart = createPieChart();
        this.addComponent(chart);
    }

    abstract protected List<GroupItem> loadGroupItems();

    abstract protected DataSeries buildChartSeries();

    protected Chart createPieChart() {
        final DataSeries series = buildChartSeries();
        Chart chart = new Chart(ChartType.PIE);
        chart.addLegendItemClickListener(new LegendItemClickListener() {
            @Override
            public void onClick(LegendItemClickEvent legendItemClickEvent) {
                DataSeriesItem dataSeries = series.get(legendItemClickEvent.getSeriesItemIndex());
                if (dataSeries instanceof DataSeriesItemExt) {
                    String key = ((DataSeriesItemExt) dataSeries).getKey();
                    clickLegendItem(key);
                } else {
                    String key = dataSeries.getName();
                    clickLegendItem(key);
                }

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

    abstract protected void clickLegendItem(String key);

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(ViewListener listener) {

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.chart.TimeSeriesChartWrapper;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author haiphucnguyen
 */
public class BugTrendReportWidget extends TimeSeriesChartWrapper<BugSearchCriteria> {

    public BugTrendReportWidget() {
         super("Bugs By Priority", 450, 300);
    }
    
    @Override
    protected XYSeriesCollection createDataset(BugSearchCriteria criteria) {
        XYSeries series = new XYSeries("XYGraph");
        series.add(1, 1);
        series.add(1, 2);
        series.add(2, 1);
        series.add(3, 9);
        series.add(4, 10);
        // Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui.chart;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.vaadin.ui.VerticalLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author haiphucnguyen
 */
public abstract class TimeSeriesChartWrapper <S extends SearchCriteria> extends VerticalLayout {

    private String title;
    private int height;
    private int width;

    public TimeSeriesChartWrapper(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void setSearchCriteria(S criteria) {
        this.removeAllComponents();

        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
            "XY Chart",
            "x-axis",
            "y-axis",
            createDataset(criteria),
            PlotOrientation.VERTICAL,  // Plot Orientation
            true,                      // Show Legend
            true,                      // Use tooltips
            false                      // Configure chart to generate URLs?
            );

        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);
        wrapper.setHeight(height + "px");
        wrapper.setWidth(width + "px");
        wrapper.setGraphHeight(height);
        wrapper.setGraphWidth(width);
        /////////////////////////////////////////////
        this.addComponent(wrapper);
    }
    
    protected abstract XYSeriesCollection createDataset(S criteria);
    
}

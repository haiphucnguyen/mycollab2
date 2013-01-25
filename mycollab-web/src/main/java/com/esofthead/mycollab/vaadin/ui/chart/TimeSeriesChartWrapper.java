/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui.chart;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
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
        this.addComponent(new Label("AAA"));
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void setSearchCriteria(S criteria) {
        this.removeAllComponents();

        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
            title,
            "x-axis",
            "y-axis",
            createDataset(criteria),
            PlotOrientation.VERTICAL,  // Plot Orientation
            true,                      // Show Legend
            true,                      // Use tooltips
            false                      // Configure chart to generate URLs?
            );

        System.out.println("Export chart");
        try {
            ChartUtilities.saveChartAsJPEG(new File("/Users/haiphucnguyen/Downloads/test.jpg"), chart, 500, 300);
        } catch (IOException e) {
            System.err.println("Problem occurred creating chart.");
}
        
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

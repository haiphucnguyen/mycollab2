/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui.chart;

import java.awt.Color;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public abstract class TimeSeriesChartWrapper<S extends SearchCriteria> extends VerticalLayout {
	private static final long serialVersionUID = 1L;
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
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                "",
                "",
                createDataset(criteria),
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
                );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);
        
        plot.getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MM/dd"));
        axis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 5));

        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);
        wrapper.setHeight(height + "px");
        wrapper.setWidth(width + "px");
        wrapper.setGraphHeight(height);
        wrapper.setGraphWidth(width);


        /////////////////////////////////////////////
        this.addComponent(wrapper);
    }

    protected abstract XYDataset createDataset(S criteria);
}

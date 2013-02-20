/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui.chart;

import java.awt.Color;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.vaadin.ui.VerticalLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author haiphucnguyen
 */
public abstract class PieChartWrapper<S extends SearchCriteria> extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private String title;
    private int height;
    private int width;

    public PieChartWrapper(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void setSearchCriteria(S criteria) {
        this.removeAllComponents();

        // create the chart...
        final JFreeChart chart = ChartFactory.createPieChart3D(
                title, // chart title
                createDataset(criteria), // data
                true, // include legend
                true, // tooltips?
                false // URLs?
                );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setBackgroundPaint(Color.white);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        plot.setLabelGenerator(new JFreeChartLabelCustom());
        // OPTIONAL CUSTOMISATION COMPLETED.

        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);
        wrapper.setHeight(height + "px");
        wrapper.setWidth(width + "px");
        wrapper.setGraphHeight(height);
        wrapper.setGraphWidth(width);
        /////////////////////////////////////////////
        this.addComponent(wrapper);
    }
    
    protected abstract DefaultPieDataset createDataset(S criteria);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui.chart;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class PieChartWrapper<S extends SearchCriteria> extends
		GenericChartWrapper<S> {
	private static final long serialVersionUID = 1L;

	protected DefaultPieDataset pieDataSet;

	public PieChartWrapper(String title, int width, int height) {
		super(title, width, height);
	}

	protected JFreeChart createChart() {
		// create the chart...
		pieDataSet = createDataset();
		final JFreeChart chart = ChartFactory.createPieChart3D(title, // chart
																		// title
				pieDataSet, // data
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

		List keys = pieDataSet.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			Comparable key = (Comparable) keys.get(i);
			plot.setSectionPaint(key, CHART_COLOR[i % CHART_COLOR.length]);
		}
		// OPTIONAL CUSTOMISATION COMPLETED.
		return chart;
	}

	protected abstract DefaultPieDataset createDataset();
}

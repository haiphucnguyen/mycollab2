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
import org.jfree.ui.RectangleInsets;
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

	public PieChartWrapper(final String title, final int width, final int height) {
		super(title, width, height);
	}

	@Override
	protected JFreeChart createChart() {
		// create the chart...
		pieDataSet = createDataset();
		final JFreeChart chart = ChartFactory.createPieChart3D("", // chart
																	// title
				pieDataSet, // data
				false, // include legend
				true, // tooltips?
				false // URLs?
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.getTitle().setPaint(new Color(0x5E5E5E));
		chart.setBorderVisible(false);
		// set the background color for the chart...
		final PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setOutlineVisible(false);
		plot.setInsets(RectangleInsets.ZERO_INSETS);
		plot.setStartAngle(290);
		plot.setBackgroundPaint(Color.white);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setNoDataMessage("No data to display");
		plot.setLabelGenerator(new JFreeChartLabelCustom());

		final List keys = pieDataSet.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			final Comparable key = (Comparable) keys.get(i);
			plot.setSectionPaint(key, Color.decode("0x"
					+ GenericChartWrapper.CHART_COLOR_STR[i
							% GenericChartWrapper.CHART_COLOR_STR.length]));
		}
		// OPTIONAL CUSTOMISATION COMPLETED.
		return chart;
	}

	protected abstract DefaultPieDataset createDataset();

	protected abstract void onClickedDescription(String key);
}

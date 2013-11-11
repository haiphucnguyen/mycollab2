package com.esofthead.mycollab.community.ui.chart;

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

/**
 * 
 * @author haiphucnguyen
 */
public abstract class TimeSeriesChartWrapper<S extends SearchCriteria> extends
		GenericChartWrapper<S> {
	private static final long serialVersionUID = 1L;

	protected XYDataset xyDataSet;

	public TimeSeriesChartWrapper(String title, int width, int height) {
		super(title, width, height);
	}

	protected JFreeChart createChart() {
		this.removeAllComponents();

		xyDataSet = createDataset();

		// create the chart...
		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, "", "",
				xyDataSet, false, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);

		chart.getTitle().setPaint(new Color(0x5E5E5E));

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
		for (int i = 0; i < xyDataSet.getSeriesCount(); i++) {
			renderer.setSeriesPaint(
					i,
					Color.decode("0x"
							+ CHART_COLOR_STR[i % CHART_COLOR_STR.length]));
		}
		plot.setRenderer(renderer);

		plot.getRangeAxis().setStandardTickUnits(
				NumberAxis.createIntegerTickUnits());

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("MM/dd"));
		axis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 5));

		return chart;
	}

	protected abstract XYDataset createDataset();
}

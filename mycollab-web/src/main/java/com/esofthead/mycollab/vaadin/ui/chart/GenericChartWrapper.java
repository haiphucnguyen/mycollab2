package com.esofthead.mycollab.vaadin.ui.chart;

import java.awt.Color;

import org.jfree.chart.JFreeChart;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

public abstract class GenericChartWrapper<S extends SearchCriteria> extends
		VerticalLayout {
	private static final long serialVersionUID = 1L;

	public static final Color[] CHART_COLOR = { Color.BLACK, Color.BLUE,
			Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
			Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
			Color.RED, Color.YELLOW };

	protected String title;
	protected int height;
	protected int width;
	protected S searchCriteria;

	public GenericChartWrapper(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	public void setSearchCriteria(S criteria) {
		this.searchCriteria = criteria;

		JFreeChart chart = createChart();
		JFreeChartWrapper chartWrapper = new JFreeChartWrapper(chart);
		chartWrapper.setHeight(height + "px");
		chartWrapper.setWidth(width + "px");
		chartWrapper.setGraphHeight(height);
		chartWrapper.setGraphWidth(width);

		this.removeAllComponents();
		this.addComponent(chartWrapper);
		this.addComponent(createLegendBox());
	}

	abstract protected JFreeChart createChart();

	protected abstract ComponentContainer createLegendBox();
}

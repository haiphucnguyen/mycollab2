package com.esofthead.mycollab.vaadin.ui.chart;

import org.jfree.chart.JFreeChart;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.ui.ColorConstants;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

public abstract class GenericChartWrapper<S extends SearchCriteria> extends
		VerticalLayout {
	private static final long serialVersionUID = 1L;

	public static final String[] CHART_COLOR_STR = { ColorConstants.BLUE,
			ColorConstants.GREEN, ColorConstants.ORANGE, ColorConstants.BLACK,
			ColorConstants.DARK_ORANGE, ColorConstants.LIGHT_BLUE,
			ColorConstants.GRAY, ColorConstants.BRIGHT_TURQUOISE,
			ColorConstants.LIGHT_GRAY, ColorConstants.CHERRY,
			ColorConstants.CONGO_PINK, ColorConstants.COFFFE,
			ColorConstants.COPPER, ColorConstants.RED,
			ColorConstants.LIGHTER_GREEN, ColorConstants.INDIAN_RED,
			ColorConstants.LAVENDER, ColorConstants.LEMON,
			ColorConstants.BROWN, ColorConstants.LIVER, ColorConstants.LION };

	protected String title;
	protected int height;
	protected int width;
	protected S searchCriteria;

	public GenericChartWrapper(final String title, final int width,
			final int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	abstract protected JFreeChart createChart();

	protected abstract ComponentContainer createLegendBox();

	public void setSearchCriteria(final S criteria) {
		this.searchCriteria = criteria;

		final JFreeChart chart = createChart();
		final JFreeChartWrapper chartWrapper = new JFreeChartWrapper(chart);

		removeAllComponents();
		final CssLayout borderWrap = new CssLayout();
		borderWrap.addComponent(chartWrapper);
		borderWrap.setStyleName("chart-wrapper");
		borderWrap.setHeight(height + "px");
		borderWrap.setWidth(width + "px");
		chartWrapper.setHeight(height + "px");
		chartWrapper.setWidth(width + "px");
		chartWrapper.setGraphHeight(height);
		chartWrapper.setGraphWidth(width);
		this.addComponent(borderWrap);
		// this.setComponentAlignment(chartWrapper, Alignment.MIDDLE_CENTER);
		final Component legendBox = createLegendBox();
		legendBox.setWidth(width + "px");
		this.addComponent(legendBox);
		this.setComponentAlignment(legendBox, Alignment.MIDDLE_CENTER);
	}
}

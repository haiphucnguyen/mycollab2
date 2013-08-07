/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.chart.GenericChartWrapper;
import com.esofthead.mycollab.vaadin.ui.chart.TimeSeriesChartWrapper;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * 
 * @author haiphucnguyen
 */
public class BugTrendReportChartWidget extends
		TimeSeriesChartWrapper<BugSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(BugTrendReportChartWidget.class);

	private static String patternDate = "yyyy-MM-dd";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			BugTrendReportChartWidget.patternDate);

	public BugTrendReportChartWidget() {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHART_TREND_TITLE),
				400, 300);
	}

	public BugTrendReportChartWidget(final int width, final int height) {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHART_TREND_TITLE),
				width, height);
	}

	@Override
	protected XYDataset createDataset() {
		final BugService bugService = AppContext
				.getSpringBean(BugService.class);
		final List<GroupItem> groupItems = bugService
				.getBugStatusTrendSummary(searchCriteria);

		final Map<String, TimeSeries> seriesMap = new HashMap<String, TimeSeries>();

		for (final GroupItem item : groupItems) {
			TimeSeries series = seriesMap.get(item.getGroupid());
			if (series == null) {
				series = new TimeSeries(item.getGroupid());
				seriesMap.put(item.getGroupid(), series);
			}
			try {
				series.add(
						new Day(BugTrendReportChartWidget.dateFormat.parse(item
								.getGroupname())), item.getValue());
			} catch (final ParseException ex) {
				BugTrendReportChartWidget.log.error(
						"Error while construct chart", ex);
			}
		}

		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		for (final TimeSeries series : seriesMap.values()) {
			dataset.addSeries(series);
		}
		return dataset;
	}

	@Override
	protected ComponentContainer createLegendBox() {
		final CustomLayout boxWrapper = CustomLayoutLoader.createLayout("legendBox");
		final CssLayout mainLayout = new CssLayout();
		// mainLayout.addStyleName("border-box");
		mainLayout.setSizeUndefined();
		for (int i = 0; i < xyDataSet.getSeriesCount(); i++) {
			final HorizontalLayout layout = new HorizontalLayout();
			layout.setMargin(false, false, false, true);
			layout.addStyleName("inline-block");
			final Comparable key = xyDataSet.getSeriesKey(i);
			final String color = "<div style = \" width:8px;height:8px;border-radius:5px;background: #"
					+ GenericChartWrapper.CHART_COLOR_STR[i
							% GenericChartWrapper.CHART_COLOR_STR.length]
					+ "\" />";
			final Label lblCircle = new Label(color);
			lblCircle.setContentMode(Label.CONTENT_XHTML);

			final Button btnLink = new Button(key + "",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
						}
					});
			btnLink.addStyleName("link");
			layout.addComponent(lblCircle);
			layout.setComponentAlignment(lblCircle, Alignment.MIDDLE_CENTER);
			layout.addComponent(btnLink);
			layout.setComponentAlignment(btnLink, Alignment.MIDDLE_CENTER);
			layout.setSizeUndefined();
			mainLayout.addComponent(layout);
		}
		boxWrapper.setWidth("100%");
		boxWrapper.addComponent(mainLayout, "legendBoxContent");
		return boxWrapper;
	}

}

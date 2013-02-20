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
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.chart.TimeSeriesChartWrapper;
import com.esofthead.mycollab.web.AppContext;

/**
 * 
 * @author haiphucnguyen
 */
public class BugTrendReportWidget extends
		TimeSeriesChartWrapper<BugSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(BugTrendReportWidget.class);

	private static String patternDate = "yyyy-MM-dd";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			patternDate);

	public BugTrendReportWidget() {
		super("Bugs Trend", 450, 300);
	}

	@Override
	protected XYDataset createDataset(BugSearchCriteria criteria) {
		BugService bugService = AppContext.getSpringBean(BugService.class);
		List<GroupItem> groupItems = bugService
				.getBugStatusTrendSummary(criteria);

		Map<String, TimeSeries> seriesMap = new HashMap<String, TimeSeries>();

		for (GroupItem item : groupItems) {
			TimeSeries series = seriesMap.get(item.getGroupid());
			if (series == null) {
				series = new TimeSeries(item.getGroupid());
				seriesMap.put(item.getGroupid(), series);
			}
			try {
				series.add(new Day(dateFormat.parse(item.getGroupname())),
						item.getValue());
			} catch (ParseException ex) {
				log.error("Error while construct chart", ex);
			}
		}

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		for (TimeSeries series : seriesMap.values()) {
			dataset.addSeries(series);
		}
		return dataset;
	}

}

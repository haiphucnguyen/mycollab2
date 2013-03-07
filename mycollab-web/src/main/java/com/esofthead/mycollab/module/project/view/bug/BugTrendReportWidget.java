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
import com.esofthead.mycollab.vaadin.ui.chart.GenericChartWrapper;
import com.esofthead.mycollab.vaadin.ui.chart.TimeSeriesChartWrapper;
import com.esofthead.mycollab.web.AppContext;
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
public class BugTrendReportWidget extends
		TimeSeriesChartWrapper<BugSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(BugTrendReportWidget.class);

	private static String patternDate = "yyyy-MM-dd";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			patternDate);

	public BugTrendReportWidget() {
		super("Bugs Trend", 500, 300);
	}

	@Override
	protected XYDataset createDataset() {
		BugService bugService = AppContext.getSpringBean(BugService.class);
		List<GroupItem> groupItems = bugService
				.getBugStatusTrendSummary(searchCriteria);

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

	@Override
	protected ComponentContainer createLegendBox() {
		CustomLayout boxWrapper = new CustomLayout("centerContent");
		CssLayout mainLayout = new CssLayout();
		mainLayout.addStyleName("border-box");
		mainLayout.setSizeUndefined();
		for (int i = 0; i < xyDataSet.getSeriesCount(); i++) {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setMargin(false, false, false, true);
			layout.addStyleName("inline-block");
			Comparable key = xyDataSet.getSeriesKey(i);
			String color = "<div style = \" width:8px;height:8px;border-radius:5px;background: #"
					+ GenericChartWrapper.CHART_COLOR_STR[i
							% GenericChartWrapper.CHART_COLOR_STR.length]
					+ "\" />";
			Label lblCircle = new Label(color);
			lblCircle.setContentMode(Label.CONTENT_XHTML);

			Button btnLink = new Button(key + "", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
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
		boxWrapper.addComponent(mainLayout, "centerContent");
		return boxWrapper;
	}

}

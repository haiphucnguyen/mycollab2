/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.ui.CssLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class BugResolutionSummaryChartWidget extends
		CssLayout {

	private static final long serialVersionUID = 1L;

	public BugResolutionSummaryChartWidget() {
		this.setSizeFull();

	}
	
	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		BugService bugService = ApplicationContextUtil
				.getSpringBean(BugService.class);

		DataSeries series = new DataSeries("Resolution");

		List<GroupItem> groupItems = bugService
				.getResolutionDefectsSummary(searchCriteria);
		String[] bugPriorities = ProjectDataTypeFactory.getBugResolutionList();
		for (String resolution : bugPriorities) {
			boolean isFound = false;
			for (GroupItem item : groupItems) {
				if (resolution.equals(item.getGroupid())) {
					series.add(new DataSeriesItem(resolution, item.getValue()));
					isFound = true;
					break;
				}
			}

			if (!isFound) {
				series.add(new DataSeriesItem(resolution, 0));
			}
		}

		Chart chart = new Chart(ChartType.PIE);

		Configuration conf = chart.getConfiguration();

		conf.setTitle("Resolution Distribution");
		conf.setCredits(new Credits(""));

		Tooltip tooltip = new Tooltip();
		tooltip.setValueDecimals(1);
		tooltip.setPointFormat("{series.name}: {point.percentage}%");
		conf.setTooltip(tooltip);

		PlotOptionsPie plotOptions = new PlotOptionsPie();
		plotOptions.setAllowPointSelect(true);
		plotOptions.setCursor(Cursor.POINTER);
		plotOptions.setShowInLegend(true);

		conf.setPlotOptions(plotOptions);

		conf.setSeries(series);
		chart.drawChart(conf);

		this.addComponent(chart);
	}

	// @Override
	// protected void onClickedDescription(String key) {
	// BugSearchCriteria searchCriteria = new BugSearchCriteria();
	// searchCriteria.setResolutions(new SetSearchField<String>(
	// SearchField.AND, new String[] { key }));
	// searchCriteria.setProjectId(new NumberSearchField(
	// CurrentProjectVariables.getProjectId()));
	// BugSearchParameter param = new BugSearchParameter(key + " Bug List",
	// searchCriteria);
	// EventBus.getInstance().fireEvent(
	// new BugEvent.GotoList(this, new BugScreenData.Search(param)));
	// }

}

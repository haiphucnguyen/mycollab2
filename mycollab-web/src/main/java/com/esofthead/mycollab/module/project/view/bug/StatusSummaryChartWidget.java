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

public class StatusSummaryChartWidget extends
		CssLayout {

	private static final long serialVersionUID = 1L;

	public StatusSummaryChartWidget() {
		this.setSizeFull();
	}
	
	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		BugService bugService = ApplicationContextUtil
				.getSpringBean(BugService.class);

		DataSeries series = new DataSeries("Status");

		List<GroupItem> groupItems = bugService
				.getStatusSummary(searchCriteria);
		String[] bugPriorities = ProjectDataTypeFactory.getBugStatusList();
		for (String status : bugPriorities) {
			boolean isFound = false;
			for (GroupItem item : groupItems) {
				if (status.equals(item.getGroupid())) {
					series.add(new DataSeriesItem(status, item.getValue()));
					isFound = true;
					break;
				}
			}

			if (!isFound) {
				series.add(new DataSeriesItem(status, 0));
			}
		}

		Chart chart = new Chart(ChartType.PIE);

		Configuration conf = chart.getConfiguration();

		conf.setTitle("Status Distribution");
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
	// searchCriteria.setStatuses(new SetSearchField<String>(SearchField.AND,
	// new String[] { key }));
	// searchCriteria.setProjectId(new NumberSearchField(
	// CurrentProjectVariables.getProjectId()));
	// BugSearchParameter param = new BugSearchParameter(key + " Bug List",
	// searchCriteria);
	// EventBus.getInstance().fireEvent(
	// new BugEvent.GotoList(this, new BugScreenData.Search(param)));
	// }
}

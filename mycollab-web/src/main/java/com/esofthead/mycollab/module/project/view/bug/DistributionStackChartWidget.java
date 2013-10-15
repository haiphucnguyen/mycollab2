package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.ui.CssLayout;

public class DistributionStackChartWidget extends CssLayout {
	private static final long serialVersionUID = 1L;

	public DistributionStackChartWidget() {
		this.setSizeFull();
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		BugService bugService = ApplicationContextUtil
				.getSpringBean(BugService.class);

		// The data
		// Source: V. Maijala, H. Norberg, J. Kumpula, M. Nieminen
		// Calf production and mortality in the Finnish
		// reindeer herding area. 2002.
		Chart chart = new Chart(ChartType.COLUMN);

		Configuration conf = chart.getConfiguration();
		conf.setTitle("Bug Distribution");
		conf.setCredits(new Credits(""));
		
		PlotOptionsColumn options = new PlotOptionsColumn();
		options.setStacking(Stacking.NORMAL);

		String predators[] = { "Bear", "Wolf", "Wolverine", "Lynx" };
		int kills[][] = { // Location:
		{ 8, 0, 7, 0 }, // Muddusjarvi
				{ 30, 1, 30, 2 }, // Ivalo
				{ 37, 0, 22, 2 }, // Oraniemi
				{ 13, 23, 4, 1 }, // Salla
				{ 3, 10, 9, 0 }, // Alakitka
		};

		// Create a data series for each numeric column in the table
		for (int predator = 0; predator < 4; predator++) {
			ListSeries series = new ListSeries();
			series.setName(predators[predator]);

			// The rows of the table
			for (int location = 0; location < kills.length; location++) {
				series.addData(kills[location][predator]);
			}
			series.setPlotOptions(options);
			conf.addSeries(series);
		}

		chart.drawChart(conf);

		this.addComponent(chart);
	}
}

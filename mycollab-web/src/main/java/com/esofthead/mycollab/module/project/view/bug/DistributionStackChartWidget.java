/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.BugStatusGroupItem;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.CssLayout;

public class DistributionStackChartWidget extends CssLayout {
	private static final long serialVersionUID = 1L;

	public DistributionStackChartWidget() {
		this.setSizeFull();
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		BugService bugService = ApplicationContextUtil
				.getSpringBean(BugService.class);
		List<BugStatusGroupItem> groupItems = bugService
				.getBugStatusGroupItemBaseComponent(searchCriteria);
		Chart chart = new Chart(ChartType.BAR);

		Configuration conf = chart.getConfiguration();
		conf.setTitle("Bug Distribution");
		conf.setCredits(new Credits(""));

		XAxis x = new XAxis();

		String[] categories = new String[groupItems.size()];
		for (int i = 0; i < categories.length; i++) {
			categories[i] = groupItems.get(i).getGroupname();
		}
		x.setCategories(categories);
		conf.addxAxis(x);

		YAxis y = new YAxis();
		y.setMin(0);
		y.setTitle("");
		conf.addyAxis(y);

		PlotOptionsSeries options = new PlotOptionsSeries();
		options.setStacking(Stacking.NORMAL);
		conf.setPlotOptions(options);

		ListSeries openSeries = new ListSeries("Open");
		openSeries.setName(BugStatusConstants.OPEN);
		for (BugStatusGroupItem item : groupItems) {
			openSeries.addData(item.getNumOpen());
		}
		conf.addSeries(openSeries);

		ListSeries inProgressSeries = new ListSeries("In Progress");
		inProgressSeries.setName(BugStatusConstants.INPROGRESS);
		for (BugStatusGroupItem item : groupItems) {
			inProgressSeries.addData(item.getNumInProgress());
		}
		conf.addSeries(inProgressSeries);

		ListSeries reOpenSeries = new ListSeries("ReOpenned");
		reOpenSeries.setName(BugStatusConstants.REOPENNED);
		for (BugStatusGroupItem item : groupItems) {
			reOpenSeries.addData(item.getNumReOpenned());
		}
		conf.addSeries(reOpenSeries);

		ListSeries reSolvedSeries = new ListSeries("Resolved");
		reSolvedSeries.setName(BugStatusConstants.RESOLVED);
		for (BugStatusGroupItem item : groupItems) {
			reSolvedSeries.addData(item.getNumResolved());
		}
		conf.addSeries(reSolvedSeries);

		ListSeries verifySeries = new ListSeries("Verified");
		verifySeries.setName(BugStatusConstants.VERIFIED);
		for (BugStatusGroupItem item : groupItems) {
			verifySeries.addData(item.getNumVerified());
		}
		conf.addSeries(verifySeries);

		chart.drawChart(conf);
		chart.setHeight((groupItems.size() * 40 + 50) + "px");

		this.addComponent(chart);
	}
}

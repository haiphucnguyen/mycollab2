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
package com.esofthead.mycollab.premium.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.esofthead.mycollab.module.project.view.bug.IStatusSummaryChartWidget;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

@ViewComponent
public class StatusSummaryChartWidget extends CssLayout implements
		IStatusSummaryChartWidget {

	private static final long serialVersionUID = 1L;

	public StatusSummaryChartWidget() {
		this.setSizeFull();
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		this.removeAllComponents();
		BugService bugService = ApplicationContextUtil
				.getSpringBean(BugService.class);

		DataSeries series = new DataSeries("Status");

		List<GroupItem> groupItems = bugService
				.getStatusSummary(searchCriteria);
		BugStatus[] bugStatues = OptionI18nEnum.bug_statuses;
		for (BugStatus status : bugStatues) {
			boolean isFound = false;
			for (GroupItem item : groupItems) {
				if (status.name().equals(item.getGroupid())) {
					series.add(new DataSeriesItem(
							AppContext.getMessage(status), item.getValue()));
					isFound = true;
					break;
				}
			}

			if (!isFound) {
				series.add(new DataSeriesItem(AppContext.getMessage(status), 0));
			}
		}

		Chart chart = new Chart(ChartType.PIE);

		Configuration conf = chart.getConfiguration();

		conf.setTitle("");
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

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(ViewListener listener) {

	}
}

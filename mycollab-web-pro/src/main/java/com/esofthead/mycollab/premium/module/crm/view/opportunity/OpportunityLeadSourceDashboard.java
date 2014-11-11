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

package com.esofthead.mycollab.premium.module.crm.view.opportunity;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.opportunity.IOpportunityLeadSourceDashboard;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
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

/**
 * 
 * @author MyCollab Ltd.
 */
@SuppressWarnings("serial")
@ViewComponent
public class OpportunityLeadSourceDashboard extends CssLayout implements
		IOpportunityLeadSourceDashboard {

	public OpportunityLeadSourceDashboard() {
		this.setSizeFull();
	}

	public void setSearchCriteria(OpportunitySearchCriteria searchCriteria) {
		this.removeAllComponents();

		final OpportunityService opportunityService = ApplicationContextUtil
				.getSpringBean(OpportunityService.class);

		List<GroupItem> groupItems = opportunityService
				.getLeadSourcesSummary(searchCriteria);

		DataSeries series = new DataSeries("Lead Source");

		final String[] leadSources = CrmDataTypeFactory.getLeadSourceList();
		for (final String status : leadSources) {
			boolean isFound = false;
			for (final GroupItem item : groupItems) {
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

		conf.setTitle("Sales Stage");
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

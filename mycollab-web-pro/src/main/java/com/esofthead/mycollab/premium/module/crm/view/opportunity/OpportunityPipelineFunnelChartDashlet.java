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
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.opportunity.IOpportunityPipelineFunnelChartDashlet;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.web.ui.Depot;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsFunnel;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

@ViewComponent
public class OpportunityPipelineFunnelChartDashlet extends Depot implements
		IOpportunityPipelineFunnelChartDashlet {
	private static final long serialVersionUID = 1L;

	public OpportunityPipelineFunnelChartDashlet() {
		super("My Pipeline", new CssLayout());
		this.setContentBorder(true);
	}

	public void display() {
		bodyContent.removeAllComponents();

		OpportunityService opportunityService = ApplicationContextUtil
				.getSpringBean(OpportunityService.class);

		OpportunitySearchCriteria searchCriteria = new OpportunitySearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(AppContext
				.getAccountId()));
		searchCriteria.setAssignUsers(new SetSearchField<String>(
				new String[] { AppContext.getUsername() }));

		List<GroupItem> groupItems = opportunityService
				.getPipeline(searchCriteria);

		DataSeries dataSeries = new DataSeries("My Pipeline");
		final String[] salesStages = CrmDataTypeFactory
				.getOpportunitySalesStageList();

		for (final String status : salesStages) {
			boolean isFound = false;
			for (final GroupItem item : groupItems) {
				if (status.equals(item.getGroupid())) {
					dataSeries.add(new DataSeriesItem(status, item.getValue()));
					isFound = true;
					break;
				}
			}

			if (!isFound) {
				dataSeries.add(new DataSeriesItem(status, 0));
			}
		}

		Chart chart = new Chart();

		Configuration conf = chart.getConfiguration();
		conf.getChart().setSpacingRight(150);
		Long totalPipelineCount = 0L;
		for (final GroupItem item : groupItems) {
			totalPipelineCount += item.getValue();
		}
		conf.setTitle("Pipeline total is $" + totalPipelineCount);
		conf.getLegend().setEnabled(true);
		conf.setCredits(new Credits(""));

		PlotOptionsFunnel options = new PlotOptionsFunnel();
		options.setNeckWidthPercentage(10);
		options.setNeckHeightPercentage(10);

		Labels dataLabels = new Labels();
		dataLabels.setFormat("{point.name} (${point.y:,.0f})");
		dataLabels.setSoftConnector(false);
		dataLabels.setColor(new SolidColor("black"));
		options.setDataLabels(dataLabels);

		dataSeries.setPlotOptions(options);
		conf.addSeries(dataSeries);
		chart.setHeight("500px");
		bodyContent.addComponent(chart);
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(ViewListener listener) {

	}
}

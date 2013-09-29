package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmDataTypeFactory;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsFunnel;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.VerticalLayout;

public class OpportunityChartDashlet extends Depot {
	private static final long serialVersionUID = 1L;

	public OpportunityChartDashlet() {
		super("My Pipeline", new VerticalLayout());
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

		Long totalPipelineCount = 0L;
		for (final GroupItem item : groupItems) {
			totalPipelineCount += item.getValue();
		}
		conf.setTitle("Pipeline total is $" + totalPipelineCount);
		conf.getLegend().setEnabled(true);
		conf.setCredits(new Credits(""));
		conf.setExporting(true);

		PlotOptionsFunnel options = new PlotOptionsFunnel();
		options.setNeckWidthPercentage(10);
		options.setNeckHeightPercentage(10);
		options.setLineWidth(10);

		Labels dataLabels = new Labels();
		dataLabels.setFormat("{point.name} ({point.y:,.0f})");
		dataLabels.setSoftConnector(false);
		dataLabels.setColor(new SolidColor("black"));
		options.setDataLabels(dataLabels);

		dataSeries.setPlotOptions(options);
		conf.addSeries(dataSeries);
		chart.setHeight("500px");
		bodyContent.addComponent(chart);
	}

}

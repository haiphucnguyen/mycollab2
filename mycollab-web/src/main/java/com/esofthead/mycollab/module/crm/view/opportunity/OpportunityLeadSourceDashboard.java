/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmDataTypeFactory;
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
@SuppressWarnings("serial")
public class OpportunityLeadSourceDashboard extends CssLayout {

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

	// @Override
	// protected void onClickedDescription(final String key) {
	// final OpportunitySearchCriteria searchCriteria = new
	// OpportunitySearchCriteria();
	// searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
	// AppContext.getAccountId()));
	// searchCriteria.setLeadSources(new SetSearchField<String>(
	// SearchField.AND, new String[] { key }));
	// EventBus.getInstance().fireEvent(
	// new OpportunityEvent.GotoList(this, searchCriteria));
	// }
}

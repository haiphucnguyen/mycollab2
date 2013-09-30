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
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.ChartClickListener;
import com.vaadin.addon.charts.ChartSelectionEvent;
import com.vaadin.addon.charts.ChartSelectionListener;
import com.vaadin.addon.charts.LegendItemClickEvent;
import com.vaadin.addon.charts.LegendItemClickListener;
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
public class OpportunitySalesStageDashboard extends CssLayout {
	private static final long serialVersionUID = 1L;

	public OpportunitySalesStageDashboard() {
		this.setSizeFull();
	}

	public void setSearchCriteria(OpportunitySearchCriteria searchCriteria) {
		this.removeAllComponents();

		final OpportunityService opportunityService = ApplicationContextUtil
				.getSpringBean(OpportunityService.class);

		final List<GroupItem> groupItems = opportunityService
				.getSalesStageSummary(searchCriteria);

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

		DataSeries series = new DataSeries("Sales Stage");

		final String[] salesStages = CrmDataTypeFactory
				.getOpportunitySalesStageList();
		for (final String status : salesStages) {
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

		chart.addChartSelectionListener(new ChartSelectionListener() {
			public void onSelection(ChartSelectionEvent e) {
				System.out.println(e.getComponent() + " " + e.getValueStart()
						+ " " + e.getValueEnd() + " " + e.getSelectionEnd());
			}
		});
		chart.addLegendItemClickListener(new LegendItemClickListener() {
			public void onClick(LegendItemClickEvent e) {
				System.out.println(e.getSource() + " " + e.getSeries());
			}
		});
		chart.addChartClickListener(new ChartClickListener() {
			public void onClick(ChartClickEvent e) {
				System.out.println(e.getxAxisValue() + " " + e.getyAxisValue());
			}
		});

		conf.setSeries(series);
		chart.drawChart(conf);

		this.addComponent(chart);
	}

	// protected void onClickedDescription(final String key) {
	// final OpportunitySearchCriteria searchCriteria = new
	// OpportunitySearchCriteria();
	// searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
	// AppContext.getAccountId()));
	// searchCriteria.setSalesStages(new SetSearchField<String>(
	// SearchField.AND, new String[] { key }));
	// EventBus.getInstance().fireEvent(
	// new OpportunityEvent.GotoList(this, searchCriteria));
	// }
}

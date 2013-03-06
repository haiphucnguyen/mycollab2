/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.chart.PieChartDescriptionBox;
import com.esofthead.mycollab.vaadin.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class OpportunitySalesStageDashboard extends
		PieChartWrapper<OpportunitySearchCriteria> {
	private static final long serialVersionUID = 1L;

	public OpportunitySalesStageDashboard() {
		this(390, 278);
	}

	public OpportunitySalesStageDashboard(int width, int height) {
		super("Deals By Stages", width, height);
	}

	@Override
	protected DefaultPieDataset createDataset() {
		// create the dataset...
		final DefaultPieDataset dataset = new DefaultPieDataset();

		OpportunityService opportunityService = AppContext
				.getSpringBean(OpportunityService.class);

		List<GroupItem> groupItems = opportunityService
				.getSalesStageSummary(searchCriteria);

		String[] salesStages = CrmDataTypeFactory
				.getOpportunitySalesStageList();
		for (String status : salesStages) {
			boolean isFound = false;
			for (GroupItem item : groupItems) {
				if (status.equals(item.getGroupid())) {
					dataset.setValue(status, item.getValue());
					isFound = true;
					break;
				}
			}

			if (!isFound) {
				dataset.setValue(status, 0);
			}
		}

		return dataset;
	}

	@Override
	protected ComponentContainer createLegendBox() {
		return PieChartDescriptionBox.createLegendBox(this, pieDataSet);
	}

	@Override
	protected void onClickedDescription(String key) {
		OpportunitySearchCriteria searchCriteria = new OpportunitySearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		searchCriteria.setSalesStages(new SetSearchField<String>(
				SearchField.AND, new String[] { key }));
		EventBus.getInstance().fireEvent(
				new OpportunityEvent.GotoList(this, searchCriteria));
	}
}

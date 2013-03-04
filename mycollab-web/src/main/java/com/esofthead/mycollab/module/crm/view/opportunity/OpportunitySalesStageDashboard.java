/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
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
		super("Deals By Stages", 530, 350);
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
		return PieChartDescriptionBox.createLegendBox(pieDataSet);
	}
}

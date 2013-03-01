/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.vaadin.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 
 * @author haiphucnguyen
 */
public class OpportunitySalesStageDashboard extends
		PieChartWrapper<OpportunitySearchCriteria> {
	private static final long serialVersionUID = 1L;

	public OpportunitySalesStageDashboard() {
		super("Deals By Stages", 530, 400);
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
		HorizontalLayout layout = new HorizontalLayout();
		List keys = pieDataSet.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			Comparable key = (Comparable) keys.get(i);
			Label lbl = new Label(key + "("
					+ String.valueOf(pieDataSet.getValue(key)) + ")");
			layout.addComponent(lbl);
		}
		return layout;
	}
}

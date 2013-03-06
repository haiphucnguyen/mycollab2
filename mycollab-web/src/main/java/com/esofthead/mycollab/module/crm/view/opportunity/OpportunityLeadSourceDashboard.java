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
@SuppressWarnings("serial")
public class OpportunityLeadSourceDashboard extends
		PieChartWrapper<OpportunitySearchCriteria> {

	public OpportunityLeadSourceDashboard() {
		this(390, 278);
	}
	
	public OpportunityLeadSourceDashboard(int width, int height) {
		super("Deals By Sources", width, height); //530, 350
	}

	@Override
	protected DefaultPieDataset createDataset() {
		final DefaultPieDataset dataset = new DefaultPieDataset();

		OpportunityService opportunityService = AppContext
				.getSpringBean(OpportunityService.class);

		List<GroupItem> groupItems = opportunityService
				.getLeadSourcesSummary(searchCriteria);

		String[] leadSources = CrmDataTypeFactory.getLeadSourceList();
		for (String source : leadSources) {
			boolean isFound = false;
			for (GroupItem item : groupItems) {
				if (source.equals(item.getGroupid())) {
					dataset.setValue(source, item.getValue());
					isFound = true;
					break;
				}
			}

			if (!isFound) {
				dataset.setValue(source, 0);
			}
		}

		return dataset;
	}

	@Override
	protected ComponentContainer createLegendBox() {
		return PieChartDescriptionBox.createLegendBox(pieDataSet);
	}
}

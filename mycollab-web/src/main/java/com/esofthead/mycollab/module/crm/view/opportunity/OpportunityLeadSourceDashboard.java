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
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmDataTypeFactory;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EventBus;
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

	private List<GroupItem> groupItems;
	
	public OpportunityLeadSourceDashboard() {
		this(400, 265);
	}

	public OpportunityLeadSourceDashboard(final int width, final int height) {
		super("Deals By Sources", width, height); // 530, 350
	}

	@Override
	protected DefaultPieDataset createDataset() {
		final DefaultPieDataset dataset = new DefaultPieDataset();

		final OpportunityService opportunityService = ApplicationContextUtil
				.getSpringBean(OpportunityService.class);

		groupItems = opportunityService
				.getLeadSourcesSummary(searchCriteria);

		final String[] leadSources = CrmDataTypeFactory.getLeadSourceList();
		for (final String source : leadSources) {
			boolean isFound = false;
			for (final GroupItem item : groupItems) {
				if (source.equals(item.getGroupid())) {
					if(item.getValue() != 0) dataset.setValue(source, item.getValue());
					else dataset.setValue(source, item.getCountNum());
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
		final DefaultPieDataset dataset = new DefaultPieDataset();
		final String[] leadSources = CrmDataTypeFactory.getLeadSourceList();
		for (final String source : leadSources) {
			boolean isFound = false;
			for (final GroupItem item : groupItems) {
				if (source.equals(item.getGroupid())) {
					dataset.setValue(source, item.getCountNum());
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				dataset.setValue(source, 0);
			}
		}
		return PieChartDescriptionBox.createLegendBox(this, dataset);
	}

	@Override
	protected void onClickedDescription(final String key) {
		final OpportunitySearchCriteria searchCriteria = new OpportunitySearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		searchCriteria.setLeadSources(new SetSearchField<String>(
				SearchField.AND, new String[] { key }));
		EventBus.getInstance().fireEvent(
				new OpportunityEvent.GotoList(this, searchCriteria));
	}
}

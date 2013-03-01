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
import com.esofthead.mycollab.vaadin.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class OpportunityLeadSourceDashboard extends
		PieChartWrapper<OpportunitySearchCriteria> {

	public OpportunityLeadSourceDashboard() {
		super("Deals By Sources", 530, 350);
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

	@SuppressWarnings("rawtypes")
	@Override
	protected ComponentContainer createLegendBox() {
		CssLayout mainLayout = new CssLayout();
		mainLayout.addStyleName("border-box");
		mainLayout.setWidth("100%");
		List keys = pieDataSet.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setMargin(false, false, false, true);
			layout.addStyleName("inline-block");
			Comparable key = (Comparable) keys.get(i);
			String color = "<div style = \" width:8px;height:8px;border-radius:5px;background: #" + CHART_COLOR_STR[i % CHART_COLOR_STR.length] + "\" />";
			Label lblCircle = new Label(color);
			lblCircle.setContentMode(Label.CONTENT_XHTML);
			
			Button btnLink = new Button(key + "("
					+ String.valueOf(pieDataSet.getValue(key)) + ")",
					new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
				}
			});
			btnLink.addStyleName("link");
			layout.addComponent(lblCircle);
			layout.setComponentAlignment(lblCircle, Alignment.MIDDLE_CENTER);
			layout.addComponent(btnLink);
			layout.setComponentAlignment(btnLink, Alignment.MIDDLE_CENTER);
			layout.setSizeUndefined();
			mainLayout.addComponent(layout);
		}
		return mainLayout;
	}
}

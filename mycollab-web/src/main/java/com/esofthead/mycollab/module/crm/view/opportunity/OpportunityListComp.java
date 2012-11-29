package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class OpportunityListComp extends Depot {
	private static final long serialVersionUID = 1L;

	private PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity> tableItem;

	public OpportunityListComp() {
		super("Opportunities", new VerticalLayout());

		this.setWidth("900px");

		initUI();
	}

	private void initUI() {
		VerticalLayout contentContainer = (VerticalLayout) content;
		tableItem = new PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity>(
				AppContext.getSpringBean(OpportunityService.class),
				SimpleOpportunity.class, new String[] { "opportunityname",
						"salesstage", "amount", "expectedcloseddate",
						"assignUserFullName" }, new String[] { "Name",
						"Sales Stage", "Amount", "Close", "User" });
		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("opportunityname", 1.0f);
		tableItem.setColumnWidth("salesstage", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("amount", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("expectedcloseddate",
				UIConstants.TABLE_DATE_WIDTH);
		tableItem.setColumnWidth("assignUserFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);
		
		contentContainer.addComponent(tableItem);
		contentContainer.addComponent(tableItem.createControls());
	}
	
	public void setSearchCriteria(OpportunitySearchCriteria searchCriteria) {
		tableItem.setSearchCriteria(searchCriteria);
	}

}

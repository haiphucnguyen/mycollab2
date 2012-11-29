package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

public class LeadListComp extends Depot {
	private static final long serialVersionUID = 1L;

	private PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead> tableItem;

	public LeadListComp() {
		super("Leads", new VerticalLayout());

		this.setWidth("900px");

		initUI();
	}

	private void initUI() {
		VerticalLayout contentContainer = (VerticalLayout) content;

		tableItem = new PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead>(
				AppContext.getSpringBean(LeadService.class), SimpleLead.class,
				new String[] { "leadName", "status", "officephone", "email",
						"assignuser" }, new String[] { "Name", "Status",
						"Office Phone", "Email", "Assign User" });

		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("leadName", 1.0f);
		tableItem.setColumnWidth("status", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem
				.setColumnWidth("officephone", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		tableItem.setColumnWidth("assignuser", UIConstants.TABLE_X_LABEL_WIDTH);
		contentContainer.addComponent(tableItem);
		contentContainer.addComponent(tableItem.createControls());
	}

	public void setSearchCriteria(LeadSearchCriteria searchCriteria) {
		tableItem.setSearchCriteria(searchCriteria);
	}
}

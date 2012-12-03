package com.esofthead.mycollab.module.crm.view.lead;

import java.util.HashSet;
import java.util.Set;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.module.crm.view.RelatedListHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class LeadListComp extends Depot  implements IRelatedListHandlers{
	private static final long serialVersionUID = 1L;

	private PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead> tableItem;
	
	private Set<RelatedListHandler> handlers;

	public LeadListComp() {
		super("Leads", new VerticalLayout());

		this.setWidth("900px");

		initUI();
	}

	private void initUI() {
		VerticalLayout contentContainer = (VerticalLayout) content;
		contentContainer.setSpacing(true);

		Button createBtn = new Button("Create", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				fireRelatedListHandler();
			}
		});

		contentContainer.addComponent(createBtn);

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
	
	private void fireRelatedListHandler() {
		if (handlers != null) {
			for (RelatedListHandler handler : handlers) {
				handler.createNewRelatedItem();
			}
		}
	}

	@Override
	public void addRelatedListHandler(RelatedListHandler handler) {
		if (handlers == null) {
			handlers = new HashSet<RelatedListHandler>();
		}

		handlers.add(handler);
	}
}

package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.HashSet;
import java.util.Set;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.module.crm.view.RelatedListHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class OpportunityListComp extends Depot implements IRelatedListHandlers {
	private static final long serialVersionUID = 1L;

	private PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity> tableItem;

	private Set<RelatedListHandler> handlers;

	public OpportunityListComp() {
		super("Opportunities", new VerticalLayout());

		this.setWidth("900px");

		initUI();
	}

	private void initUI() {
		VerticalLayout contentContainer = (VerticalLayout) content;
		contentContainer.setSpacing(true);

		Button createBtn = new Button("New Opportunity",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						fireRelatedListHandler();
					}
				});

		createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createBtn.setStyleName(BaseTheme.BUTTON_LINK);
		contentContainer.addComponent(createBtn);

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

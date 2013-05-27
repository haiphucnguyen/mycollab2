package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.OpportunityI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class AccountOpportunityListComp extends
		RelatedListComp<SimpleOpportunity, OpportunitySearchCriteria> {

	private static final long serialVersionUID = 1L;

	public AccountOpportunityListComp() {
		super("Opportunities");

		initUI();
	}

	private void initUI() {
		final VerticalLayout contentContainer = (VerticalLayout) bodyContent;

		final Button newBtn = new Button("New Opportunity",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						fireNewRelatedItem("");
					}
				});
		newBtn.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
		newBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		newBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));

		addHeaderElement(newBtn);

		tableItem = new OpportunityTableDisplay(
				new String[] { "opportunityname", "salesstage", "amount",
						"expectedcloseddate" },
				new String[] {
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
						LocalizationHelper
								.getMessage(OpportunityI18nEnum.TABLE_SALE_STAGE_HEADER),
						LocalizationHelper
								.getMessage(OpportunityI18nEnum.TABLE_AMOUNT_HEADER),
						LocalizationHelper
								.getMessage(OpportunityI18nEnum.TABLE_CLOSE_DATE_HEADER) });

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleOpportunity opportunity = (SimpleOpportunity) event
								.getData();
						if ("opportunityname".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoRead(this,
											opportunity.getId()));
						}
					}
				});

		contentContainer.addComponent(tableItem);
	}

	@Override
	public void refresh() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

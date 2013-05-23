/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class LeadListDashlet extends Depot {

	private LeadTableDisplay tableItem;

	public LeadListDashlet() {
		super("My Leads", new VerticalLayout());

		if (ScreenSize.hasSupport1024Pixels()) {
			tableItem = new LeadTableDisplay(
					new String[] { "leadName", "email" },
					new String[] {
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER) });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			tableItem = new LeadTableDisplay(
					new String[] { "leadName", "officephone", "email" },
					new String[] {
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER) });
		}
		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleLead lead = (SimpleLead) event.getData();
						if ("leadName".equals(event.getFieldName())) {
							EventBus.getInstance()
									.fireEvent(
											new LeadEvent.GotoRead(
													LeadListDashlet.this, lead
															.getId()));
						}
					}
				});
		bodyContent.addComponent(tableItem);
	}

	public void display() {
		final LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setAssignUsers(new SetSearchField<String>(
				new String[] { AppContext.getUsername() }));
		tableItem.setSearchCriteria(criteria);
	}
}

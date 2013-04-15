/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.lead.LeadSimpleSearchPanel;
import com.esofthead.mycollab.module.crm.view.lead.LeadTableDisplay;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Button;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class AccountLeadSelectionWindow extends
		RelatedItemSelectionWindow<SimpleLead, LeadSearchCriteria> {

	public AccountLeadSelectionWindow(AccountLeadListComp associateLeadList) {
		super("Select Leads", associateLeadList);

		this.setWidth("900px");
	}

	@Override
	protected void initUI() {
		tableItem = new LeadTableDisplay(
				new String[] { "selected", "title", "leadName", "email",
						"officephone" },
				new String[] {
						"",
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_TITLE_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER) });

		Button selectBtn = new Button("Select", new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				close();
			}
		});
		selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		LeadSimpleSearchPanel leadSimpleSearchPanel = new LeadSimpleSearchPanel();
		leadSimpleSearchPanel
				.addSearchHandler(new SearchHandler<LeadSearchCriteria>() {

					@Override
					public void onSearch(LeadSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}

				});

		this.bodyContent.addComponent(leadSimpleSearchPanel);
		this.bodyContent.addComponent(selectBtn);
		this.bodyContent.addComponent(tableItem);
	}

}

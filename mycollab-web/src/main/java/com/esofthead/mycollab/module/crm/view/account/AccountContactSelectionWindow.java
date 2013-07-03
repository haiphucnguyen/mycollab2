/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.contact.ContactSimpleSearchPanel;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableDisplay;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class AccountContactSelectionWindow extends
		RelatedItemSelectionWindow<SimpleContact, ContactSearchCriteria> {

	public AccountContactSelectionWindow(
			final AccountContactListComp associateContactList) {
		super("Select Contacts", associateContactList);

		this.setWidth("900px");
	}

	@Override
	protected void initUI() {
		this.tableItem = new ContactTableDisplay(
				new String[] { "selected", "contactName", "title",
						"accountName", "officephone" },
				new String[] {
						"",
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_TITLE_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_ACCOUNT_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER) });
		this.tableItem.addStyleName(UIConstants.LIMITED_HEIGHT_TABLE);

		final Button selectBtn = new Button("Select",
				new Button.ClickListener() {

					@Override
					public void buttonClick(final ClickEvent event) {
						AccountContactSelectionWindow.this.close();
					}
				});
		selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		final ContactSimpleSearchPanel contactSimpleSearchPanel = new ContactSimpleSearchPanel();
		contactSimpleSearchPanel
				.addSearchHandler(new SearchHandler<ContactSearchCriteria>() {

					@Override
					public void onSearch(final ContactSearchCriteria criteria) {
						AccountContactSelectionWindow.this.tableItem
								.setSearchCriteria(criteria);
					}

				});

		this.bodyContent.addComponent(contactSimpleSearchPanel);
		this.bodyContent.addComponent(selectBtn);
		this.bodyContent.addComponent(this.tableItem);
	}
}

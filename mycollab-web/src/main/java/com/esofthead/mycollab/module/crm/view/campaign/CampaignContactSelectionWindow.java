/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

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

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class CampaignContactSelectionWindow extends
		RelatedItemSelectionWindow<SimpleContact, ContactSearchCriteria> {

	public CampaignContactSelectionWindow(
			CampaignContactListComp associateContactList) {
		super("Select Contacts", associateContactList);

		this.setWidth("900px");
	}

	@Override
	protected void initUI() {
		tableItem = new ContactTableDisplay(
				new String[] { "selected", "contactName", "email",
						"officephone", "accountName" },
				new String[] {
						"",
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_ACCOUNT_NAME_HEADER) });

		Button selectBtn = new Button("Select", new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				close();
			}
		});
		selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		ContactSimpleSearchPanel contactSimpleSearchPanel = new ContactSimpleSearchPanel();
		contactSimpleSearchPanel
				.addSearchHandler(new SearchHandler<ContactSearchCriteria>() {

					@Override
					public void onSearch(ContactSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}

				});

		this.bodyContent.addComponent(contactSimpleSearchPanel);
		this.bodyContent.addComponent(selectBtn);
		this.bodyContent.addComponent(tableItem);
	}

}

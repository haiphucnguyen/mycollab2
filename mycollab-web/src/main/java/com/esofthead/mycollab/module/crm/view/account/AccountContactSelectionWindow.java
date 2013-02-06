/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.contact.ContactSimpleSearchPanel;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableDisplay;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class AccountContactSelectionWindow extends RelatedItemSelectionWindow<SimpleContact, ContactSearchCriteria> {

    public AccountContactSelectionWindow(AccountContactListComp associateContactList) {
        super("Select Contacts", associateContactList);
        
        this.setWidth("900px");
    }

    @Override
    protected void initUI() {
        tableItem = new ContactTableDisplay(new String[]{"selected",
                    "contactName", "title", "accountName", "officephone"}, new String[]{"", "Name", "Title",
                    "Account Name", "Office Phone"});
        
        Button selectBtn = new Button("Select", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                close();
            }
        });
        selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        
        ContactSimpleSearchPanel contactSimpleSearchPanel = new ContactSimpleSearchPanel();
        contactSimpleSearchPanel.addSearchHandler(new SearchHandler<ContactSearchCriteria>(){

			@Override
			public void onSearch(ContactSearchCriteria criteria) {
				tableItem.setSearchCriteria(criteria);
			}
        	
        });
        
        this.bodyContent.addComponent(selectBtn);
        this.bodyContent.addComponent(contactSimpleSearchPanel);
        this.bodyContent.addComponent(tableItem);
    }
}

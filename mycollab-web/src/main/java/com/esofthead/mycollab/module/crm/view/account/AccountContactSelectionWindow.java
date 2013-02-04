/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableDisplay;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

/**
 *
 * @author haiphucnguyen
 */
public class AccountContactSelectionWindow extends RelatedItemSelectionWindow<ContactSearchCriteria> {

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
                
            }
        });
        selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        
        this.bodyContent.addComponent(selectBtn);
        this.bodyContent.addComponent(tableItem);
    }
}

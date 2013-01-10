/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableDisplay;

/**
 *
 * @author haiphucnguyen
 */
public class AccountContactSelectionWindow extends RelatedItemSelectionWindow<ContactSearchCriteria> {

    public AccountContactSelectionWindow(AccountContactListComp associateContactList) {
        super("Contacts", associateContactList);
    }

    @Override
    protected void initUI() {
        tableItem = new ContactTableDisplay(new String[]{"selected",
                    "contactName", "title", "accountName", "officephone"}, new String[]{"", "Name", "Title",
                    "Account Name", "Office Phone"});
        this.addComponent(tableItem);
    }
}

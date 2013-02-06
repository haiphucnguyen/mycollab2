/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableDisplay;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;

/**
 *
 * @author haiphucnguyen
 */
public class OpportunityContactSelectionWindow extends RelatedItemSelectionWindow<SimpleContact, ContactSearchCriteria> {

    public OpportunityContactSelectionWindow(OpportunityContactListComp associateContactList) {
        super("Select Contacts", associateContactList);
        
        this.setWidth("900px");
    }

    @Override
    protected void initUI() {
        tableItem = new ContactTableDisplay(new String[]{"selected",
                    "contactName", "email", "officephone", "accountName"}, 
                new String[]{"", "Name", "Email", "Phone", "Account"});
        
        Button selectBtn = new Button("Select", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        
        this.bodyContent.addComponent(selectBtn);
        this.bodyContent.addComponent(tableItem);
    }
    
}

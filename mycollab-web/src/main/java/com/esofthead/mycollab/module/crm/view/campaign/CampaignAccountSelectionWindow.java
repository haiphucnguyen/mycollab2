/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.account.AccountTableDisplay;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;

/**
 *
 * @author haiphucnguyen
 */
public class CampaignAccountSelectionWindow extends RelatedItemSelectionWindow<SimpleAccount, AccountSearchCriteria> {

    public CampaignAccountSelectionWindow(CampaignAccountListComp associateContactList) {
        super("Select Accounts", associateContactList);
        
        this.setWidth("900px");
    }

    @Override
    protected void initUI() {
        tableItem = new AccountTableDisplay(new String[]{"selected",
                    "accountname", "phoneoffice", "email", "city"}, 
                new String[]{"", "Name", "Phone", "Email", "City"});
        
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

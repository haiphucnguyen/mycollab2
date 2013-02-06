/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.account.AccountSimpleSearchPanel;
import com.esofthead.mycollab.module.crm.view.account.AccountTableDisplay;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;

/**
 *
 * @author haiphucnguyen
 */
public class CampaignAccountSelectionWindow extends RelatedItemSelectionWindow<SimpleAccount, AccountSearchCriteria> {

    public CampaignAccountSelectionWindow(CampaignAccountListComp associateAccountList) {
        super("Select Accounts", associateAccountList);
        
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
        
        AccountSimpleSearchPanel accountSimpleSearchPanel = new AccountSimpleSearchPanel();
        accountSimpleSearchPanel.addSearchHandler(new SearchHandler<AccountSearchCriteria>(){

			@Override
			public void onSearch(AccountSearchCriteria criteria) {
				tableItem.setSearchCriteria(criteria);
			}
        	
        });
        
        this.bodyContent.addComponent(accountSimpleSearchPanel);
        this.bodyContent.addComponent(selectBtn);
        this.bodyContent.addComponent(tableItem);
    }
    
}

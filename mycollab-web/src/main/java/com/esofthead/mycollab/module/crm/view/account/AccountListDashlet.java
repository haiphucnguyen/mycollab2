/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class AccountListDashlet extends Depot {

    private AccountTableDisplay tableItem;

    public AccountListDashlet() {
        super("My Accounts", new VerticalLayout());
        tableItem = new AccountTableDisplay(new String[]{"accountname",
                    "city", "phoneoffice", "email"},
                new String[]{"Name", "City", "Phone Office",
                    "Email Address"});
        this.content.addComponent(tableItem);
    }

    @Override
    public void attach() {
        super.attach();
        
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setAssignUser(new StringSearchField(AppContext.getUsername()));
        tableItem.setSearchCriteria(criteria);
    }
    
    
}

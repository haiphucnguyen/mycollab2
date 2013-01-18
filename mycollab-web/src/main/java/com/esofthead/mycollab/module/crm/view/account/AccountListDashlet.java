/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
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
        tableItem = new AccountTableDisplay(new String[]{"accountname", "phoneoffice", "email"},
                new String[]{"Name", "Phone", "Email"});

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleAccount account = (SimpleAccount) event.getData();
                if ("accountname".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(new AccountEvent.GotoRead(AccountListDashlet.this, account.getId()));
                }
            }
        });
        this.content.addComponent(tableItem);
    }
    
    public void display() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setAssignUser(new StringSearchField(AppContext.getUsername()));
        tableItem.setSearchCriteria(criteria);
    }
}

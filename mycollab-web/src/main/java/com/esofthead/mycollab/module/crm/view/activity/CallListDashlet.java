/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.CallSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class CallListDashlet extends Depot {

    private CallTableDisplay tableItem;

    public CallListDashlet() {
        super("My Calls", new VerticalLayout());

        tableItem = new CallTableDisplay(new String[]{"subject", "startdate", "status"}, new String[]{"Subject", "Start Date", "Status"});
        this.content.addComponent(tableItem);
    }

    public void display() {
        CallSearchCriteria criteria = new CallSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setAssignUsers(new SetSearchField<String>(new String[]{AppContext.getUsername()}));
        tableItem.setSearchCriteria(criteria);
    }
}

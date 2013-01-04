/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class LeadListDashlet extends Depot {

    private LeadTableDisplay tableItem;

    public LeadListDashlet() {
        super("My Leads", new VerticalLayout());
        tableItem = new LeadTableDisplay(
                new String[]{"leadName", "title",
                    "officephone", "email"},
                new String[]{"Name", "Title",
                    "Office Phone", "Email"});
        this.content.addComponent(tableItem);
    }

    @Override
    public void attach() {
        super.attach();

        LeadSearchCriteria criteria = new LeadSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setAssignUsers(new SetSearchField<String>(new String[]{AppContext.getUsername()}));
        tableItem.setSearchCriteria(criteria);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
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
        tableItem = new LeadTableDisplay(new String[]{"leadName", "officephone", "email"},
                new String[]{"Name", "Phone", "Email"});
        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleLead lead = (SimpleLead) event.getData();
                if ("leadName".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(new LeadEvent.GotoRead(LeadListDashlet.this, lead.getId()));
                }
            }
        });
        this.bodyContent.addComponent(tableItem);
    }

    public void display() {
        LeadSearchCriteria criteria = new LeadSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setAssignUsers(new SetSearchField<String>(new String[]{AppContext.getUsername()}));
        tableItem.setSearchCriteria(criteria);
    }
}

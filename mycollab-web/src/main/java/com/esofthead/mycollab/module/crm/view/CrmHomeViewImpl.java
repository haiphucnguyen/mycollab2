package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.module.crm.view.account.AccountListDashlet;
import com.esofthead.mycollab.module.crm.view.activity.CallListDashlet;
import com.esofthead.mycollab.module.crm.view.activity.MeetingListDashlet;
import com.esofthead.mycollab.module.crm.view.lead.LeadListDashlet;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class CrmHomeViewImpl extends AbstractView implements CrmHomeView {
    
    public CrmHomeViewImpl() {
        this.setSpacing(true);
        this.setMargin(true);
    }
    
    @Override
    public void displayDashboard() {
        this.removeAllComponents();
        
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setWidth("100%");
        
        VerticalLayout myAssignmentsLayout = new VerticalLayout();
        myAssignmentsLayout.addComponent(new AccountListDashlet());
        myAssignmentsLayout.addComponent(new MeetingListDashlet());
        myAssignmentsLayout.addComponent(new CallListDashlet());
        myAssignmentsLayout.addComponent(new LeadListDashlet());
        
        layout.addComponent(myAssignmentsLayout);
        
        VerticalLayout streamsLayout = new VerticalLayout();
        streamsLayout.setWidth("400px");
        streamsLayout.addComponent(new ActivityStreamPanel());
        layout.addComponent(streamsLayout);
        layout.setComponentAlignment(streamsLayout, Alignment.MIDDLE_RIGHT);
        
        layout.setExpandRatio(myAssignmentsLayout, 1.0f);
        
        this.addComponent(layout);
    }
}

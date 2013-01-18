package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.module.crm.view.account.AccountListDashlet;
import com.esofthead.mycollab.module.crm.view.activity.CallListDashlet;
import com.esofthead.mycollab.module.crm.view.activity.MeetingListDashlet;
import com.esofthead.mycollab.module.crm.view.lead.LeadListDashlet;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class CrmHomeViewImpl extends AbstractView implements CrmHomeView {

    private final AccountListDashlet accountDashlet;
    private final MeetingListDashlet meetingDashlet;
    private final CallListDashlet callDashlet;
    private final LeadListDashlet leadDashlet;
    private final ActivityStreamPanel activityStreamPanel;
    
    private final SalesDashboardView salesDashboard;

    public CrmHomeViewImpl() {
        this.setSpacing(true);
        this.setMargin(false);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setWidth("100%");

        VerticalLayout myAssignmentsLayout = new VerticalLayout();

        accountDashlet = new AccountListDashlet();
        meetingDashlet = new MeetingListDashlet();
        callDashlet = new CallListDashlet();
        leadDashlet = new LeadListDashlet();

        myAssignmentsLayout.addComponent(new LazyLoadWrapper(accountDashlet));
        myAssignmentsLayout.addComponent(new LazyLoadWrapper(meetingDashlet));
        myAssignmentsLayout.addComponent(new LazyLoadWrapper(callDashlet));
        myAssignmentsLayout.addComponent(new LazyLoadWrapper(leadDashlet));

        layout.addComponent(myAssignmentsLayout);

        VerticalLayout streamsLayout = new VerticalLayout();
        streamsLayout.setWidth("550px");
        
        salesDashboard = new SalesDashboardView();
        salesDashboard.setWidth("540px");
        LazyLoadWrapper salesDashboardLazycomp = new LazyLoadWrapper(salesDashboard);
        streamsLayout.addComponent(salesDashboardLazycomp);
        streamsLayout.setComponentAlignment(salesDashboardLazycomp,
                Alignment.MIDDLE_RIGHT);
        
        activityStreamPanel = new ActivityStreamPanel();
        activityStreamPanel.setWidth("540px");
        LazyLoadWrapper activityLazyLoad = new LazyLoadWrapper(
                activityStreamPanel);
        streamsLayout.addComponent(activityLazyLoad);
        streamsLayout.setComponentAlignment(activityLazyLoad,
                Alignment.MIDDLE_RIGHT);
        
        layout.addComponent(streamsLayout);
        layout.setComponentAlignment(streamsLayout, Alignment.TOP_RIGHT);

        layout.setExpandRatio(myAssignmentsLayout, 1.0f);

        this.addComponent(layout);
    }

    @Override
    public void displayDashboard() {
        accountDashlet.display();
        meetingDashlet.display();
        callDashlet.display();
        leadDashlet.display();
        activityStreamPanel.display();
        salesDashboard.displayReport();
    }
}

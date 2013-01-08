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

    private AccountListDashlet accountDashlet;
    private MeetingListDashlet meetingDashlet;
    private CallListDashlet callDashlet;
    private LeadListDashlet leadDashlet;
    private ActivityStreamPanel activityStreamPanel;

    public CrmHomeViewImpl() {
        this.setSpacing(true);
        this.setMargin(true);

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
        streamsLayout.setWidth("410px");
        activityStreamPanel = new ActivityStreamPanel();
        activityStreamPanel.setWidth("400px");
        streamsLayout.addComponent(new LazyLoadWrapper(activityStreamPanel));
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
    }
}

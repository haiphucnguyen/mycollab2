package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.module.crm.view.account.AccountListDashlet;
import com.esofthead.mycollab.module.crm.view.activity.CallListDashlet;
import com.esofthead.mycollab.module.crm.view.activity.MeetingListDashlet;
import com.esofthead.mycollab.module.crm.view.lead.LeadListDashlet;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.Sizeable;
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
    private final ActivityStreamPanel activityStreamPanel;

    private final SalesDashboardView salesDashboard;

    public CrmHomeViewImpl() {
        this.setSpacing(true);
        this.setMargin(false);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setWidth("100%");

        VerticalLayout myAssignmentsLayout = new VerticalLayout();

        if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
            accountDashlet = new AccountListDashlet();
            myAssignmentsLayout
                    .addComponent(new LazyLoadWrapper(accountDashlet));
        }

        if (AppContext.canRead(RolePermissionCollections.CRM_MEETING)) {
            meetingDashlet = new MeetingListDashlet();
            myAssignmentsLayout
                    .addComponent(new LazyLoadWrapper(meetingDashlet));
        }

        if (AppContext.canRead(RolePermissionCollections.CRM_CALL)) {
            callDashlet = new CallListDashlet();
            myAssignmentsLayout.addComponent(new LazyLoadWrapper(callDashlet));
        }

        if (AppContext.canRead(RolePermissionCollections.CRM_LEAD)) {
            leadDashlet = new LeadListDashlet();
            myAssignmentsLayout.addComponent(new LazyLoadWrapper(leadDashlet));
        }

        layout.addComponent(myAssignmentsLayout);

        VerticalLayout streamsLayout = new VerticalLayout();
        streamsLayout.setMargin(false, false, false, true);
        streamsLayout.setWidth(Sizeable.SIZE_UNDEFINED, 0);

        salesDashboard = new SalesDashboardView();
        salesDashboard.setWidth("400px");
        LazyLoadWrapper salesDashboardLazycomp = new LazyLoadWrapper(
                salesDashboard);
        streamsLayout.addComponent(salesDashboardLazycomp);
        streamsLayout.setComponentAlignment(salesDashboardLazycomp,
                Alignment.MIDDLE_RIGHT);

        activityStreamPanel = new ActivityStreamPanel();
        activityStreamPanel.setWidth("400px");
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
        if (accountDashlet != null) {
            accountDashlet.display();
        }

        if (meetingDashlet != null) {
            meetingDashlet.display();
        }

        if (callDashlet != null) {
            callDashlet.display();
        }

        if (leadDashlet != null) {
            leadDashlet.display();
        }

        activityStreamPanel.display();
        salesDashboard.displayReport();
    }
}

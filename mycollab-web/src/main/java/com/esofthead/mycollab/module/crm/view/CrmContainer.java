package com.esofthead.mycollab.module.crm.view;

import java.util.Iterator;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.CrmEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CrmContainer extends AbstractView {

    private static final long serialVersionUID = 1L;
    private static final String ACCOUNT_LIST = "Accounts";
    private static final String NEW_ACCOUNT_ITEM = "New Account";
    private static final String NEW_CASE_ITEM = "New Case";
    private static final String CASE_LIST = "Cases";
    private static final String CONTACT_LIST = "Contacts";
    private static final String NEW_CONTACT_ITEM = "New Contact";
    private static final String CAMPAIGN_LIST = "Campaigns";
    private static final String NEW_CAMPAIGN_ITEM = "New Campaign";
    private static final String LEAD_LIST = "Leads";
    private static final String NEW_LEAD_ITEM = "New Lead";
    private static final String OPPORTUNITY_LIST = "Opportunities";
    private static final String NEW_OPPORTUNITY_ITEM = "New Opportunity";
    private static final String NEW_TASK_ITEM = "New Task";
    private static final String NEW_CALL_ITEM = "New Call";
    private static final String NEW_MEETING_ITEM = "New Meeting";
    private static final String ACTIVITIES_LIST = "Activities";
    private final VerticalLayout currentView;
    private final PopupButton addBtn;
    private final CssLayout toolbar;

    public CrmContainer() {
        ControllerRegistry.getInstance().addController(new CrmController(this));
        CustomLayout container = new CustomLayout("crmContainer");
        container.setStyleName("crmContainer");

        container.setWidth("100%");
        NavigatorItemListener listener = new NavigatorItemListener();

        toolbar = new CssLayout();

        Button homeBtn = new Button(null, listener);
        homeBtn.setStyleName("link");
        homeBtn.setIcon(new ThemeResource("icons/16/home.png"));
        toolbar.addComponent(homeBtn);

        Button accountList = new Button(ACCOUNT_LIST, listener);
        System.out.println("Enable: " + AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT));
        accountList.setEnabled(AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT));
        accountList.setStyleName("link");
        toolbar.addComponent(accountList);

        Button contactList = new Button(CONTACT_LIST, listener);
        contactList.setStyleName("link");
        toolbar.addComponent(contactList);

        Button campaignList = new Button(CAMPAIGN_LIST, listener);
        campaignList.setEnabled(AppContext.canRead(RolePermissionCollections.CRM_CAMPAIGN));
        campaignList.setStyleName("link");
        toolbar.addComponent(campaignList);

        Button leadList = new Button(LEAD_LIST, listener);
        leadList.setEnabled(AppContext.canRead(RolePermissionCollections.CRM_LEAD));
        leadList.setStyleName("link");
        toolbar.addComponent(leadList);

        Button opportunityList = new Button(OPPORTUNITY_LIST, listener);
        opportunityList.setEnabled(AppContext.canRead(RolePermissionCollections.CRM_OPPORTUNITY));
        opportunityList.setStyleName("link");
        toolbar.addComponent(opportunityList);

        Button caseList = new Button(CASE_LIST, listener);
        caseList.setEnabled(AppContext.canRead(RolePermissionCollections.CRM_CASE));
        caseList.setStyleName("link");
        toolbar.addComponent(caseList);

        Button activitiesList = new Button(ACTIVITIES_LIST, listener);
        boolean isActivityEnable = AppContext.canRead(RolePermissionCollections.CRM_MEETING) || AppContext.canRead(RolePermissionCollections.CRM_TASK) || AppContext.canRead(RolePermissionCollections.CRM_CALL);
        accountList.setEnabled(isActivityEnable);
        activitiesList.setStyleName("link");
        toolbar.addComponent(activitiesList);

        toolbar.setStyleName("h-sidebar-menu");

        addBtn = new PopupButton("Add");
        GridLayout addBtnLayout = new GridLayout(3, 2);
        addBtnLayout.setMargin(true);
        addBtnLayout.setWidth("300px");
        addBtnLayout.setSpacing(true);

        ButtonLink newAccountBtn = new ButtonLink(NEW_ACCOUNT_ITEM, listener);
        newAccountBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT));
        addBtnLayout.addComponent(newAccountBtn);

        ButtonLink newContactBtn = new ButtonLink(NEW_CONTACT_ITEM, listener);
        newContactBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CONTACT));
        addBtnLayout.addComponent(newContactBtn);

        ButtonLink newCampaignBtn = new ButtonLink(NEW_CAMPAIGN_ITEM, listener);
        newCampaignBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CAMPAIGN));
        addBtnLayout.addComponent(newCampaignBtn);

        ButtonLink newOpportunityBtn = new ButtonLink(NEW_OPPORTUNITY_ITEM,
                listener);
        newOpportunityBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));
        addBtnLayout.addComponent(newOpportunityBtn);

        ButtonLink newLeadBtn = new ButtonLink(NEW_LEAD_ITEM, listener);
        newLeadBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_LEAD));
        addBtnLayout.addComponent(newLeadBtn);

        ButtonLink newCaseBtn = new ButtonLink(NEW_CASE_ITEM, listener);
        newCaseBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CASE));
        addBtnLayout.addComponent(newCaseBtn);

        ButtonLink newTaskBtn = new ButtonLink(NEW_TASK_ITEM, listener);
        newTaskBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_TASK));
        addBtnLayout.addComponent(newTaskBtn);

        ButtonLink newCallBtn = new ButtonLink(NEW_CALL_ITEM, listener);
        newCallBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CALL));
        addBtnLayout.addComponent(newCallBtn);

        ButtonLink newMeetingBtn = new ButtonLink(NEW_MEETING_ITEM, listener);
        newMeetingBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_MEETING));
        addBtnLayout.addComponent(newMeetingBtn);


        addBtn.addComponent(addBtnLayout);
        addBtn.setStyleName("link");
        toolbar.addComponent(addBtn);

        container.addComponent(toolbar, "crmToolbar");

        currentView = new VerticalLayout();
        container.addComponent(currentView, "currentView");
        this.addComponent(container);
        this.setComponentAlignment(container, Alignment.MIDDLE_CENTER);

        EventBus.getInstance().fireEvent(new CrmEvent.GotoHome(this, null));
    }

    public void addView(View view) {
        currentView.removeAllComponents();
        currentView.addComponent(view.getWidget());
    }

    private class NavigatorItemListener implements Button.ClickListener {

        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            String caption = event.getButton().getCaption();

            if (caption == null) {
                EventBus.getInstance().fireEvent(
                        new CrmEvent.GotoHome(this, null));
            } else if (NEW_ACCOUNT_ITEM.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new AccountEvent.GotoAdd(this, null));
            } else if (ACCOUNT_LIST.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new AccountEvent.GotoList(this, null));
            } else if (NEW_CAMPAIGN_ITEM.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new CampaignEvent.GotoAdd(this, null));
            } else if (CAMPAIGN_LIST.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new CampaignEvent.GotoList(this, null));
            } else if (NEW_CASE_ITEM.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new CaseEvent.GotoAdd(this, null));
            } else if (CASE_LIST.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new CaseEvent.GotoList(this, null));
            } else if (CONTACT_LIST.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new ContactEvent.GotoList(this, null));
            } else if (NEW_CONTACT_ITEM.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new ContactEvent.GotoAdd(this, null));
            } else if (NEW_LEAD_ITEM.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new LeadEvent.GotoAdd(this, null));
            } else if (LEAD_LIST.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new LeadEvent.GotoList(this, null));
            } else if (NEW_OPPORTUNITY_ITEM.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new OpportunityEvent.GotoAdd(this, null));
            } else if (OPPORTUNITY_LIST.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new OpportunityEvent.GotoList(this, null));
            } else if (ACTIVITIES_LIST.equals(caption)) {
                EventBus.getInstance().fireEvent(
                        new ActivityEvent.GotoCalendar(this, null));
            } else if (NEW_TASK_ITEM.equals(caption)) {
                EventBus.getInstance().fireEvent(new ActivityEvent.TaskAdd(this, null));
            } else if (NEW_CALL_ITEM.equals(caption)) {
                EventBus.getInstance().fireEvent(new ActivityEvent.CallAdd(this, null));
            } else if (NEW_MEETING_ITEM.equals(caption)) {
                EventBus.getInstance().fireEvent(new ActivityEvent.MeetingAdd(this, null));
            }

            addBtn.setPopupVisible(false);

            for (Iterator<com.vaadin.ui.Component> it = toolbar
                    .getComponentIterator(); it.hasNext();) {
                Button btn = (Button) it.next();
                btn.removeStyleName("isSelected");
            }

            event.getButton().addStyleName("isSelected");
        }
    }
}

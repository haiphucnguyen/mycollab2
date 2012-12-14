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
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

public class CrmContainer extends AbstractView {
	private static final long serialVersionUID = 1L;

	private static String ACCOUNT_LIST = "Accounts";

	private static String NEW_ACCOUNT_ITEM = "New Account";

	private static String NEW_CASE_ITEM = "New Case";

	private static String CASE_LIST = "Cases";

	private static String CONTACT_LIST = "Contacts";

	private static String NEW_CONTACT_ITEM = "New Contact";

	private static String CAMPAIGN_LIST = "Campaigns";

	private static String NEW_CAMPAIGN_ITEM = "New Campaign";

	private static String LEAD_LIST = "Leads";

	private static String NEW_LEAD_ITEM = "New Lead";

	private static String OPPORTUNITY_LIST = "Opportunities";

	private static String NEW_OPPORTUNITY_ITEM = "New Opportunity";

	private static String ACTIVITIES_LIST = "Activities";

	private final VerticalLayout currentView;

	private final PopupButton addBtn;

	private final CssLayout toolbar;

	public CrmContainer() {
		new CrmController(this);
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
		accountList.setStyleName("link");
		toolbar.addComponent(accountList);

		Button contactList = new Button(CONTACT_LIST, listener);
		contactList.setStyleName("link");
		toolbar.addComponent(contactList);

		Button campaignList = new Button(CAMPAIGN_LIST, listener);
		campaignList.setStyleName("link");
		toolbar.addComponent(campaignList);

		Button leadList = new Button(LEAD_LIST, listener);
		leadList.setStyleName("link");
		toolbar.addComponent(leadList);

		Button opportunityList = new Button(OPPORTUNITY_LIST, listener);
		opportunityList.setStyleName("link");
		toolbar.addComponent(opportunityList);

		Button caseList = new Button(CASE_LIST, listener);
		caseList.setStyleName("link");
		toolbar.addComponent(caseList);

		Button activitiesList = new Button(ACTIVITIES_LIST, listener);
		activitiesList.setStyleName("link");
		toolbar.addComponent(activitiesList);

		toolbar.setStyleName("h-sidebar-menu");

		addBtn = new PopupButton("Add");
		GridLayout addBtnLayout = new GridLayout(3, 2);
		addBtnLayout.setMargin(true);
		addBtnLayout.setWidth("300px");
		addBtnLayout.setSpacing(true);

		ButtonLink newAccountBtn = new ButtonLink(NEW_ACCOUNT_ITEM, listener);
		addBtnLayout.addComponent(newAccountBtn);

		ButtonLink newContactBtn = new ButtonLink(NEW_CONTACT_ITEM, listener);
		addBtnLayout.addComponent(newContactBtn);

		ButtonLink newCampaignBtn = new ButtonLink(NEW_CAMPAIGN_ITEM, listener);
		addBtnLayout.addComponent(newCampaignBtn);

		ButtonLink newOpportunityBtn = new ButtonLink(NEW_OPPORTUNITY_ITEM,
				listener);
		addBtnLayout.addComponent(newOpportunityBtn);

		ButtonLink newLeadBtn = new ButtonLink(NEW_LEAD_ITEM, listener);
		addBtnLayout.addComponent(newLeadBtn);

		addBtnLayout.addComponent(new ButtonLink(NEW_CASE_ITEM, listener));

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

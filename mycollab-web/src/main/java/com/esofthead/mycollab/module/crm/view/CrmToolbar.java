package com.esofthead.mycollab.module.crm.view;

import java.util.Iterator;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.CrmEvent;
import com.esofthead.mycollab.module.crm.events.DocumentEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;

@ViewComponent
public class CrmToolbar extends CssLayout implements View {
	private class NavigatorItemListener implements Button.ClickListener {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(final ClickEvent event) {
			final String caption = event.getButton().getCaption();

			if (caption == null) {
				EventBus.getInstance().fireEvent(
						new CrmEvent.GotoHome(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_ACCOUNT_NEW_ACTION).equals(
					caption)) {
				EventBus.getInstance().fireEvent(
						new AccountEvent.GotoAdd(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_ACCOUNTS_HEADER).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new AccountEvent.GotoList(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_CAMPAIGN_NEW_ACTION).equals(
					caption)) {
				EventBus.getInstance().fireEvent(
						new CampaignEvent.GotoAdd(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_CAMPAIGNS_HEADER).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new CampaignEvent.GotoList(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_CASE_NEW_ACTION).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new CaseEvent.GotoAdd(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_CASES_HEADER).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new CaseEvent.GotoList(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_CONTACTS_HEADER).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new ContactEvent.GotoList(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_CONTACT_NEW_ACTION).equals(
					caption)) {
				EventBus.getInstance().fireEvent(
						new ContactEvent.GotoAdd(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_LEAD_NEW_ACTION).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new LeadEvent.GotoAdd(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_LEADS_HEADER).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new LeadEvent.GotoList(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_OPPORTUNITY_NEW_ACTION).equals(
					caption)) {
				EventBus.getInstance().fireEvent(
						new OpportunityEvent.GotoAdd(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_OPPORTUNTIES_HEADER).equals(
					caption)) {
				EventBus.getInstance().fireEvent(
						new OpportunityEvent.GotoList(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_ACTIVITIES_HEADER)
					.equals(caption)) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.GotoCalendar(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_TASK_NEW_ACTION).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.TaskAdd(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_CALL_NEW_ACTION).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.CallAdd(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_MEETING_NEW_ACTION).equals(
					caption)) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.MeetingAdd(this, null));
			} else if (LocalizationHelper.getMessage(
					CrmCommonI18nEnum.TOOLBAR_DOCUMENT_HEADER).equals(caption)) {
				EventBus.getInstance().fireEvent(
						new DocumentEvent.GotoDashboard(this, null));
			}

			addBtn.setPopupVisible(false);

			for (final Iterator<com.vaadin.ui.Component> it = getComponentIterator(); it
					.hasNext();) {
				final Button btn = (Button) it.next();
				btn.removeStyleName("isSelected");
			}

			event.getButton().addStyleName("isSelected");
		}
	}

	private static final long serialVersionUID = 1L;

	private final PopupButton addBtn;

	public CrmToolbar() {
		this.setWidth(Sizeable.SIZE_UNDEFINED, 0);
		this.setHeight("30px");
		final NavigatorItemListener listener = new NavigatorItemListener();
		final Button homeBtn = new Button(null, listener);
		homeBtn.setStyleName("link");
		homeBtn.setIcon(MyCollabResource.newResource("icons/16/home.png"));
		addComponent(homeBtn);

		final Button accountList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_ACCOUNTS_HEADER),
				listener);
		accountList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_ACCOUNT));
		accountList.setStyleName("link");
		addComponent(accountList);

		final Button contactList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CONTACTS_HEADER),
				listener);
		contactList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_CONTACT));
		contactList.setStyleName("link");
		addComponent(contactList);

		final Button campaignList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CAMPAIGNS_HEADER),
				listener);
		campaignList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_CAMPAIGN));
		campaignList.setStyleName("link");
		addComponent(campaignList);

		final Button leadList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_LEADS_HEADER),
				listener);
		leadList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_LEAD));
		leadList.setStyleName("link");
		addComponent(leadList);

		final Button opportunityList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_OPPORTUNTIES_HEADER),
				listener);
		opportunityList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_OPPORTUNITY));
		opportunityList.setStyleName("link");
		addComponent(opportunityList);

		final Button caseList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CASES_HEADER),
				listener);
		caseList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_CASE));
		caseList.setStyleName("link");
		addComponent(caseList);

		final Button activitiesList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_ACTIVITIES_HEADER),
				listener);
		final boolean isActivityEnable = AppContext
				.canRead(RolePermissionCollections.CRM_MEETING)
				|| AppContext.canRead(RolePermissionCollections.CRM_TASK)
				|| AppContext.canRead(RolePermissionCollections.CRM_CALL);
		activitiesList.setEnabled(isActivityEnable);
		activitiesList.setStyleName("link");
		addComponent(activitiesList);

		setStyleName("h-sidebar-menu");

		final Button fileBtn = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_DOCUMENT_HEADER),
				listener);
		fileBtn.setStyleName("link");
		addComponent(fileBtn);

		addBtn = new PopupButton("Add");
		final GridLayout addBtnLayout = new GridLayout(3, 2);
		addBtnLayout.setMargin(true);
		addBtnLayout.setWidth("370px");
		addBtnLayout.setSpacing(true);

		final ButtonLink newAccountBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_ACCOUNT_NEW_ACTION),
				listener);
		newAccountBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_ACCOUNT));
		newAccountBtn.setIcon(MyCollabResource
				.newResource("icons/18/crm/account.png"));
		addBtnLayout.addComponent(newAccountBtn);

		final ButtonLink newContactBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CONTACT_NEW_ACTION),
				listener);
		newContactBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CONTACT));
		newContactBtn.setIcon(MyCollabResource
				.newResource("icons/18/crm/contact.png"));
		addBtnLayout.addComponent(newContactBtn);

		final ButtonLink newCampaignBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CAMPAIGN_NEW_ACTION),
				listener);
		newCampaignBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CAMPAIGN));
		newCampaignBtn.setIcon(MyCollabResource
				.newResource("icons/18/crm/campaign.png"));
		addBtnLayout.addComponent(newCampaignBtn);

		final ButtonLink newOpportunityBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_OPPORTUNITY_NEW_ACTION),
				listener);
		newOpportunityBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));
		newOpportunityBtn.setIcon(MyCollabResource
				.newResource("icons/18/crm/opportunity.png"));
		addBtnLayout.addComponent(newOpportunityBtn);

		final ButtonLink newLeadBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_LEAD_NEW_ACTION),
				listener);
		newLeadBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_LEAD));
		newLeadBtn.setIcon(MyCollabResource
				.newResource("icons/18/crm/lead.png"));
		addBtnLayout.addComponent(newLeadBtn);

		final ButtonLink newCaseBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CASE_NEW_ACTION),
				listener);
		newCaseBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CASE));
		newCaseBtn.setIcon(MyCollabResource
				.newResource("icons/18/crm/case.png"));
		addBtnLayout.addComponent(newCaseBtn);

		final ButtonLink newTaskBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_TASK_NEW_ACTION),
				listener);
		newTaskBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_TASK));
		newTaskBtn.setIcon(MyCollabResource
				.newResource("icons/18/crm/task.png"));
		addBtnLayout.addComponent(newTaskBtn);

		final ButtonLink newCallBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CALL_NEW_ACTION),
				listener);
		newCallBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CALL));
		newCallBtn.setIcon(MyCollabResource
				.newResource("icons/18/crm/call.png"));
		addBtnLayout.addComponent(newCallBtn);

		final ButtonLink newMeetingBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_MEETING_NEW_ACTION),
				listener);
		newMeetingBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_MEETING));
		newMeetingBtn.setIcon(MyCollabResource
				.newResource("icons/18/crm/meeting.png"));
		addBtnLayout.addComponent(newMeetingBtn);

		addBtn.addComponent(addBtnLayout);
		addBtn.setStyleName("link");
		addComponent(addBtn);
	}

	@Override
	public void addViewListener(
			final ApplicationEventListener<? extends ApplicationEvent> listener) {
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	public void gotoItem(final String crmItem) {
		for (final Iterator<com.vaadin.ui.Component> it = getComponentIterator(); it
				.hasNext();) {
			final Button btn = (Button) it.next();
			if (crmItem.equals(btn.getCaption())) {
				btn.addStyleName("isSelected");
			} else {
				btn.removeStyleName("isSelected");
			}
		}
	}

}

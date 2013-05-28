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
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CrmModule extends AbstractView implements IModule {

	private static final long serialVersionUID = 1L;

	private final VerticalLayout currentView;
	private final PopupButton addBtn;
	private final CssLayout toolbar;

	public CrmModule() {
		ControllerRegistry.addController(new CrmController(this));
		CustomLayout container = new CustomLayout("crmContainer");
		container.setStyleName("crmContainer");

		container.setWidth("100%");
		NavigatorItemListener listener = new NavigatorItemListener();

		toolbar = new CssLayout();

		Button homeBtn = new Button(null, listener);
		homeBtn.setStyleName("link");
		homeBtn.setIcon(new ThemeResource("icons/16/home.png"));
		toolbar.addComponent(homeBtn);

		Button accountList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_ACCOUNTS_HEADER),
				listener);
		accountList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_ACCOUNT));
		accountList.setStyleName("link");
		toolbar.addComponent(accountList);

		Button contactList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CONTACTS_HEADER),
				listener);
		contactList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_CONTACT));
		contactList.setStyleName("link");
		toolbar.addComponent(contactList);

		Button campaignList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CAMPAIGNS_HEADER),
				listener);
		campaignList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_CAMPAIGN));
		campaignList.setStyleName("link");
		toolbar.addComponent(campaignList);

		Button leadList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_LEADS_HEADER),
				listener);
		leadList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_LEAD));
		leadList.setStyleName("link");
		toolbar.addComponent(leadList);

		Button opportunityList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_OPPORTUNTIES_HEADER),
				listener);
		opportunityList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_OPPORTUNITY));
		opportunityList.setStyleName("link");
		toolbar.addComponent(opportunityList);

		Button caseList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CASES_HEADER),
				listener);
		caseList.setEnabled(AppContext
				.canRead(RolePermissionCollections.CRM_CASE));
		caseList.setStyleName("link");
		toolbar.addComponent(caseList);

		Button activitiesList = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_ACTIVITIES_HEADER),
				listener);
		boolean isActivityEnable = AppContext
				.canRead(RolePermissionCollections.CRM_MEETING)
				|| AppContext.canRead(RolePermissionCollections.CRM_TASK)
				|| AppContext.canRead(RolePermissionCollections.CRM_CALL);
		activitiesList.setEnabled(isActivityEnable);
		activitiesList.setStyleName("link");
		toolbar.addComponent(activitiesList);

		toolbar.setStyleName("h-sidebar-menu");

		addBtn = new PopupButton("Add");
		GridLayout addBtnLayout = new GridLayout(3, 2);
		addBtnLayout.setMargin(true);
		addBtnLayout.setWidth("370px");
		addBtnLayout.setSpacing(true);

		ButtonLink newAccountBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_ACCOUNT_NEW_ACTION),
				listener);
		newAccountBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_ACCOUNT));
		newAccountBtn.setIcon(new ThemeResource("icons/18/crm/account.png"));
		addBtnLayout.addComponent(newAccountBtn);

		ButtonLink newContactBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CONTACT_NEW_ACTION),
				listener);
		newContactBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CONTACT));
		newContactBtn.setIcon(new ThemeResource("icons/18/crm/contact.png"));
		addBtnLayout.addComponent(newContactBtn);

		ButtonLink newCampaignBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CAMPAIGN_NEW_ACTION),
				listener);
		newCampaignBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CAMPAIGN));
		newCampaignBtn.setIcon(new ThemeResource("icons/18/crm/campaign.png"));
		addBtnLayout.addComponent(newCampaignBtn);

		ButtonLink newOpportunityBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_OPPORTUNITY_NEW_ACTION),
				listener);
		newOpportunityBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));
		newOpportunityBtn.setIcon(new ThemeResource("icons/18/crm/opportunity.png"));
		addBtnLayout.addComponent(newOpportunityBtn);

		ButtonLink newLeadBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_LEAD_NEW_ACTION),
				listener);
		newLeadBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_LEAD));
		newLeadBtn.setIcon(new ThemeResource("icons/18/crm/lead.png"));
		addBtnLayout.addComponent(newLeadBtn);

		ButtonLink newCaseBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CASE_NEW_ACTION),
				listener);
		newCaseBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CASE));
		newCaseBtn.setIcon(new ThemeResource("icons/18/crm/case.png"));
		addBtnLayout.addComponent(newCaseBtn);

		ButtonLink newTaskBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_TASK_NEW_ACTION),
				listener);
		newTaskBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_TASK));
		newTaskBtn.setIcon(new ThemeResource("icons/18/crm/task.png"));
		addBtnLayout.addComponent(newTaskBtn);

		ButtonLink newCallBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_CALL_NEW_ACTION),
				listener);
		newCallBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CALL));
		newCallBtn.setIcon(new ThemeResource("icons/18/crm/call.png"));
		addBtnLayout.addComponent(newCallBtn);

		ButtonLink newMeetingBtn = new ButtonLink(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TOOLBAR_MEETING_NEW_ACTION),
				listener);
		newMeetingBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_MEETING));
		newMeetingBtn.setIcon(new ThemeResource("icons/18/crm/meeting.png"));
		addBtnLayout.addComponent(newMeetingBtn);

		addBtn.addComponent(addBtnLayout);
		addBtn.setStyleName("link");
		toolbar.addComponent(addBtn);

		container.addComponent(toolbar, "crmToolbar");

		currentView = new VerticalLayout();
		container.addComponent(currentView, "currentView");
		this.addComponent(container);
		this.setComponentAlignment(container, Alignment.MIDDLE_CENTER);
	}

	public void gotoCrmDashboard() {
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

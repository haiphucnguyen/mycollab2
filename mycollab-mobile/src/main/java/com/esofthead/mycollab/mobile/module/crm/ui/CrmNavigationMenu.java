package com.esofthead.mycollab.mobile.module.crm.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.module.crm.events.AccountEvent;
import com.esofthead.mycollab.mobile.module.user.ui.UserPanel;
import com.esofthead.mycollab.mobile.ui.AbstractNavigationMenu;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class CrmNavigationMenu extends AbstractNavigationMenu {
	private static final long serialVersionUID = -4835237473507947020L;
	
	private static Logger log = Logger.getLogger(CrmNavigationMenu.class);
	
	private final Map<String, MenuButton> buttonMap = new HashMap<String, MenuButton>();
	
	public CrmNavigationMenu() {
		super();
		
		setWidth("100%");
		
		UserPanel userPanel = new UserPanel();
		userPanel.setWidth("100%");
		addComponent(userPanel);
		
		/*final MenuButton homeBtn = new MenuButton("Home", "&#xE600;");
		addComponent(homeBtn);
		buttonMap.put("Home", homeBtn);*/
		
		final MenuButton accountBtn = new MenuButton("Accounts", "&#xE601;");
		addComponent(accountBtn);
		buttonMap.put("Accounts", accountBtn);
		
		final MenuButton contactBtn = new MenuButton("Contacts", "&#xE603;");
		addComponent(contactBtn);
		buttonMap.put("Contacts", contactBtn);
		
		final MenuButton campaignBtn = new MenuButton("Campaigns", "&#xE602;");
		addComponent(campaignBtn);
		buttonMap.put("Campaign", campaignBtn);
		
		final MenuButton leadBtn = new MenuButton("Leads", "&#xE609;");
		addComponent(leadBtn);
		buttonMap.put("Leads", leadBtn);
		
		final MenuButton opportunityBtn = new MenuButton("Opportunities", "&#xE604;");
		addComponent(opportunityBtn);
		buttonMap.put("Opportunities", opportunityBtn);
		
		final MenuButton caseBtn = new MenuButton("Cases", "&#xE605;");
		addComponent(caseBtn);
		buttonMap.put("Cases", caseBtn);
		
		final MenuButton activityBtn = new MenuButton("Activities", "&#xE606;");
		addComponent(activityBtn);
		buttonMap.put("Activities", activityBtn);
		
		final MenuButton documentBtn = new MenuButton("Documents", "&#xE607;");
		addComponent(documentBtn);
		buttonMap.put("Documents", documentBtn);
		
		final MenuButton settingBtn = new MenuButton("Settings", "&#xE608;");
		addComponent(settingBtn);
		buttonMap.put("Settings", settingBtn);
	}
	
	@Override
	protected Button.ClickListener createDefaultButtonClickListener() {
		
		return new Button.ClickListener() {

			private static final long serialVersionUID = 1L;
	
			@Override
			public void buttonClick(final ClickEvent event) {
				final String caption = ((MenuButton) event.getButton()).getBtnId();
				
				if ("Accounts".equals(
						caption)) {
					EventBus.getInstance().fireEvent(
							new AccountEvent.GotoList(this, null));
				} /*else if (LocalizationHelper.getMessage(
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
				} else if (LocalizationHelper.getMessage(
						CrmCommonI18nEnum.TOOLBAR_CRMNOTIFICATION_HEADER).equals(
						caption)) {
					EventBus.getInstance()
							.fireEvent(
									new CrmSettingEvent.GotoNotificationSetting(
											this, null));
				}*/
	
				//addBtn.setPopupVisible(false);
	
				for (final Iterator<MenuButton> it = CrmNavigationMenu.this
						.buttonIterator(); it.hasNext();) {
					final MenuButton btn = it.next();
					btn.removeStyleName("isSelected");
				}
	
				event.getButton().addStyleName("isSelected");
			}
		};
	}
	
	public Iterator<MenuButton> buttonIterator() {
		return buttonMap.values().iterator();
	}
}

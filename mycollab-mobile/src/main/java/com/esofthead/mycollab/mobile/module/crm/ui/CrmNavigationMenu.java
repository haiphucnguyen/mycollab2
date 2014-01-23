package com.esofthead.mycollab.mobile.module.crm.ui;

import com.esofthead.mycollab.mobile.module.user.ui.UserPanel;
import com.esofthead.mycollab.mobile.ui.AbstractNavigationMenu;

public class CrmNavigationMenu extends AbstractNavigationMenu {
	private static final long serialVersionUID = -4835237473507947020L;
	
	public CrmNavigationMenu() {
		super();
		
		setWidth("100%");
		
		UserPanel userPanel = new UserPanel();
		userPanel.setWidth("100%");
		addComponent(userPanel);
		
		final MenuButton homeBtn = new MenuButton("Home", "&#xE600;");
		addComponent(homeBtn);
		
		final MenuButton accountBtn = new MenuButton("Accounts", "&#xE601;");
		addComponent(accountBtn);
		
		final MenuButton contactBtn = new MenuButton("Contacts", "&#xE603;");
		addComponent(contactBtn);
		
		final MenuButton campaignBtn = new MenuButton("Campaigns", "&#xE602;");
		addComponent(campaignBtn);
		
		final MenuButton leadBtn = new MenuButton("Leads", "&#xE609;");
		addComponent(leadBtn);
		
		final MenuButton opportunityBtn = new MenuButton("Opportunities", "&#xE604;");
		addComponent(opportunityBtn);
		
		final MenuButton caseBtn = new MenuButton("Cases", "&#xE605;");
		addComponent(caseBtn);
		
		final MenuButton activityBtn = new MenuButton("Activities", "&#xE606;");
		addComponent(activityBtn);
		
		final MenuButton documentBtn = new MenuButton("Documents", "&#xE607;");
		addComponent(documentBtn);
		
		final MenuButton settingBtn = new MenuButton("Settings", "&#xE608;");
		addComponent(settingBtn);
	}
}

package com.esofthead.mycollab.module.crm.ui;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class CrmHome extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	
	private static String NEW_ACCOUNT_ITEM = "New Account";

	private CssLayout tabContainer;

	private NewItemListener newItemListener;

	private IPage currentView;

	public CrmHome() {
		super();
		tabContainer = new CssLayout();
		tabContainer.setStyleName("toolbar");
		tabContainer.setWidth("100%");

		Button accountBtn = new Button("Accounts");
		tabContainer.addComponent(accountBtn);

		Button contactBtn = new Button("Contacts");
		tabContainer.addComponent(contactBtn);

		Button campaignBtn = new Button("Campaigns");
		tabContainer.addComponent(campaignBtn);

		PopupButton addBtn = new PopupButton("Add");
		GridLayout addBtnLayout = new GridLayout(3, 2);

		newItemListener = new NewItemListener();

		Button newAccountBtn = new Button(NEW_ACCOUNT_ITEM, newItemListener);
		newAccountBtn.setStyleName(BaseTheme.BUTTON_LINK);

		addBtnLayout.addComponent(newAccountBtn);

		Button newCampaignBtn = new Button("New Campaign", newItemListener);
		newCampaignBtn.setStyleName(BaseTheme.BUTTON_LINK);

		addBtnLayout.addComponent(newCampaignBtn);

		Button newOpportunityBtn = new Button("New Opportunity",
				newItemListener);
		newOpportunityBtn.setStyleName(BaseTheme.BUTTON_LINK);

		addBtnLayout.addComponent(newOpportunityBtn);

		Button newLeadBtn = new Button("New Lead", newItemListener);
		newLeadBtn.setStyleName(BaseTheme.BUTTON_LINK);
		addBtnLayout.addComponent(newLeadBtn);

		addBtn.addComponent(addBtnLayout);
		tabContainer.addComponent(addBtn);

		this.addComponent(tabContainer);
	}
	
	private void addView(IPage component) {
		if (currentView == null) {
			this.addComponent((Component)component);
			currentView = component;
		} else {
			this.replaceComponent((Component)currentView, (Component)component);
			currentView = component;
		}
	}

	private class NewItemListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			String caption = event.getButton().getCaption();

			if (NEW_ACCOUNT_ITEM.equals(caption)) {
				addView(new AccountAdd());
			}
		}

	}

}

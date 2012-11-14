package com.esofthead.mycollab.module.crm.ui;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.CrmEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@Scope("prototype")
@Component
public class CrmContainer extends AbstractView {
	private static final long serialVersionUID = 1L;

	private static String ACCOUNT_LIST = "Accounts";

	private static String NEW_ACCOUNT_ITEM = "New Account";

	private static String CONTACT_LIST = "Contacts";

	private static String NEW_CONTACT_ITEM = "New Contact";

	private static String CAMPAIGN_LIST = "Campaigns";

	private static String NEW_CAMPAIGN_ITEM = "New Campaign";

	private static String LEAD_LIST = "Leads";

	private static String NEW_LEAD_ITEM = "New Lead";

	private static String OPPORTUNITY_LIST = "Opportunities";

	private static String NEW_OPPORTUNITY_ITEM = "New Opportunity";

	private VerticalLayout currentView;

	private PopupButton addBtn;

	private CssLayout toolbar;

	@PostConstruct
	private void init() {
		registerCommonListeners();
		registerAccountListeners();
		registerCampaignListeners();
		registerContactListeners();
		registerLeadListeners();
		registerOpportunityListeners();
	}

	@Override
	protected void initializeLayout() {
		CustomLayout container = new CustomLayout("crmContainer");

		container.setWidth("100%");
		NavigatorItemListener listener = new NavigatorItemListener();

		toolbar = new CssLayout();
		toolbar.setWidth("100%");

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

		toolbar.setStyleName("h-sidebar-menu");

		addBtn = new PopupButton("Add");
		GridLayout addBtnLayout = new GridLayout(3, 2);
		addBtnLayout.setMargin(true);
		addBtnLayout.setSpacing(true);

		Button newAccountBtn = new Button(NEW_ACCOUNT_ITEM, listener);
		newAccountBtn.setStyleName(BaseTheme.BUTTON_LINK);
		addBtnLayout.addComponent(newAccountBtn);

		Button newContactBtn = new Button(NEW_CONTACT_ITEM, listener);
		newContactBtn.setStyleName(BaseTheme.BUTTON_LINK);
		addBtnLayout.addComponent(newContactBtn);

		Button newCampaignBtn = new Button(NEW_CAMPAIGN_ITEM, listener);
		newCampaignBtn.setStyleName(BaseTheme.BUTTON_LINK);
		addBtnLayout.addComponent(newCampaignBtn);

		Button newOpportunityBtn = new Button(NEW_OPPORTUNITY_ITEM, listener);
		newOpportunityBtn.setStyleName(BaseTheme.BUTTON_LINK);
		addBtnLayout.addComponent(newOpportunityBtn);

		Button newLeadBtn = new Button(NEW_LEAD_ITEM, listener);
		newLeadBtn.setStyleName(BaseTheme.BUTTON_LINK);
		addBtnLayout.addComponent(newLeadBtn);

		addBtn.addComponent(addBtnLayout);
		addBtn.setStyleName("link");
		toolbar.addComponent(addBtn);

		container.addComponent(toolbar, "crmToolbar");

		currentView = new VerticalLayout();
		container.addComponent(currentView, "currentView");
		eventBus.fireEvent(new CrmEvent.GotoHome(this, null));
		this.addComponent(container);
	}
	
	@SuppressWarnings("serial")
	private void registerCommonListeners() {
		eventBus.addListener(new ApplicationEventListener<CrmEvent.GotoHome>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return CrmEvent.GotoHome.class;
			}

			@Override
			public void handle(CrmEvent.GotoHome event) {
				CrmHomeViewImpl view = AppContext.getView(CrmHomeViewImpl.class);
				addView(view);
			}
		});
	}

	@SuppressWarnings("serial")
	private void registerAccountListeners() {
		eventBus.addListener(new ApplicationEventListener<AccountEvent.GotoAdd>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.GotoAdd.class;
			}

			@Override
			public void handle(AccountEvent.GotoAdd event) {
				AccountAddView accountAddView = AppContext
						.getView(AccountAddViewImpl.class);
				accountAddView.addNewItem();
				addView((AbstractView) accountAddView);
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<AccountEvent.GotoRead>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.GotoRead.class;
			}

			@Override
			public void handle(AccountEvent.GotoRead event) {
				AccountAddView accountAddView = AppContext
						.getView(AccountAddViewImpl.class);
				accountAddView.editItem((Account) event.getData());
				addView((AbstractView) accountAddView);
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<AccountEvent.GotoList>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.GotoList.class;
			}

			@Override
			public void handle(AccountEvent.GotoList event) {
				AccountListViewImpl accountListView = AppContext
						.getView(AccountListViewImpl.class);
				addView(accountListView);
				accountListView.doDefaultSearch();
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<AccountEvent.GotoEdit>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.GotoEdit.class;
			}

			@Override
			public void handle(AccountEvent.GotoEdit event) {
				AccountAddView accountView = AppContext
						.getView(AccountAddViewImpl.class);
				accountView.editItem((Account) event.getData());
				addView((AbstractView) accountView);
			}
		});
	}

	@SuppressWarnings("serial")
	private void registerCampaignListeners() {
		eventBus.addListener(new ApplicationEventListener<CampaignEvent.GotoAdd>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return CampaignEvent.GotoAdd.class;
			}

			@Override
			public void handle(CampaignEvent.GotoAdd event) {
				CampaignAddViewImpl view = AppContext
						.getView(CampaignAddViewImpl.class);
				addView(view);
				view.addNewItem();
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<CampaignEvent.GotoRead>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return CampaignEvent.GotoRead.class;
			}

			@Override
			public void handle(CampaignEvent.GotoRead event) {
				CampaignAddViewImpl view = AppContext
						.getView(CampaignAddViewImpl.class);
				view.editItem((Campaign) event.getData());
				addView(view);
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<CampaignEvent.GotoEdit>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return CampaignEvent.GotoEdit.class;
			}

			@Override
			public void handle(CampaignEvent.GotoEdit event) {
				CampaignAddViewImpl view = AppContext
						.getView(CampaignAddViewImpl.class);
				view.editItem((Campaign) event.getData());
				addView(view);
			}
		});

		eventBus.addListener(new ApplicationEventListener<CampaignEvent.GotoList>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return CampaignEvent.GotoList.class;
			}

			@Override
			public void handle(CampaignEvent.GotoList event) {
				CampaignListViewImpl campaignListView = AppContext
						.getView(CampaignListViewImpl.class);
				addView(campaignListView);
				campaignListView.doDefaultSearch();
				addBtn.setPopupVisible(false);
			}
		});
	}

	@SuppressWarnings("serial")
	private void registerContactListeners() {
		eventBus.addListener(new ApplicationEventListener<ContactEvent.GotoAdd>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ContactEvent.GotoAdd.class;
			}

			@Override
			public void handle(ContactEvent.GotoAdd event) {
				ContactAddViewImpl view = AppContext
						.getView(ContactAddViewImpl.class);
				addView(view);
				view.addNewItem();
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<ContactEvent.GotoRead>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ContactEvent.GotoRead.class;
			}

			@Override
			public void handle(ContactEvent.GotoRead event) {
				ContactAddViewImpl view = AppContext
						.getView(ContactAddViewImpl.class);
				addView(view);
				view.viewItem((Contact) event.getData());
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<ContactEvent.GotoEdit>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ContactEvent.GotoEdit.class;
			}

			@Override
			public void handle(ContactEvent.GotoEdit event) {
				ContactAddViewImpl view = AppContext
						.getView(ContactAddViewImpl.class);
				addView(view);
				view.editItem((Contact) event.getData());
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<ContactEvent.GotoList>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ContactEvent.GotoList.class;
			}

			@Override
			public void handle(ContactEvent.GotoList event) {
				ContactListViewImpl contactListView = AppContext
						.getView(ContactListViewImpl.class);
				addView(contactListView);
				contactListView.doDefaultSearch();
				addBtn.setPopupVisible(false);
			}
		});
	}

	private void registerLeadListeners() {
		eventBus.addListener(new ApplicationEventListener<LeadEvent.GotoAdd>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return LeadEvent.GotoAdd.class;
			}

			@Override
			public void handle(LeadEvent.GotoAdd event) {
				LeadAddView leadAddView = AppContext
						.getView(LeadAddViewImpl.class);
				leadAddView.addNewItem();
				addView((AbstractView) leadAddView);
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<LeadEvent.GotoRead>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return LeadEvent.GotoRead.class;
			}

			@Override
			public void handle(LeadEvent.GotoRead event) {
				LeadAddView leadAddView = AppContext
						.getView(LeadAddViewImpl.class);
				leadAddView.editItem((Lead) event.getData());
				addView((AbstractView) leadAddView);
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<LeadEvent.GotoList>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return LeadEvent.GotoList.class;
			}

			@Override
			public void handle(LeadEvent.GotoList event) {
				LeadListViewImpl accountListView = AppContext
						.getView(LeadListViewImpl.class);
				addView(accountListView);
				accountListView.doDefaultSearch();
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<LeadEvent.GotoEdit>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return LeadEvent.GotoEdit.class;
			}

			@Override
			public void handle(LeadEvent.GotoEdit event) {
				LeadAddViewImpl leadView = AppContext
						.getView(LeadAddViewImpl.class);
				leadView.editItem((Lead) event.getData());
				addView(leadView);
			}
		});
	}

	private void registerOpportunityListeners() {
		eventBus.addListener(new ApplicationEventListener<OpportunityEvent.GotoAdd>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return OpportunityEvent.GotoAdd.class;
			}

			@Override
			public void handle(OpportunityEvent.GotoAdd event) {
				OpportunityAddViewImpl view = AppContext
						.getView(OpportunityAddViewImpl.class);
				view.addNewItem();
				addView(view);
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<OpportunityEvent.GotoRead>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return OpportunityEvent.GotoRead.class;
			}

			@Override
			public void handle(OpportunityEvent.GotoRead event) {
				OpportunityAddViewImpl view = AppContext
						.getView(OpportunityAddViewImpl.class);
				view.editItem((Opportunity) event.getData());
				addView(view);
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<OpportunityEvent.GotoList>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return OpportunityEvent.GotoList.class;
			}

			@Override
			public void handle(OpportunityEvent.GotoList event) {
				OpportunityListViewImpl view = AppContext
						.getView(OpportunityListViewImpl.class);
				addView(view);
				view.doDefaultSearch();
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<OpportunityEvent.GotoEdit>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return OpportunityEvent.GotoEdit.class;
			}

			@Override
			public void handle(OpportunityEvent.GotoEdit event) {
				OpportunityAddViewImpl view = AppContext
						.getView(OpportunityAddViewImpl.class);
				view.editItem((Opportunity) event.getData());
				addView(view);
			}
		});
	}

	private void addView(AbstractView view) {
		currentView.removeAllComponents();
		currentView.addComponent(view);
	}

	private class NavigatorItemListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			String caption = event.getButton().getCaption();

			if (caption == null) {
				eventBus.fireEvent(new CrmEvent.GotoHome(this, null));
			} else if (NEW_ACCOUNT_ITEM.equals(caption)) {
				eventBus.fireEvent(new AccountEvent.GotoAdd(this, null));
			} else if (ACCOUNT_LIST.equals(caption)) {
				eventBus.fireEvent(new AccountEvent.GotoList(this, null));
			} else if (NEW_CAMPAIGN_ITEM.equals(caption)) {
				eventBus.fireEvent(new CampaignEvent.GotoAdd(this, null));
			} else if (CAMPAIGN_LIST.equals(caption)) {
				eventBus.fireEvent(new CampaignEvent.GotoList(this, null));
			} else if (CONTACT_LIST.equals(caption)) {
				eventBus.fireEvent(new ContactEvent.GotoList(this, null));
			} else if (NEW_CONTACT_ITEM.equals(caption)) {
				eventBus.fireEvent(new ContactEvent.GotoAdd(this, null));
			} else if (NEW_LEAD_ITEM.equals(caption)) {
				eventBus.fireEvent(new LeadEvent.GotoAdd(this, null));
			} else if (LEAD_LIST.equals(caption)) {
				eventBus.fireEvent(new LeadEvent.GotoList(this, null));
			} else if (NEW_OPPORTUNITY_ITEM.equals(caption)) {
				eventBus.fireEvent(new OpportunityEvent.GotoAdd(this, null));
			} else if (OPPORTUNITY_LIST.equals(caption)) {
				eventBus.fireEvent(new OpportunityEvent.GotoList(this, null));
			}

			for (Iterator<com.vaadin.ui.Component> it = toolbar
					.getComponentIterator(); it.hasNext();) {
				Button btn = (Button) it.next();
				btn.removeStyleName("isSelected");
			}

			event.getButton().addStyleName("isSelected");
		}
	}

}

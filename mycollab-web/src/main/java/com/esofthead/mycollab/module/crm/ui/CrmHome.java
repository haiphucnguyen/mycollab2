package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@Scope("prototype")
@Component
public class CrmHome extends AbstractView {
	private static final long serialVersionUID = 1L;

	private static String ACCOUNT_LIST = "Accounts";

	private static String NEW_ACCOUNT_ITEM = "New Account";

	private static String CONTACT_LIST = "Contacts";

	private static String NEW_CAMPAIGN_ITEM = "New Campaign";

	private static String LEAD_LIST = "Leads";

	private VerticalLayout currentView;

	private PopupButton addBtn;

	public CrmHome() {
		super();
	}

	@PostConstruct
	private void init() {
		registerAccountListeners();
		registerCampaignListeners();
		registerContactListeners();
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
				// addView(AppContext.getSpringBean(CampaignAddViewImpl.class),
				// new Params(Params.ADD, null));
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<CampaignEvent.GotoList>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return CampaignEvent.GotoList.class;
			}

			@Override
			public void handle(CampaignEvent.GotoList event) {
				// addView(AppContext.getSpringBean(ContactListViewImpl.class),
				// new Params());
				addBtn.setPopupVisible(false);
			}
		});
	}

	@SuppressWarnings("serial")
	private void registerContactListeners() {
		eventBus.addListener(new ApplicationEventListener<ContactEvent.GotoAdd>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.GotoAdd.class;
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
				addBtn.setPopupVisible(false);
			}
		});
	}

	private void addView(AbstractView view) {
		currentView.removeAllComponents();
		currentView.addComponent(view.getCompContainer());
	}

	private class NavigatorItemListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			String caption = event.getButton().getCaption();

			if (NEW_ACCOUNT_ITEM.equals(caption)) {
				eventBus.fireEvent(new AccountEvent.GotoAdd(this, null));
			} else if (ACCOUNT_LIST.equals(caption)) {
				eventBus.fireEvent(new AccountEvent.GotoList(this, null));
			} else if (NEW_CAMPAIGN_ITEM.equals(caption)) {
				eventBus.fireEvent(new CampaignEvent.GotoAdd(this, null));
			} else if (CONTACT_LIST.equals(caption)) {
				eventBus.fireEvent(new ContactEvent.GotoList(this, null));
			}
		}
	}

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout container = new VerticalLayout();
		container.setSpacing(true);
		container.setMargin(true);
		container.setWidth("100%");
		NavigatorItemListener listener = new NavigatorItemListener();

		CssLayout toolbar = new CssLayout();
		toolbar.setWidth("100%");

		Button accountList = new Button(ACCOUNT_LIST, listener);
		toolbar.addComponent(accountList);

		Button contactList = new Button(CONTACT_LIST, listener);
		toolbar.addComponent(contactList);

		Button leadList = new Button(LEAD_LIST, listener);
		toolbar.addComponent(leadList);
		toolbar.setStyleName("sidebar-menu");

		addBtn = new PopupButton("Add");
		GridLayout addBtnLayout = new GridLayout(3, 2);
		addBtnLayout.setMargin(true);
		addBtnLayout.setSpacing(true);

		Button newAccountBtn = new Button(NEW_ACCOUNT_ITEM, listener);
		newAccountBtn.setStyleName(BaseTheme.BUTTON_LINK);

		addBtnLayout.addComponent(newAccountBtn);

		Button newCampaignBtn = new Button("New Campaign", listener);
		newCampaignBtn.setStyleName(BaseTheme.BUTTON_LINK);

		addBtnLayout.addComponent(newCampaignBtn);

		Button newOpportunityBtn = new Button("New Opportunity", listener);
		newOpportunityBtn.setStyleName(BaseTheme.BUTTON_LINK);

		addBtnLayout.addComponent(newOpportunityBtn);

		Button newLeadBtn = new Button("New Lead", listener);
		newLeadBtn.setStyleName(BaseTheme.BUTTON_LINK);
		addBtnLayout.addComponent(newLeadBtn);

		addBtn.addComponent(addBtnLayout);
		addBtn.setStyleName("link");
		toolbar.addComponent(addBtn);

		container.addComponent(toolbar);

		currentView = new VerticalLayout();
		container.addComponent(currentView);
		return container;
	}

}

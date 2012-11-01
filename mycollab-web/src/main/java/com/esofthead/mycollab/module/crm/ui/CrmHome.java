package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ui.Params;
import com.esofthead.mycollab.vaadin.mvp.ui.View;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@Component
public class CrmHome extends AbstractView {
	private static final long serialVersionUID = 1L;

	private static String ACCOUNT_LIST = "Accounts";

	private static String NEW_ACCOUNT_ITEM = "New Account";

	private static String CONTACT_LIST = "Contacts";

	private static String LEAD_LIST = "Leads";

	private VerticalLayout currentView;

	private PopupButton addBtn;

	public CrmHome() {
		super();
	}

	@SuppressWarnings("serial")
	@PostConstruct
	private void init() {
		eventBus.addListener(new ApplicationEventListener<AccountEvent>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.class;
			}

			@Override
			public void handle(AccountEvent event) {
				if (AccountEvent.GOTO_ADD_VIEW.equals(event.getName())) {
					addView(AppContext.getSpringBean(AccountAddViewImpl.class),
							new Params(Params.ADD, null));
				} else if (AccountEvent.GOTO_READ_VIEW.equals(event.getName())) {
					addView(AppContext.getSpringBean(AccountAddViewImpl.class),
							new Params(Params.VIEW, new Object[] { event
									.getData() }));
				} else if (AccountEvent.GOTO_LIST_VIEW.equals(event.getName())) {
					addView(AppContext.getSpringBean(AccountListViewImpl.class),
							new Params());
				} else if (AccountEvent.GOTO_EDIT_VIEW.equals(event.getName())) {
					addView(AppContext.getSpringBean(AccountAddViewImpl.class),
							new Params(Params.EDIT, new Object[] { event
									.getData() }));
				}
				addBtn.setPopupVisible(false);
			}
		});

		eventBus.addListener(new ApplicationEventListener<ContactEvent>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ContactEvent.class;
			}

			@Override
			public void handle(ContactEvent event) {
				if (ContactEvent.GOTO_ADD_VIEW.equals(event.getName())) {
					addView(AppContext.getSpringBean(ContactAddViewImpl.class),
							new Params(Params.ADD, null));
				} else if (ContactEvent.GOTO_READ_VIEW.equals(event.getName())) {
					addView(AppContext.getSpringBean(ContactAddViewImpl.class),
							new Params(Params.VIEW, new Object[] { event
									.getData() }));
				} else if (ContactEvent.GOTO_LIST_VIEW.equals(event.getName())) {
					addView(AppContext.getSpringBean(ContactListViewImpl.class),
							new Params());
				} else if (ContactEvent.GOTO_EDIT_VIEW.equals(event.getName())) {
					addView(AppContext.getSpringBean(ContactAddViewImpl.class),
							new Params(Params.EDIT, new Object[] { event
									.getData() }));
				}
				addBtn.setPopupVisible(false);
			}
		});
	}

	private void addView(View view, Params params) {
		ComponentContainer component = view.createMainLayout();

		currentView.removeAllComponents();
		currentView.addComponent(component);

		((View) view).handleRequest(params);
	}

	private class NavigatorItemListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			String caption = event.getButton().getCaption();

			if (NEW_ACCOUNT_ITEM.equals(caption)) {
				eventBus.fireEvent(new AccountEvent(this,
						AccountEvent.GOTO_ADD_VIEW, null));
			} else if (ACCOUNT_LIST.equals(caption)) {
				eventBus.fireEvent(new AccountEvent(this,
						AccountEvent.GOTO_LIST_VIEW, null));
			} else if (CONTACT_LIST.equals(caption)) {
				eventBus.fireEvent(new ContactEvent(this,
						ContactEvent.GOTO_LIST_VIEW, null));
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

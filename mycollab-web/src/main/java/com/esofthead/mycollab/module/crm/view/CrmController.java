package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.AccountEvent.GotoRead;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.view.account.AccountAddPresenter;
import com.esofthead.mycollab.module.crm.view.account.AccountAddView;
import com.esofthead.mycollab.module.crm.view.account.AccountAddViewImpl;
import com.esofthead.mycollab.module.crm.view.account.AccountListPresenterImpl;
import com.esofthead.mycollab.module.crm.view.account.AccountListView;
import com.esofthead.mycollab.module.crm.view.account.AccountListViewImpl;
import com.esofthead.mycollab.module.crm.view.account.AccountReadPresenter;
import com.esofthead.mycollab.module.crm.view.account.AccountReadView;
import com.esofthead.mycollab.module.crm.view.account.AccountReadViewImpl;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignAddPresenter;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignAddView;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignAddViewImpl;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignListPresenterImpl;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignListView;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignListViewImpl;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignReadPresenter;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignReadView;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignReadViewImpl;
import com.esofthead.mycollab.module.crm.view.contact.ContactAddPresenter;
import com.esofthead.mycollab.module.crm.view.contact.ContactAddView;
import com.esofthead.mycollab.module.crm.view.contact.ContactAddViewImpl;
import com.esofthead.mycollab.module.crm.view.contact.ContactListPresenterImpl;
import com.esofthead.mycollab.module.crm.view.contact.ContactListView;
import com.esofthead.mycollab.module.crm.view.contact.ContactListViewImpl;
import com.esofthead.mycollab.module.crm.view.contact.ContactReadPresenter;
import com.esofthead.mycollab.module.crm.view.contact.ContactReadView;
import com.esofthead.mycollab.module.crm.view.contact.ContactReadViewImpl;
import com.esofthead.mycollab.module.crm.view.lead.LeadAddPresenter;
import com.esofthead.mycollab.module.crm.view.lead.LeadAddView;
import com.esofthead.mycollab.module.crm.view.lead.LeadAddViewImpl;
import com.esofthead.mycollab.module.crm.view.lead.LeadListPresenterImpl;
import com.esofthead.mycollab.module.crm.view.lead.LeadListView;
import com.esofthead.mycollab.module.crm.view.lead.LeadListViewImpl;
import com.esofthead.mycollab.module.crm.view.lead.LeadReadPresenter;
import com.esofthead.mycollab.module.crm.view.lead.LeadReadView;
import com.esofthead.mycollab.module.crm.view.lead.LeadReadViewImpl;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityAddPresenter;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityAddView;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityAddViewImpl;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityListPresenterImpl;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityListView;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityListViewImpl;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityReadPresenter;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityReadView;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityReadViewImpl;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;

public class CrmController {
	private CrmContainer container;

	public CrmController(CrmContainer container) {
		this.container = container;

		bindAccountEvents();
		bindCampaignEvents();
		bindContactEvents();
		bindLeadEvents();
		bindOpportunityEvents();
	}

	@SuppressWarnings("serial")
	private void bindAccountEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<AccountEvent.GotoList>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return AccountEvent.GotoList.class;
					}

					@Override
					public void handle(AccountEvent.GotoList event) {
						AccountListView view = ViewManager
								.getView(AccountListViewImpl.class);
						AccountListPresenterImpl presenter = new AccountListPresenterImpl(
								view);
						presenter.go(container);
						presenter.doDefaultSearch();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<AccountEvent.GotoAdd>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return AccountEvent.GotoAdd.class;
					}

					@Override
					public void handle(AccountEvent.GotoAdd event) {
						AccountAddView view = ViewManager
								.getView(AccountAddViewImpl.class);
						new AccountAddPresenter(view).go(container);
						view.addNewItem();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<AccountEvent.GotoEdit>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return AccountEvent.GotoEdit.class;
					}

					@Override
					public void handle(AccountEvent.GotoEdit event) {
						AccountAddView view = ViewManager
								.getView(AccountAddViewImpl.class);
						new AccountAddPresenter(view).go(container);
						view.editItem((Account) event.getData());
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<AccountEvent.GotoRead>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return AccountEvent.GotoRead.class;
					}

					@Override
					public void handle(GotoRead event) {
						AccountReadView view = ViewManager
								.getView(AccountReadViewImpl.class);
						Account account = (Account) event.getData();
						new AccountReadPresenter(view).go(container);
						view.displayItem(account);
					}
				});
	}

	@SuppressWarnings("serial")
	private void bindCampaignEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<CampaignEvent.GotoList>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return CampaignEvent.GotoList.class;
					}

					@Override
					public void handle(CampaignEvent.GotoList event) {
						CampaignListView view = ViewManager
								.getView(CampaignListViewImpl.class);
						CampaignListPresenterImpl presenter = new CampaignListPresenterImpl(
								view);
						presenter.go(container);
						presenter.doDefaultSearch();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<CampaignEvent.GotoAdd>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return CampaignEvent.GotoAdd.class;
					}

					@Override
					public void handle(CampaignEvent.GotoAdd event) {
						CampaignAddView view = ViewManager
								.getView(CampaignAddViewImpl.class);
						new CampaignAddPresenter(view).go(container);
						view.addNewItem();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<CampaignEvent.GotoEdit>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return CampaignEvent.GotoEdit.class;
					}

					@Override
					public void handle(CampaignEvent.GotoEdit event) {
						CampaignAddView view = ViewManager
								.getView(CampaignAddViewImpl.class);
						new CampaignAddPresenter(view).go(container);
						view.editItem((Campaign) event.getData());
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<CampaignEvent.GotoRead>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return CampaignEvent.GotoRead.class;
					}

					@Override
					public void handle(CampaignEvent.GotoRead event) {
						CampaignReadView view = ViewManager
								.getView(CampaignReadViewImpl.class);
						Campaign campaign = (Campaign) event.getData();
						new CampaignReadPresenter(view).go(container);
						view.displayItem(campaign);
					}
				});
	}

	@SuppressWarnings("serial")
	private void bindContactEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ContactEvent.GotoList>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ContactEvent.GotoList.class;
					}

					@Override
					public void handle(ContactEvent.GotoList event) {
						ContactListView view = ViewManager
								.getView(ContactListViewImpl.class);
						ContactListPresenterImpl presenter = new ContactListPresenterImpl(
								view);
						presenter.go(container);
						presenter.doDefaultSearch();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ContactEvent.GotoAdd>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ContactEvent.GotoAdd.class;
					}

					@Override
					public void handle(ContactEvent.GotoAdd event) {
						ContactAddView view = ViewManager
								.getView(ContactAddViewImpl.class);
						new ContactAddPresenter(view).go(container);
						view.addNewItem();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ContactEvent.GotoEdit>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ContactEvent.GotoEdit.class;
					}

					@Override
					public void handle(ContactEvent.GotoEdit event) {
						ContactAddView view = ViewManager
								.getView(ContactAddViewImpl.class);
						new ContactAddPresenter(view).go(container);
						view.editItem((Contact) event.getData());
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ContactEvent.GotoRead>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ContactEvent.GotoRead.class;
					}

					@Override
					public void handle(ContactEvent.GotoRead event) {
						ContactReadView view = ViewManager
								.getView(ContactReadViewImpl.class);
						SimpleContact campaign = (SimpleContact) event
								.getData();
						new ContactReadPresenter(view).go(container);
						view.displayItem(campaign);
					}
				});
	}

	@SuppressWarnings("serial")
	private void bindLeadEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<LeadEvent.GotoList>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return LeadEvent.GotoList.class;
					}

					@Override
					public void handle(LeadEvent.GotoList event) {
						LeadListView view = ViewManager
								.getView(LeadListViewImpl.class);
						LeadListPresenterImpl presenter = new LeadListPresenterImpl(
								view);
						presenter.go(container);
						presenter.doDefaultSearch();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<LeadEvent.GotoAdd>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return LeadEvent.GotoAdd.class;
					}

					@Override
					public void handle(LeadEvent.GotoAdd event) {
						LeadAddView view = ViewManager
								.getView(LeadAddViewImpl.class);
						new LeadAddPresenter(view).go(container);
						view.addNewItem();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<LeadEvent.GotoEdit>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return LeadEvent.GotoEdit.class;
					}

					@Override
					public void handle(LeadEvent.GotoEdit event) {
						LeadAddView view = ViewManager
								.getView(LeadAddViewImpl.class);
						new LeadAddPresenter(view).go(container);
						view.editItem((Lead) event.getData());
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<LeadEvent.GotoRead>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return LeadEvent.GotoRead.class;
					}

					@Override
					public void handle(LeadEvent.GotoRead event) {
						LeadReadView view = ViewManager
								.getView(LeadReadViewImpl.class);
						SimpleLead lead = (SimpleLead) event.getData();
						new LeadReadPresenter(view).go(container);
						view.displayItem(lead);
					}
				});
	}

	@SuppressWarnings("serial")
	private void bindOpportunityEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<OpportunityEvent.GotoList>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return OpportunityEvent.GotoList.class;
					}

					@Override
					public void handle(OpportunityEvent.GotoList event) {
						OpportunityListView view = ViewManager
								.getView(OpportunityListViewImpl.class);
						OpportunityListPresenterImpl presenter = new OpportunityListPresenterImpl(
								view);
						presenter.go(container);
						presenter.doDefaultSearch();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<OpportunityEvent.GotoAdd>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return OpportunityEvent.GotoAdd.class;
					}

					@Override
					public void handle(OpportunityEvent.GotoAdd event) {
						OpportunityAddView view = ViewManager
								.getView(OpportunityAddViewImpl.class);
						new OpportunityAddPresenter(view).go(container);
						view.addNewItem();
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<OpportunityEvent.GotoEdit>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return OpportunityEvent.GotoEdit.class;
					}

					@Override
					public void handle(OpportunityEvent.GotoEdit event) {
						OpportunityAddView view = ViewManager
								.getView(OpportunityAddViewImpl.class);
						new OpportunityAddPresenter(view).go(container);
						view.editItem((Opportunity) event.getData());
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<OpportunityEvent.GotoRead>() {

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return OpportunityEvent.GotoRead.class;
					}

					@Override
					public void handle(OpportunityEvent.GotoRead event) {
						OpportunityReadView view = ViewManager
								.getView(OpportunityReadViewImpl.class);
						Opportunity item = (Opportunity) event.getData();
						new OpportunityReadPresenter(view).go(container);
						view.displayItem(item);
					}
				});
	}
}

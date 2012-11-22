package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.AccountEvent.GotoAdd;
import com.esofthead.mycollab.module.crm.events.AccountEvent.GotoList;
import com.esofthead.mycollab.module.crm.events.AccountEvent.GotoRead;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
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
					public void handle(GotoList event) {
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
					public void handle(GotoAdd event) {
						AccountAddView view = ViewManager
								.getView(AccountAddViewImpl.class);
						new AccountAddPresenter(view).go(container);
						view.addNewItem();
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
}

package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.AccountEvent.GotoAdd;
import com.esofthead.mycollab.module.crm.events.AccountEvent.GotoList;
import com.esofthead.mycollab.module.crm.events.AccountEvent.GotoRead;
import com.esofthead.mycollab.module.crm.view.account.AccountAddPresenter;
import com.esofthead.mycollab.module.crm.view.account.AccountAddView;
import com.esofthead.mycollab.module.crm.view.account.AccountAddViewImpl;
import com.esofthead.mycollab.module.crm.view.account.AccountListPresenterImpl;
import com.esofthead.mycollab.module.crm.view.account.AccountListView;
import com.esofthead.mycollab.module.crm.view.account.AccountListViewImpl;
import com.esofthead.mycollab.module.crm.view.account.AccountReadPresenter;
import com.esofthead.mycollab.module.crm.view.account.AccountReadView;
import com.esofthead.mycollab.module.crm.view.account.AccountReadViewImpl;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;

public class CrmController {
	private CrmContainer container;
	
	public CrmController(CrmContainer container) {
		this.container = container;
		
		bind();
	}
	
	private void bind() {
		EventBus.getInstance().addListener(new ApplicationEventListener<AccountEvent.GotoList>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.GotoList.class;
			}

			@Override
			public void handle(GotoList event) {
				AccountListView view = ViewManager.getView(AccountListViewImpl.class);
				AccountListPresenterImpl presenter = new AccountListPresenterImpl(view);
				presenter.go(container);
				presenter.doDefaultSearch();
			}
		});
		
		EventBus.getInstance().addListener(new ApplicationEventListener<AccountEvent.GotoAdd>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.GotoAdd.class;
			}

			@Override
			public void handle(GotoAdd event) {
				AccountAddView view = ViewManager.getView(AccountAddViewImpl.class);
				new AccountAddPresenter(view).go(container);
				view.addNewItem();
			}
		});
		
		EventBus.getInstance().addListener(new ApplicationEventListener<AccountEvent.GotoRead>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.GotoRead.class;
			}

			@Override
			public void handle(GotoRead event) {
				AccountReadView view = ViewManager.getView(AccountReadViewImpl.class);
				Account account = (Account)event.getData();
				new AccountReadPresenter(view).go(container);
				view.displayItem(account);
			}
		});
	}
}

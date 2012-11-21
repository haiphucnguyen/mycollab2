package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.FormEvent.Cancel;
import com.esofthead.mycollab.vaadin.events.FormEvent.Save;
import com.esofthead.mycollab.web.AppContext;

public class AccountAddPresenter extends CrmGenericPresenter<AccountAddView>{

	public AccountAddPresenter(AccountAddView view) {
		this.view = view;
		bind();
	}
	
	private void bind() {
		view.getFormHandlers().addFormHandler(new EditFormHandler() {
			
			@Override
			public void onSave(Save event) {
				 Account account = (Account) event.getData();
				 saveAccount(account);
				 EventBus.getInstance().fireEvent(new AccountEvent.GotoList(this, null));
			}
			
			@Override
			public void onCancel(Cancel event) {
				EventBus.getInstance().fireEvent(new AccountEvent.GotoList(this, null));
			}
		});
	}

	public void saveAccount(Account account) {
		AccountService accountService = AppContext.getSpringBean(AccountService.class);
		
		account.setSaccountid(AppContext.getAccountId());
		if (account.getId() == null) {
			accountService.saveWithSession(account, AppContext.getUsername());
		} else {
			accountService.updateWithSession(account, AppContext.getUsername());
		}

	}
}

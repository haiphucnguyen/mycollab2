package com.esofthead.mycollab.module.crm.presenter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.web.AppContext;


public class AccountAddPresenter {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountService accountService;

	@PostConstruct
	private void initListeners() {
//		eventBus.addListener(new ApplicationEventListener<AccountEvent.Save>() {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return AccountEvent.Save.class;
//			}
//
//			@Override
//			public void handle(AccountEvent.Save event) {
//				Account account = (Account) event.getData();
//				saveAccount(account);
//			}
//		});
	}

	public void saveAccount(Account account) {
		account.setSaccountid(AppContext.getAccountId());
		if (account.getId() == null) {
			accountService.saveWithSession(account, AppContext.getUsername());
		} else {
			accountService.updateWithSession(account, AppContext.getUsername());
		}

		
	}
}

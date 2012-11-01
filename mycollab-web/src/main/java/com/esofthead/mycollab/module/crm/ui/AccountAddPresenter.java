package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter.ViewInterface;
import com.esofthead.mycollab.web.AppContext;

@Component
@ViewInterface(AccountAddView.class)
public class AccountAddPresenter extends AbstractPresenter<AccountAddView> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountService accountService;

	@PostConstruct
	private void initListeners() {
		eventBus.addListener(new ApplicationEventListener<AccountEvent>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.class;
			}

			@Override
			public void handle(AccountEvent event) {
				if (event.getName().equals(AccountEvent.SAVE)) {
					Account account = (Account) event.getData();
					saveAccount(account);
				}
			}
		});
	}

	public void saveAccount(Account account) {
		account.setSaccountid(AppContext.getAccountId());
		if (account.getId() == null) {
			accountService.saveWithSession(account, AppContext.getUsername());
		} else {
			accountService.updateWithSession(account, AppContext.getUsername());
		}

		eventBus.fireEvent(new AccountEvent(this, AccountEvent.GOTO_LIST_VIEW));
	}
}

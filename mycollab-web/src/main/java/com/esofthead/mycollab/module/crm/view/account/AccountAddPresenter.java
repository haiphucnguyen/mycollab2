package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class AccountAddPresenter extends CrmGenericPresenter<AccountAddView> {

	public AccountAddPresenter(AccountAddView view) {
		this.view = view;
		bind();
	}

	@SuppressWarnings("unchecked")
	private void bind() {
		view.getFormHandlers().addFormHandler(new EditFormHandler<Account>() {

			@Override
			public void onSave(final Account account) {
				saveAccount(account);
				EventBus.getInstance().fireEvent(
						new AccountEvent.GotoList(this, null));
			}

			@Override
			public void onCancel() {
				EventBus.getInstance().fireEvent(
						new AccountEvent.GotoList(this, null));
			}

			@Override
			public void onSaveAndNew(final Account account) {
				saveAccount(account);
				EventBus.getInstance().fireEvent(
						new AccountEvent.GotoAdd(this, null));
			}
		});
	}

	public void saveAccount(Account account) {
		AccountService accountService = AppContext
				.getSpringBean(AccountService.class);

		account.setSaccountid(AppContext.getAccountId());
		if (account.getId() == null) {
			accountService.saveWithSession(account, AppContext.getUsername());
		} else {
			accountService.updateWithSession(account, AppContext.getUsername());
		}

	}
}

package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.web.AppContext;

public class AccountReadPresenter extends CrmGenericPresenter<AccountReadView> {

	public AccountReadPresenter(AccountReadView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<Account>() {

					@Override
					public void onEdit(Account data) {
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(Account data) {
						AccountService accountService = AppContext
								.getSpringBean(AccountService.class);
						accountService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoList(this, null));
					}

					@Override
					public void onClone(Account data) {
						Account cloneData = (Account) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoList(this, null));
					}
				});
	}
}

package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;

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
						// TODO Auto-generated method stub

					}

					@Override
					public void onDelete(Account data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onClone(Account data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub

					}
				});
	}
}

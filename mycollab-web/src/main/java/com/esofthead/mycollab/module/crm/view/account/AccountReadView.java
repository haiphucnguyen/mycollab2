package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface AccountReadView extends View {
	HasPreviewFormHandlers<Account> getPreviewFormHandlers();

	void displayItem(Account account);
}

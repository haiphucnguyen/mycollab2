package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.vaadin.events.HasFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.TemplateItemView;

public interface AccountAddView extends TemplateItemView<Account> {
	HasFormHandlers getFormHandler();
}

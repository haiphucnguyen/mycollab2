package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.TemplateEditItemView;

public interface AccountAddView extends TemplateEditItemView<Account> {
	HasEditFormHandlers<Account> getFormHandlers();
}

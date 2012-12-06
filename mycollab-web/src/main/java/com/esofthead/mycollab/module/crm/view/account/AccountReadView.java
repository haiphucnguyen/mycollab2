package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface AccountReadView extends IPreviewView<SimpleAccount> {
	HasPreviewFormHandlers<Account> getPreviewFormHandlers();

	IRelatedListHandlers getRelatedContactHandlers();

	IRelatedListHandlers getRelatedOpportunityHandlers();

	IRelatedListHandlers getRelatedLeadHandlers();
}

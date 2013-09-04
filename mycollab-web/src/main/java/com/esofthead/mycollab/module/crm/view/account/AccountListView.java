package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

public interface AccountListView extends
		ListView<AccountSearchCriteria, SimpleAccount> {
	public static final String VIEW_DEF_ID = "crm-account-list";

}

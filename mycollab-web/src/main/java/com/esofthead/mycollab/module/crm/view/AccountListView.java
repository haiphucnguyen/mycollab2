package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.mvp.TemplateSearchableView;

public interface AccountListView extends
		TemplateSearchableView<AccountSearchCriteria> {

	HasSearchHandlers<AccountSearchCriteria> getSearchPanel();

	interface AccountListPresenter {
		void onItemSelect(SimpleAccount account);

		void doDefaultSearch();

		void doSearch(AccountSearchCriteria searchCriteria);
	}
}

package com.esofthead.mycollab.module.crm.view;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.TemplateSearchableView;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface AccountListView extends
		TemplateSearchableView<AccountSearchCriteria>, View {

	void displayAccounts(List<SimpleAccount> accounts);

	HasSearchHandlers<AccountSearchCriteria> getSearchPanel();

	interface AccountListPresenter extends Presenter {
		void onItemSelect(SimpleAccount account);

		void doDefaultSearch();

		void doSearch(AccountSearchCriteria searchCriteria);
	}
}

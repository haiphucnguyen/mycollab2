package com.esofthead.mycollab.module.crm.view.account;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface AccountListView extends View {

	void displayAccounts(List<SimpleAccount> accounts, int currentPage,
			int totalPages);

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();

	void setPresenter(AccountListPresenter presenter);

	HasSearchHandlers<AccountSearchCriteria> getSearchHandlers();

	HasPagableHandlers getPagableHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();

	interface AccountListPresenter extends Presenter {
		void onItemSelect(SimpleAccount account);
		
		void doDefaultSearch();

		void doSearch(AccountSearchCriteria searchCriteria);
	}
}

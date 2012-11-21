package com.esofthead.mycollab.module.crm.view;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.TemplateSearchableView;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl.PopupButtonControlListener;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton.SelectionOptionListener;

public interface AccountListView extends
		TemplateSearchableView<AccountSearchCriteria>, View {

	void displayAccounts(List<SimpleAccount> accounts, int currentPage,
			int totalPages);

	void enableActionControls();

	void disableActionControls();

	void setPresenter(AccountListPresenter presenter);

	HasSearchHandlers<AccountSearchCriteria> getSearchPanel();

	interface AccountListPresenter extends Presenter, SelectionOptionListener,
			PopupButtonControlListener {
		void onItemSelect(SimpleAccount account);

		void doDefaultSearch();

		void doSearch(AccountSearchCriteria searchCriteria);
	}
}

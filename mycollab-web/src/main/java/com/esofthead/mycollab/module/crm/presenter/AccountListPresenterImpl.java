package com.esofthead.mycollab.module.crm.presenter;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.SelectionModel;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.view.AccountListView.AccountListPresenter;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton.SelectionOptionListener;
import com.esofthead.mycollab.web.AppContext;

public class AccountListPresenterImpl  implements SelectionOptionListener,
		AccountListPresenter {
	private static final long serialVersionUID = 1L;

	private SearchRequest<AccountSearchCriteria> searchRequest;
	
	private List<SimpleAccount> currentListData = new ArrayList<SimpleAccount>();
	
	private SelectionModel<SimpleAccount> selectionModel = new SelectionModel<SimpleAccount>();
	
	public AccountListPresenterImpl() {
		
	}

	@Override
	public void onSelect() {
		selectionModel.addSelections(currentListData);

	}

	@Override
	public void onDeSelect() {
		selectionModel.removeAll();
	}

	@Override
	public void onItemSelect(SimpleAccount account) {
		if (selectionModel.isSelected(account)) {
			selectionModel.removeSelection(account);
		} else {
			selectionModel.addSelection(account);
		}

		checkWhetherEnableTableActionControl();
	}

	private void checkWhetherEnableTableActionControl() {

	}

	@Override
	public void doDefaultSearch() {
		AccountSearchCriteria accountSearchCriteria = new AccountSearchCriteria();
		accountSearchCriteria.setSaccountid(new NumberSearchField(
				SearchField.AND, AppContext.getAccountId()));
		doSearch(accountSearchCriteria);
	}

	@Override
	public void doSearch(AccountSearchCriteria searchCriteria) {
		this.searchRequest = new SearchRequest<AccountSearchCriteria>(
				searchCriteria, 0, SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);

	}

}

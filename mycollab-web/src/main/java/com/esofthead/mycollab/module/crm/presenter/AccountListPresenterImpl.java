package com.esofthead.mycollab.module.crm.presenter;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.SelectionModel;
import com.esofthead.mycollab.module.crm.CrmContainer;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.AccountListView;
import com.esofthead.mycollab.module.crm.view.AccountListView.AccountListPresenter;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton.SelectionOptionListener;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class AccountListPresenterImpl implements SelectionOptionListener,
		AccountListPresenter {

	private AccountService accountService;

	private SearchRequest<AccountSearchCriteria> searchRequest;

	private List<SimpleAccount> currentListData = new ArrayList<SimpleAccount>();

	private SelectionModel<SimpleAccount> selectionModel = new SelectionModel<SimpleAccount>();

	private AccountListView view;

	public AccountListPresenterImpl(AccountListView view) {
		this.view = view;
		accountService = AppContext.getSpringBean(AccountService.class);
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
		List<SimpleAccount> accounts = accountService
				.findPagableListByCriteria(searchRequest);
		System.out.println("Display accounts: " + accounts.size());
		view.displayAccounts(accounts);
	}

	@Override
	public void go(ComponentContainer container) {
		CrmContainer crmContainer = (CrmContainer) container;
		crmContainer.addView(view);
		doDefaultSearch();
	}

}

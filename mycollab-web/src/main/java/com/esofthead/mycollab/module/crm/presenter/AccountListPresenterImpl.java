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
import com.esofthead.mycollab.vaadin.events.SearchEvent;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

public class AccountListPresenterImpl implements AccountListPresenter {

	private AccountService accountService;

	private SearchRequest<AccountSearchCriteria> searchRequest;

	private List<SimpleAccount> currentListData = new ArrayList<SimpleAccount>();

	private SelectionModel<SimpleAccount> selectionModel = new SelectionModel<SimpleAccount>();

	private AccountListView view;

	public AccountListPresenterImpl(AccountListView view) {
		this.view = view;
		view.setPresenter(this);
		accountService = AppContext.getSpringBean(AccountService.class);

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<AccountSearchCriteria>() {

					@Override
					public void onSearch(
							SearchEvent<AccountSearchCriteria> event) {
						AccountSearchCriteria criteria = event.getCriteria();
						searchRequest = new SearchRequest<AccountSearchCriteria>(
								criteria, 0,
								SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);
						doSearch();
					}
				});
	}

	@Override
	public void onSelect() {
		selectionModel.addSelections(currentListData);

		for (SimpleAccount account : selectionModel) {
			CheckBox checkBox = (CheckBox) account.getExtraData();
			checkBox.setValue(true);
		}

		view.enableActionControls();
	}

	@Override
	public void onDeSelect() {
		selectionModel.removeAll();
		for (SimpleAccount account : currentListData) {
			CheckBox checkBox = (CheckBox) account.getExtraData();
			checkBox.setValue(false);
		}

		view.disableActionControls();
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
		if (selectionModel.size() > 0) {
			view.enableActionControls();
		} else {
			view.disableActionControls();
		}
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
		doSearch();
	}

	@SuppressWarnings("unchecked")
	private void doSearch() {
		int totalCount = accountService.getTotalCount(searchRequest
				.getSearchCriteria());

		currentListData = accountService
				.findPagableListByCriteria(searchRequest);
		view.displayAccounts(currentListData, 1, totalCount
				/ SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS + 1);
		checkWhetherEnableTableActionControl();
	}

	@Override
	public void go(ComponentContainer container) {
		CrmContainer crmContainer = (CrmContainer) container;
		crmContainer.addView(view);
		doDefaultSearch();
	}

	@Override
	public void onSelect(String id, String caption) {
		if ("delete".equals(id)) {
			deleteSelectedItems();
		}
	}

	private void deleteSelectedItems() {
		List<Integer> keyList = new ArrayList<Integer>();
		for (SimpleAccount account : selectionModel) {
			keyList.add(account.getId());
		}

		if (keyList.size() > 0) {
			accountService.removeWithSession(keyList, AppContext.getUsername());
		}
	}

}

package com.esofthead.mycollab.module.crm.view.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.account.AccountListView.AccountListPresenter;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

public class AccountListPresenterImpl extends
		CrmGenericPresenter<AccountListView> implements AccountListPresenter {

	private AccountService accountService;

	private AccountSearchCriteria searchCriteria;

	private boolean isSelectAll = false;

	public AccountListPresenterImpl(final AccountListView view) {
		this.view = view;
		accountService = AppContext.getSpringBean(AccountService.class);

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<AccountSearchCriteria>() {

					@Override
					public void onSearch(AccountSearchCriteria criteria) {
						doSearch(criteria);
					}
				});

		view.getOptionSelectionHandlers().addSelectionOptionHandler(
				new SelectionOptionHandler() {

					@Override
					public void onSelectCurrentPage() {
						Collection<SimpleAccount> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (SimpleAccount account : currentDataList) {
							account.setSelected(true);
							CheckBox checkBox = (CheckBox) account
									.getExtraData();
							checkBox.setValue(true);
						}

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						Collection<SimpleAccount> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (SimpleAccount account : currentDataList) {
							account.setSelected(false);
							CheckBox checkBox = (CheckBox) account
									.getExtraData();
							checkBox.setValue(false);
						}

						checkWhetherEnableTableActionControl();

					}

					@Override
					public void onSelectAll() {
						isSelectAll = true;
						Collection<SimpleAccount> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						for (SimpleAccount account : currentDataList) {
							account.setSelected(true);
							CheckBox checkBox = (CheckBox) account
									.getExtraData();
							checkBox.setValue(true);
						}
					}
				});

		view.getPopupActionHandlers().addPopupActionHandler(
				new PopupActionHandler() {

					@Override
					public void onSelect(String id, String caption) {
						if ("delete".equals(id)) {
							deleteSelectedItems();
						}
					}
				});

		view.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<SimpleAccount>() {

					@Override
					public void onSelect(SimpleAccount item) {
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<SimpleAccount> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (SimpleAccount account : currentDataList) {
			if (account.isSelected()) {
				countItems++;
			}
		}
		if (countItems > 0) {
			view.enableActionControls(countItems);
		} else {
			view.disableActionControls();
		}
	}

	@Override
	public void doDefaultSearch() {
		AccountSearchCriteria criteria = new AccountSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		doSearch(criteria);
	}

	@Override
	public void doSearch(AccountSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	private void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleAccount> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleAccount account : currentDataList) {
				keyList.add(account.getId());
			}

			if (keyList.size() > 0) {
				accountService.removeWithSession(keyList, AppContext.getUsername());
				doSearch(searchCriteria);
			}
		} else {
			
		}
		
	}

}

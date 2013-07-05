package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;

public interface AccountListView extends View {
	public static final String VIEW_DEF_ID = "crm-account-list";

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();

	HasSearchHandlers<AccountSearchCriteria> getSearchHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();

	HasSelectableItemHandlers<SimpleAccount> getSelectableItemHandlers();

	IPagedBeanTable<AccountSearchCriteria, SimpleAccount> getPagedBeanTable();
}

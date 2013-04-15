package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AccountSelectionWindow extends Window {

	private static final long serialVersionUID = 1L;
	private AccountSearchCriteria searchCriteria;
	private AccountTableDisplay tableItem;
	private FieldSelection fieldSelection;

	public AccountSelectionWindow(FieldSelection fieldSelection) {
		super("Account Name Lookup");
		this.setWidth("600px");
		this.fieldSelection = fieldSelection;
		this.setModal(true);
	}

	public void show() {
		searchCriteria = new AccountSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		createAccountList();

		AccountSimpleSearchPanel accountSimpleSearchPanel = new AccountSimpleSearchPanel();
		accountSimpleSearchPanel
				.addSearchHandler(new SearchHandler<AccountSearchCriteria>() {

					@Override
					public void onSearch(AccountSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}

				});
		layout.addComponent(accountSimpleSearchPanel);
		layout.addComponent(tableItem);
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	@SuppressWarnings("serial")
	private void createAccountList() {
		tableItem = new AccountTableDisplay(
				new String[] { "accountname", "city", "assignuser" },
				new String[] {
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_CITY_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_ASSIGNED_USER_HEADER) });
		tableItem.setWidth("100%");
		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleAccount account = (SimpleAccount) event.getData();
						if ("accountname".equals(event.getFieldName())) {
							fieldSelection.fireValueChange(account);
							AccountSelectionWindow.this.getParent()
									.removeWindow(AccountSelectionWindow.this);
						}
					}
				});
	}
}

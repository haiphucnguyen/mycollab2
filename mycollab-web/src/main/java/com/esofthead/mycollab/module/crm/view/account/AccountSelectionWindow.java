package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AccountSelectionWindow extends Window {
	private static final long serialVersionUID = 1L;

	private AccountSearchCriteria searchCriteria;

	private PagedBeanTable2<AccountService, AccountSearchCriteria, SimpleAccount> tableItem;

	private FieldSelection fieldSelection;

	public AccountSelectionWindow(FieldSelection fieldSelection) {
		super("Account Name Lookup");
		this.setWidth("600px");

		this.fieldSelection = fieldSelection;
	}

	public void show() {
		searchCriteria = new AccountSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		createAccountList();

		layout.addComponent(createSearchPanel());
		layout.addComponent(tableItem);
		layout.addComponent(tableItem.createControls());
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	private ComponentContainer createSearchPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		TextField valueField = new TextField();
		layout.addComponent(valueField);

		ValueComboBox group = new ValueComboBox(false, new String[] {
				"Organization Name", "Billing City", "Webiste", "Phone",
				"Assigned To" });
		layout.addComponent(group);

		Button searchBtn = new Button("Search");
		layout.addComponent(searchBtn);
		return layout;
	}

	private void createAccountList() {
		tableItem = new PagedBeanTable2<AccountService, AccountSearchCriteria, SimpleAccount>(
				AppContext.getSpringBean(AccountService.class),
				SimpleAccount.class, new String[] { "accountname", "city",
						"assignuser" }, new String[] { "Name", "City",
						"Assign User" });
		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("accountname", 1.0f);
		tableItem.setColumnWidth("city", 150);
		tableItem.setColumnWidth("assignuser", 150);

		tableItem.addGeneratedColumn("accountname", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleAccount account = ((PagedBeanTable2<AccountService, AccountSearchCriteria, SimpleAccount>) source)
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(account.getAccountname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								fieldSelection.fireValueChange(account);
								AccountSelectionWindow.this.getParent()
										.removeWindow(
												AccountSelectionWindow.this);
							}
						});
				return b;
			}
		});
	}

}

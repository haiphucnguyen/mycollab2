package com.esofthead.mycollab.module.crm.ui.components;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryContainer;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryDefinition;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryFactory;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
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

@Scope("prototype")
@Component
public class AccountSelectionWindow extends Window {
	private static final long serialVersionUID = 1L;

	private AccountSearchCriteria searchCriteria;

	private BeanTable<SimpleAccount> tableItem;

	private FieldSelection fieldSelection;

	public AccountSelectionWindow(FieldSelection fieldSelection) {
		super("Account Name Lookup");

		this.fieldSelection = fieldSelection;
	}

	@PostConstruct
	private void init() {
	}

	public void show() {
		searchCriteria = new AccountSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		layout.addComponent(createSearchPanel());
		layout.addComponent(createAccountList());
		this.setContent(layout);
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

	private com.vaadin.ui.Component createAccountList() {
		tableItem = new BeanTable<SimpleAccount>();
		tableItem.addStyleName("striped");
		tableItem.setWidth("100%");
		tableItem.setHeight("200px");

		MyBatisQueryContainer<SimpleAccount> container = new MyBatisQueryContainer<SimpleAccount>(
				new MyBatisQueryDefinition<AccountSearchCriteria>(
						AppContext.getSpringBean(AccountService.class), false,
						5), new MyBatisQueryFactory<AccountSearchCriteria>(
						searchCriteria));

		container.addContainerProperty("accountname", String.class, "", true,
				true);
		tableItem.setColumnWidth("accountname", 250);

		container.addContainerProperty("city", String.class, "", true, true);
		tableItem.setColumnWidth("city", 150);

		container.addContainerProperty("assignuser", String.class, "", true,
				true);
		tableItem.setColumnWidth("assignuser", 150);

		tableItem.setContainerDataSource(container);

		tableItem.addGeneratedColumn("accountname", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				Button b = new Button(account.getAccountname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
										.getBeanByIndex(itemId);
								fieldSelection.fireValueChange(account);
								AccountSelectionWindow.this.getParent()
										.removeWindow(
												AccountSelectionWindow.this);
							}
						});
				b.setStyleName("link");
				return b;
			}
		});

		tableItem
				.setColumnHeaders(new String[] { "Name", "City", "Assign User" });

		return tableItem;
	}

}

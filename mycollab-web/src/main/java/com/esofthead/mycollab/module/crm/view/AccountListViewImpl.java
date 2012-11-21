package com.esofthead.mycollab.module.crm.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.AccountSearchPanel;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public class AccountListViewImpl extends AbstractView implements
		AccountListView {
	private static final long serialVersionUID = 1L;

	private AccountSearchPanel accountSearchPanel;

	private PagedBeanTable<SimpleAccount> tableItem;

	private AccountSearchCriteria searchCriteria;

	private VerticalLayout accountListLayout;

	private PopupButtonControl tableActionControls;

	private Label selectedItemsNumberLabel = new Label();

	private AccountListPresenter presenter;

	public AccountListViewImpl() {
		this.setSpacing(true);

		accountSearchPanel = new AccountSearchPanel();
		this.addComponent(accountSearchPanel);

		accountListLayout = new VerticalLayout();
		accountListLayout.setSpacing(true);
		this.addComponent(accountListLayout);
	}

	@Override
	public void displayAccounts(List<SimpleAccount> accounts, int currentPage,
			int totalPages) {
		tableItem = new PagedBeanTable<SimpleAccount>();
		tableItem.setCurrentPage(currentPage);
		tableItem.setTotalPage(totalPages);

		BeanItemContainer<SimpleAccount> container = new BeanItemContainer<SimpleAccount>(
				SimpleAccount.class, accounts);
		tableItem.setContainerDataSource(container);
		tableItem.setVisibleColumns(new String[] { "selected", "accountname",
				"city", "billingCountry", "phoneoffice", "email", "assignuser",
				"createdtime" });
		tableItem.setColumnHeaders(new String[] { "", "Name", "City",
				"Billing Country", "Phone Office", "Email Address",
				"Assign User", "Created Time" });

		tableItem.setWidth("1130px");

		tableItem.setColumnWidth("selected", 20);
		tableItem.setColumnWidth("city", 130);

		tableItem.setColumnWidth("billingCountry", 130);

		tableItem.setColumnWidth("phoneoffice", 90);
		tableItem.setColumnWidth("email", 180);

		tableItem.setColumnWidth("assignuser", 140);
		tableItem.setColumnWidth("createdtime", 120);

		tableItem.addGeneratedColumn("selected", new ColumnGenerator() {

			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						SimpleAccount account = ((PagedBeanTable<SimpleAccount>) source)
								.getBeanByIndex(itemId);
						presenter.onItemSelect(account);

					}
				});

				@SuppressWarnings("unchecked")
				SimpleAccount account = ((PagedBeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				account.setExtraData(cb);
				return cb;
			}
		});

		tableItem.addGeneratedColumn("email", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			@SuppressWarnings("unchecked")
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				SimpleAccount account = ((PagedBeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				if (account != null) {
					Link l = new Link();
					l.setResource(new ExternalResource("mailto:"
							+ account.getEmail()));
					l.setCaption(account.getEmail());
					return l;
				} else {
					return new Label("");
				}

			}
		});

		tableItem.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleAccount account = ((PagedBeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				if (account != null) {
					Label l = new Label();

					l.setCaption(account.getCreatedtime() + "");
					return l;
				} else {
					return new Label("");
				}

			}
		});

		tableItem.addGeneratedColumn("accountname", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleAccount account = ((PagedBeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				if (account != null) {
					ButtonLink b = new ButtonLink(account.getAccountname(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {

								}
							});
					return b;
				} else {
					return new Label("");
				}

			}
		});

		accountListLayout.removeAllComponents();

		accountListLayout.addComponent(constructTableActionControls());
		accountListLayout.addComponent(tableItem);
		accountListLayout.addComponent(tableItem.createControls());
	}

	@Override
	public HasSearchHandlers<AccountSearchCriteria> getSearchPanel() {
		return accountSearchPanel;
	}

	@SuppressWarnings("serial")
	@PostConstruct
	private void init() {
		// eventBus.addListener(new
		// ApplicationEventListener<AccountEvent.Search>() {
		//
		// @Override
		// public Class<? extends ApplicationEvent> getEventType() {
		// return AccountEvent.Search.class;
		// }
		//
		// @Override
		// public void handle(AccountEvent.Search event) {
		// searchCriteria = (AccountSearchCriteria) event.getData();
		// AccountListViewImpl.this.doSearch(searchCriteria);
		// }
		// });
	}

	@Override
	public void doDefaultSearch() {
		searchCriteria = new AccountSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		doSearch(searchCriteria);
	}

	@SuppressWarnings("serial")
	@Override
	public void doSearch(AccountSearchCriteria searchCriteria) {
		// tableItem = new BeanTable<SimpleAccount>();
		// tableItem.addStyleName("striped");
		//
		// MyBatisQueryContainer<SimpleAccount> container = new
		// MyBatisQueryContainer<SimpleAccount>(
		// new MyBatisQueryDefinition<AccountSearchCriteria>(
		// AppContext.getSpringBean(AccountService.class), false,
		// 5), new MyBatisQueryFactory<AccountSearchCriteria>(
		// searchCriteria));
		// container.addContainerProperty("selected", Boolean.class, false,
		// false,
		// false);
		// container.addContainerProperty("accountname", String.class, "", true,
		// true);
		// container.addContainerProperty("city", String.class, "", true, true);
		// container.addContainerProperty("billingCountry", String.class, "",
		// true, true);
		// container.addContainerProperty("phoneoffice", String.class, "", true,
		// true);
		//
		// container.addContainerProperty("email", String.class, "", true,
		// true);
		// container.addContainerProperty("assignuser", String.class, "", true,
		// true);
		// container.addContainerProperty("createdtime", String.class, "", true,
		// true);
		// tableItem.setContainerDataSource(container);
		// tableItem.setColumnHeaders(new String[] { "", "Name", "City",
		// "Billing Country", "Phone Office", "Email Address",
		// "Assign User", "Created Time" });
		//
		// tableItem.setWidth("1130px");
		//
		// tableItem.setColumnWidth("selected", 20);
		// tableItem.setColumnWidth("city", 130);
		//
		// tableItem.setColumnWidth("billingCountry", 130);
		//
		// tableItem.setColumnWidth("phoneoffice", 90);
		// tableItem.setColumnWidth("email", 180);
		//
		// tableItem.setColumnWidth("assignuser", 140);
		// tableItem.setColumnWidth("createdtime", 120);
		//
		// tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
		//
		// @Override
		// public Object generateCell(final Table source, final Object itemId,
		// Object columnId) {
		// final CheckBox cb = new CheckBox("", false);
		// cb.setImmediate(true);
		// cb.addListener(new Property.ValueChangeListener() {
		// @Override
		// public void valueChange(Property.ValueChangeEvent event) {
		// SimpleAccount account = ((BeanTable<SimpleAccount>) source)
		// .getBeanByIndex(itemId);
		//
		// }
		// });
		//
		// cb.addListener(new Button.ClickListener() {
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		// System.out.println("Value: " + cb.getValue());
		//
		// }
		// });
		// @SuppressWarnings("unchecked")
		// SimpleAccount account = ((BeanTable<SimpleAccount>) source)
		// .getBeanByIndex(itemId);
		// account.setExtraData(cb);
		// return cb;
		// }
		// });
		//
		// tableItem.addGeneratedColumn("email", new ColumnGenerator() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// @SuppressWarnings("unchecked")
		// public com.vaadin.ui.Component generateCell(Table source,
		// Object itemId, Object columnId) {
		// SimpleAccount account = ((BeanTable<SimpleAccount>) source)
		// .getBeanByIndex(itemId);
		// if (account != null) {
		// Link l = new Link();
		// l.setResource(new ExternalResource("mailto:"
		// + account.getEmail()));
		// l.setCaption(account.getEmail());
		// return l;
		// } else {
		// return new Label("");
		// }
		//
		// }
		// });
		//
		// tableItem.addGeneratedColumn("createdtime", new ColumnGenerator() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public com.vaadin.ui.Component generateCell(Table source,
		// Object itemId, Object columnId) {
		// @SuppressWarnings("unchecked")
		// final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
		// .getBeanByIndex(itemId);
		// if (account != null) {
		// Label l = new Label();
		//
		// l.setCaption(account.getCreatedtime() + "");
		// return l;
		// } else {
		// return new Label("");
		// }
		//
		// }
		// });
		//
		// tableItem.addGeneratedColumn("accountname", new ColumnGenerator() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public com.vaadin.ui.Component generateCell(Table source,
		// final Object itemId, Object columnId) {
		// @SuppressWarnings("unchecked")
		// final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
		// .getBeanByIndex(itemId);
		// if (account != null) {
		// ButtonLink b = new ButtonLink(account.getAccountname(),
		// new Button.ClickListener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		//
		// }
		// });
		// return b;
		// } else {
		// return new Label("");
		// }
		//
		// }
		// });
		//
		// accountListLayout.removeAllComponents();
		//
		// accountListLayout.addComponent(constructTableActionControls());
		// accountListLayout.addComponent(tableItem);
		// accountListLayout.addComponent(tableItem.createControls());
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		SelectionOptionButton selecSplitButton = new SelectionOptionButton();
		layout.addComponent(selecSplitButton);
		selecSplitButton.addListener(presenter);

		tableActionControls = new PopupButtonControl("delete", "Delete");
		tableActionControls.addOptionItem("mail", "Mail");
		tableActionControls.addOptionItem("export", "Export");
		
		tableActionControls.addListener(presenter);

		layout.addComponent(tableActionControls);
		layout.addComponent(selectedItemsNumberLabel);
		layout.setComponentAlignment(selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layout;
	}

	@Override
	public void enableActionControls() {
		tableActionControls.setEnabled(true);
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setEnabled(false);
	}

	@Override
	public void setPresenter(AccountListPresenter presenter) {
		this.presenter = presenter;

	}
}

package com.esofthead.mycollab.module.crm.view.account;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.AccountSearchPanel;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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

	private final AccountSearchPanel accountSearchPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable<SimpleAccount> tableItem;

	private final VerticalLayout accountListLayout;

	private PopupButtonControl tableActionControls;

	private final Label selectedItemsNumberLabel = new Label();

	public AccountListViewImpl() {
		this.setSpacing(true);

		accountSearchPanel = new AccountSearchPanel();
		this.addComponent(accountSearchPanel);

		accountListLayout = new VerticalLayout();
		accountListLayout.setSpacing(true);
		this.addComponent(accountListLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable<SimpleAccount>();

		tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						@SuppressWarnings("unchecked")
						SimpleAccount account = tableItem
								.getBeanByIndex(itemId);
						tableItem.fireSelectItemEvent(account);

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
									System.out.println("View account: "
											+ account);
									EventBus.getInstance().fireEvent(
											new AccountEvent.GotoRead(this,
													account));
								}
							});
					return b;
				} else {
					return new Label("");
				}

			}
		});

		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("city", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("billingCountry",
				UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem
				.setColumnWidth("phoneoffice", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		tableItem.setColumnWidth("assignuser", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("createdtime", UIConstants.TABLE_DATE_WIDTH);

		tableItem.setWidth("100%");

		accountListLayout.addComponent(constructTableActionControls());
		accountListLayout.addComponent(tableItem);
		accountListLayout.addComponent(tableItem.createControls());
	}

	@Override
	public void displayAccounts(List<SimpleAccount> accounts, int currentPage,
			int totalPages) {
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

	}

	@Override
	public HasSearchHandlers<AccountSearchCriteria> getSearchHandlers() {
		return accountSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton();
		layout.addComponent(selectOptionButton);

		tableActionControls = new PopupButtonControl("delete", "Delete");
		tableActionControls.addOptionItem("mail", "Mail");
		tableActionControls.addOptionItem("export", "Export");

		layout.addComponent(tableActionControls);
		layout.addComponent(selectedItemsNumberLabel);
		layout.setComponentAlignment(selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layout;
	}

	@Override
	public void enableActionControls(int numOfSelectedItems) {
		tableActionControls.setEnabled(true);
		selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setEnabled(false);
		selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasPagableHandlers getPagableHandlers() {
		return tableItem;
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleAccount> getSelectableItemHandlers() {
		return tableItem;
	}
}

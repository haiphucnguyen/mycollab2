package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.ui.components.AccountSearchPanel;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryContainer;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryDefinition;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryFactory;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@Component
public class AccountListViewImpl extends AbstractView implements
		AccountListView {
	private static final long serialVersionUID = 1L;

	private BeanTable<SimpleAccount> tableItem;

	private AccountSearchCriteria searchCriteria;

	private VerticalLayout accountListLayout;

	@SuppressWarnings("serial")
	@PostConstruct
	private void init() {
		eventBus.addListener(new ApplicationEventListener<AccountEvent.Search>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.Search.class;
			}

			@Override
			public void handle(AccountEvent.Search event) {
				searchCriteria = (AccountSearchCriteria) event.getData();
				AccountListViewImpl.this.doSearch(searchCriteria);
			}
		});
	}

	@Override
	public void doDefaultSearch() {
		searchCriteria = new AccountSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		doSearch(searchCriteria);
	}

	@Override
	public void doSearch(AccountSearchCriteria searchCriteria) {
		tableItem = new BeanTable<SimpleAccount>();
		tableItem.addStyleName("striped");

		MyBatisQueryContainer<SimpleAccount> container = new MyBatisQueryContainer<SimpleAccount>(
				new MyBatisQueryDefinition<AccountSearchCriteria>(
						AppContext.getSpringBean(AccountService.class), false,
						5), new MyBatisQueryFactory<AccountSearchCriteria>(
						searchCriteria));
		container.addContainerProperty("accountname", String.class, "", true,
				true);
		container.addContainerProperty("city", String.class, "", true, true);
		container.addContainerProperty("billingCountry", String.class, "",
				true, true);
		container.addContainerProperty("phoneoffice", String.class, "", true,
				true);

		container.addContainerProperty("email", String.class, "", true, true);
		container.addContainerProperty("assignuser", String.class, "", true,
				true);
		container.addContainerProperty("createdtime", String.class, "", true,
				true);
		container.addContainerProperty("action", Object.class, "", true, false);
		tableItem.setContainerDataSource(container);
		tableItem.setColumnHeaders(new String[] { "Name", "City",
				"Billing Country", "Phone Office", "Email Address",
				"Assign User", "Created Time", "Action" });

		System.out.println("View: " + container.getQueryView().size());

		tableItem.setColumnWidth("accountname", 207);

		tableItem.setColumnWidth("city", 137);

		tableItem.setColumnWidth("billingCountry", 137);

		tableItem.setColumnWidth("email", 137);

		tableItem.setColumnWidth("assignuser", 136);

		tableItem.setColumnWidth("action", 82);

		tableItem.addGeneratedColumn("email", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			@SuppressWarnings("unchecked")
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				SimpleAccount account = ((BeanTable<SimpleAccount>) source)
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
				final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
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
				final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				if (account != null) {
					Button b = new Button(account.getAccountname(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									eventBus.fireEvent(new AccountEvent.GotoRead(
											this, account));
								}
							});
					b.setStyleName("link");
					return b;
				} else {
					return new Label("");
				}

			}
		});

		tableItem.addGeneratedColumn("action", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				if (account != null) {
					HorizontalLayout layout = new HorizontalLayout();
					layout.setSpacing(true);
					Button editAccount = new Button("Edit",
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									eventBus.fireEvent(new AccountEvent.GotoEdit(
											this, account));

								}
							});
					editAccount.setStyleName(BaseTheme.BUTTON_LINK);
					layout.addComponent(editAccount);

					layout.addComponent(new Label("|"));
					Button deleteAccount = new Button("Delete",
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									// TODO Auto-generated method stub

								}
							});
					deleteAccount.setStyleName(BaseTheme.BUTTON_LINK);
					layout.addComponent(deleteAccount);
					return layout;
				} else {
					return new Label("");
				}

			}
		});

		accountListLayout.removeAllComponents();
		accountListLayout.addComponent(tableItem);
		accountListLayout.addComponent(tableItem.createControls());
	}

	@Override
	protected void initializeLayout() {
		this.setSpacing(true);

		AccountSearchPanel accountSearchPanel = AppContext
				.getSpringBean(AccountSearchPanel.class);
		this.addComponent(accountSearchPanel);

		accountListLayout = new VerticalLayout();
		this.addComponent(accountListLayout);
	}
}

package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

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
import com.esofthead.mycollab.vaadin.mvp.ui.Params;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
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

	private PagedTable tableItem;

	private AccountSearchCriteria searchCriteria;

	@Override
	public void handleRequest(Params params) {
		searchCriteria = new AccountSearchCriteria();
	}

	@SuppressWarnings("serial")
	@PostConstruct
	private void init() {
		eventBus.addListener(new ApplicationEventListener<AccountEvent>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return AccountEvent.class;
			}

			@Override
			public void handle(AccountEvent event) {
				if (event.getName().equals(AccountEvent.SEARCH)) {
					searchCriteria = (AccountSearchCriteria) event.getData();

				}
			}
		});
	}

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		AccountSearchPanel accountSearchPanel = AppContext
				.getSpringBean(AccountSearchPanel.class);
		layout.addComponent(accountSearchPanel);
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

		container.addContainerProperty("billingCountry", String.class, "",
				true, true);
		tableItem.setColumnWidth("billingCountry", 150);

		container.addContainerProperty("phoneoffice", String.class, "", true,
				true);

		container.addContainerProperty("email", String.class, "", true, true);
		tableItem.setColumnWidth("email", 150);

		container.addContainerProperty("assignuser", String.class, "", true,
				true);
		tableItem.setColumnWidth("assignuser", 150);

		container.addContainerProperty("createdtime", String.class, "", true,
				true);
		container.addContainerProperty("action", Object.class, "", true, false);
		tableItem.setColumnWidth("action", 80);

		tableItem.setContainerDataSource(container);
		tableItem.setColumnHeaders(new String[] { "Name", "City",
				"Billing Country", "Phone Office", "Email Address",
				"Assign User", "Created Time", "Action" });

		tableItem.addGeneratedColumn("email", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				SimpleAccount account = ((BeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				Link l = new Link();
				l.setResource(new ExternalResource("mailto:"
						+ account.getEmail()));
				l.setCaption(account.getEmail());
				return l;
			}
		});

		tableItem.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				Label l = new Label();
				@SuppressWarnings("unchecked")
				final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				l.setCaption(account.getCreatedtime() + "");
				return l;
			}
		});

		tableItem.addGeneratedColumn("accountname", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				Button b = new Button(account.getAccountname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								eventBus.fireEvent(new AccountEvent(this,
										AccountEvent.GOTO_READ_VIEW, account));
							}
						});
				b.setStyleName("link");
				return b;
			}
		});

		tableItem.addGeneratedColumn("action", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleAccount account = ((BeanTable<SimpleAccount>) source)
						.getBeanByIndex(itemId);
				HorizontalLayout layout = new HorizontalLayout();
				layout.setSpacing(true);
				Button editAccount = new Button("Edit",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								eventBus.fireEvent(new AccountEvent(this,
										AccountEvent.GOTO_EDIT_VIEW, account));

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
			}
		});

		layout.addComponent(tableItem);
		layout.addComponent(tableItem.createControls());
		return layout;
	}
}

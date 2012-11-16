package com.esofthead.mycollab.module.crm.ui;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.vaadin.hene.splitbutton.SplitButton;

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
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton.SelectionOptionListener;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property;
import com.vaadin.terminal.ExternalResource;
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

@Component
public class AccountListViewImpl extends AbstractView implements
		AccountListView, SelectionOptionListener {
	private static final long serialVersionUID = 1L;

	private BeanTable<SimpleAccount> tableItem;

	private AccountSearchCriteria searchCriteria;

	private VerticalLayout accountListLayout;

	private SplitButton tableActionControls;

	private final Set<Object> selectedItemIds = new HashSet<Object>();

	@Override
	protected void initializeLayout() {
		this.setSpacing(true);

		AccountSearchPanel accountSearchPanel = AppContext
				.getSpringBean(AccountSearchPanel.class);
		this.addComponent(accountSearchPanel);

		accountListLayout = new VerticalLayout();
		accountListLayout.setSpacing(true);
		this.addComponent(accountListLayout);
	}

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
		container.addContainerProperty("selected", Boolean.class, false, false,
				false);
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
		tableItem.setContainerDataSource(container);
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
			public Object generateCell(Table source, final Object itemId,
					Object columnId) {
				boolean selected = selectedItemIds.contains(itemId);
				/*
				 * When the chekboc value changes, add/remove the itemId from
				 * the selectedItemIds set
				 */
				final CheckBox cb = new CheckBox("", selected);
				cb.addListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(Property.ValueChangeEvent event) {
						if (selectedItemIds.contains(itemId)) {
							selectedItemIds.remove(itemId);
						} else {
							selectedItemIds.add(itemId);
						}
					}
				});
				return cb;
			}
		});

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
					ButtonLink b = new ButtonLink(account.getAccountname(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									eventBus.fireEvent(new AccountEvent.GotoRead(
											this, account));
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

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		SelectionOptionButton selecSplitButton = new SelectionOptionButton();
		layout.addComponent(selecSplitButton);
		selecSplitButton.addListener(this);

		tableActionControls = new SplitButton("Delete");
		tableActionControls.addStyleName(SplitButton.STYLE_CHAMELEON);
		
		VerticalLayout actionLayout = new VerticalLayout();
		actionLayout.setWidth("100px");
		actionLayout.addComponent(new ButtonLink("Mail"));
		actionLayout.addComponent(new ButtonLink("Export"));
		tableActionControls.setComponent(actionLayout);
		
		layout.addComponent(tableActionControls);
		return layout;
	}

	@Override
	public void onSelect() {
		tableActionControls.setEnabled(true);

		for (Object id : tableItem.getItemIds()) {
			CheckBox checkBox;
            try {
                checkBox = (CheckBox) tableItem.getContainerProperty(id,
                        "selected").getValue();
                System.out.println("Checkbox: " + checkBox);
            } catch (Exception e) {
                e.printStackTrace(); // ->> NullPointerException
            }
		}
	}

	@Override
	public void onDeSelect() {
		tableActionControls.setEnabled(false);

	}
}

package com.esofthead.mycollab.module.crm.view.account;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
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
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	private TextField valueField;
	private UserListSelect userField;
	private GridLayout layoutSearchPane;

	private void addTextFieldSearch() {
		valueField = new TextField();
		layoutSearchPane.addComponent(valueField, 0, 0);
	}

	private void addUserListSelectField() {
		userField = new UserListSelect();
		layoutSearchPane.addComponent(userField, 0, 0, 0, 2);
	}

	@SuppressWarnings("serial")
	private ComponentContainer createSearchPanel() {
		layoutSearchPane = new GridLayout(3, 3);
		layoutSearchPane.setSpacing(true);

		userField = new UserListSelect();

		final ValueComboBox group = new ValueComboBox(false, new String[] {
				"Name", "Email", "Website", "Phone", "Assigned to" });
		group.setImmediate(true);
		group.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {

				layoutSearchPane.removeComponent(0, 0);
				String searchType = (String) group.getValue();
				if (searchType.equals("Name")) {
					addTextFieldSearch();
				} else if (searchType.equals("Email")) {
					addTextFieldSearch();
				} else if (searchType.equals("Website")) {
					addTextFieldSearch();
				} else if (searchType.equals("Phone")) {
					addTextFieldSearch();
				} else if (searchType.equals("Assigned to")) {
					addUserListSelectField();
				}
			}
		});
		layoutSearchPane.addComponent(group, 1, 0);

		Button searchBtn = new Button("Search");
		searchBtn.addListener(new Button.ClickListener() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void buttonClick(ClickEvent event) {
				String searchType = (String) group.getValue();
				searchCriteria = new AccountSearchCriteria();
				searchCriteria.setSaccountid(new NumberSearchField(
						SearchField.AND, AppContext.getAccountId()));
				String strSearch = (String) valueField.getValue();
				if (StringUtil.isNotNullOrEmpty(strSearch)) {
					if (StringUtil.isNotNullOrEmpty(searchType)) {

						if (searchType.equals("Name")) {
							searchCriteria
									.setAccountname(new StringSearchField(
											SearchField.AND, strSearch));
						} else if (searchType.equals("Email")) {
							searchCriteria.setAnyMail(new StringSearchField(
									SearchField.AND, strSearch));
						} else if (searchType.equals("Website")) {
							searchCriteria.setWebsite(new StringSearchField(
									SearchField.AND, strSearch));
						} else if (searchType.equals("Phone")) {
							searchCriteria.setAnyPhone(new StringSearchField(
									SearchField.AND, strSearch));
						}
					}
					
					Collection<String> users = (Collection<String>) userField
							.getValue();
					if (users != null && users.size() > 0) {
						searchCriteria.setAssignUsers(new SetSearchField(
								SearchField.AND, users));
					}
				}

				tableItem.setSearchCriteria(searchCriteria);
			}
		});
		layoutSearchPane.addComponent(searchBtn, 2, 0, 2, 2);
		return layoutSearchPane;
	}

	private void createAccountList() {
		tableItem = new AccountTableDisplay(new String[] { "accountname",
				"city", "assignuser" }, new String[] { "Name", "City",
				"Assign User" });
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

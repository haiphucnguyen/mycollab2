package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
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

    private ComponentContainer createSearchPanel() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        TextField valueField = new TextField();
        layout.addComponent(valueField);

        ValueComboBox group = new ValueComboBox(false, new String[]{
                    "Organization Name", "Billing City", "Webiste", "Phone",
                    "Assigned To"});
        layout.addComponent(group);

        Button searchBtn = new Button("Search");
        layout.addComponent(searchBtn);
        return layout;
    }

    private void createAccountList() {
        tableItem = new AccountTableDisplay(new String[]{"accountname", "city",
                    "assignuser"}, new String[]{"Name", "City",
                    "Assign User"});
        tableItem.setWidth("100%");
        tableItem.setHeight("200px");

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
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
                            .removeWindow(
                            AccountSelectionWindow.this);
                }
            }
        });
    }
}

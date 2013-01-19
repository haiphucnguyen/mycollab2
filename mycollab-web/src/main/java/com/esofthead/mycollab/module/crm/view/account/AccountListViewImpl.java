package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hene.splitbutton.PopupButtonControl;

@ViewComponent
public class AccountListViewImpl extends AbstractView implements
        AccountListView {

    private static final long serialVersionUID = 1L;
    private final AccountSearchPanel accountSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private AccountTableDisplay tableItem;
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
        tableItem = new AccountTableDisplay(new String[]{"selected", "accountname",
                    "city", "phoneoffice", "email", "assignUserFullName"},
                new String[]{"", "Name", "City", "Phone Office",
                    "Email Address", "Assign User"});

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleAccount account = (SimpleAccount) event.getData();
                if ("accountname".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(new AccountEvent.GotoRead(AccountListViewImpl.this, account.getId()));
                }
            }
        });

        accountListLayout.addComponent(constructTableActionControls());
        accountListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<AccountSearchCriteria> getSearchHandlers() {
        return accountSearchPanel;
    }

    private ComponentContainer constructTableActionControls() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);

        selectOptionButton = new SelectionOptionButton(tableItem);
        layout.addComponent(selectOptionButton);
        
        Button deleteBtn = new Button("Delete");
        deleteBtn.setEnabled(AppContext.canAccess(RolePermissionCollections.CRM_ACCOUNT));

        tableActionControls = new PopupButtonControl("delete", deleteBtn);
        tableActionControls.addOptionItem("mail", "Mail");
        tableActionControls.addOptionItem("export", "Export");
        tableActionControls.setVisible(false);

        layout.addComponent(tableActionControls);
        layout.addComponent(selectedItemsNumberLabel);
        layout.setComponentAlignment(selectedItemsNumberLabel,
                Alignment.MIDDLE_CENTER);
        
        layout.setExpandRatio(selectedItemsNumberLabel, 1.0f);
        
        Button reportBtn = new Button("Reports");
        reportBtn.setIcon(new ThemeResource("icons/16/report.png"));
        reportBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
        layout.addComponent(reportBtn);
        return layout;
    }

    @Override
    public void enableActionControls(int numOfSelectedItems) {
        tableActionControls.setVisible(true);
        selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
    }

    @Override
    public void disableActionControls() {
        tableActionControls.setVisible(false);
        selectOptionButton.setSelectedChecbox(false);
        selectedItemsNumberLabel.setValue("");
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

    @Override
    public IPagedBeanTable<AccountSearchCriteria, SimpleAccount> getPagedBeanTable() {
        return tableItem;
    }
}

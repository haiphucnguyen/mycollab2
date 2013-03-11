package com.esofthead.mycollab.module.crm.view.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.file.ExportStreamResource;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

public class AccountListPresenter extends CrmGenericPresenter<AccountListView>
        implements ListPresenter<AccountSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private static final String[] EXPORT_VISIBLE_COLUMNS = new String[]{
        "accountname", "city", "phoneoffice", "email", "assignUserFullName"};
    private static final String[] EXPORT_DISPLAY_NAMES = new String[]{"Name",
        "City", "Phone Office", "Email Address", "Assign User"};
    private AccountService accountService;
    private AccountSearchCriteria searchCriteria;
    private boolean isSelectAll = false;

    public AccountListPresenter() {
        super(AccountListView.class);
        accountService = AppContext.getSpringBean(AccountService.class);

        view.getPagedBeanTable().addPagableHandler(new PagableHandler() {
            private static final long serialVersionUID = 1L;

            @Override
            public void move(int newPageNumber) {
                pageChange();
            }

            @Override
            public void displayItemChange(int numOfItems) {
                pageChange();
            }

            private void pageChange() {
                if (isSelectAll) {
                    selectAllItemsInCurrentPage();
                }

                checkWhetherEnableTableActionControl();
            }
        });

        view.getSearchHandlers().addSearchHandler(
                new SearchHandler<AccountSearchCriteria>() {
                    @Override
                    public void onSearch(AccountSearchCriteria criteria) {
                        doSearch(criteria);
                    }
                });

        view.getOptionSelectionHandlers().addSelectionOptionHandler(
                new SelectionOptionHandler() {
                    @Override
                    public void onSelectCurrentPage() {
                        isSelectAll = false;
                        selectAllItemsInCurrentPage();

                        checkWhetherEnableTableActionControl();
                    }

                    @Override
                    public void onDeSelect() {
                        Collection<SimpleAccount> currentDataList = view
                                .getPagedBeanTable().getCurrentDataList();
                        isSelectAll = false;
                        for (SimpleAccount item : currentDataList) {
                            item.setSelected(false);
                            CheckBox checkBox = (CheckBox) item.getExtraData();
                            checkBox.setValue(false);
                        }

                        checkWhetherEnableTableActionControl();

                    }

                    @Override
                    public void onSelectAll() {
                        isSelectAll = true;
                        selectAllItemsInCurrentPage();

                        checkWhetherEnableTableActionControl();
                    }
                });

        view.getPopupActionHandlers().addPopupActionHandler(
                new PopupActionHandler() {
                    @Override
                    public void onSelect(String id, String caption) {
                        if ("delete".equals(id)) {
                            ConfirmDialog.show(view.getWindow(),
                                    "Please Confirm:",
                                    "Are you sure to delete selected items ?",
                                    "Yes", "No", new ConfirmDialog.Listener() {
                                private static final long serialVersionUID = 1L;

                                @Override
                                public void onClose(ConfirmDialog dialog) {
                                    if (dialog.isConfirmed()) {
                                        deleteSelectedItems();
                                    }
                                }
                            });

                        } else if ("mail".equals(id)) {
                            if (isSelectAll) {
                                AppContext.getApplication().getMainWindow().showNotification("This version has not supported the sending email for all users yet!");
                            } else {
                                List<String> lstMail = new ArrayList<String>();
                                List<SimpleAccount> tableData = view.getPagedBeanTable()
                                        .getCurrentDataList();
                                for (SimpleAccount item : tableData) {
                                    if (item.isSelected()) {
                                        lstMail.add(item.getAccountname() + " <" + item.getEmail() + ">");
                                    }
                                }
                                view.getWidget().getWindow().addWindow(new MailFormWindow(lstMail));
                            }

                        } else if ("export".equals(id)) {
                            Resource res = null;

                            if (isSelectAll) {
                                res = new StreamResource(
                                        new ExportStreamResource.AllItems<AccountSearchCriteria>(
                                        EXPORT_VISIBLE_COLUMNS,
                                        EXPORT_DISPLAY_NAMES,
                                        AppContext
                                        .getSpringBean(AccountService.class),
                                        searchCriteria), "export.csv",
                                        view.getApplication());
                            } else {
                                List tableData = view.getPagedBeanTable()
                                        .getCurrentDataList();
                                res = new StreamResource(
                                        new ExportStreamResource.ListData(
                                        EXPORT_VISIBLE_COLUMNS,
                                        EXPORT_DISPLAY_NAMES, tableData),
                                        "export.csv", view.getApplication());
                            }

                            view.getWidget().getWindow().open(res, "_blank");
                        }
                    }
                });

        view.getSelectableItemHandlers().addSelectableItemHandler(
                new SelectableItemHandler<SimpleAccount>() {
                    @Override
                    public void onSelect(SimpleAccount item) {
                        isSelectAll = false;
                        item.setSelected(!item.isSelected());

                        checkWhetherEnableTableActionControl();
                    }
                });
    }

    private void selectAllItemsInCurrentPage() {
        Collection<SimpleAccount> currentDataList = view.getPagedBeanTable()
                .getCurrentDataList();
        for (SimpleAccount item : currentDataList) {
            item.setSelected(true);
            CheckBox checkBox = (CheckBox) item.getExtraData();
            checkBox.setValue(true);
        }
    }

    private void checkWhetherEnableTableActionControl() {
        Collection<SimpleAccount> currentDataList = view.getPagedBeanTable()
                .getCurrentDataList();
        int countItems = 0;
        for (SimpleAccount item : currentDataList) {
            if (item.isSelected()) {
                countItems++;
            }
        }
        if (countItems > 0) {
            view.enableActionControls(countItems);
        } else {
            view.disableActionControls();
        }
    }

    @Override
    public void doSearch(AccountSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
        view.getPagedBeanTable().setSearchCriteria(searchCriteria);
        checkWhetherEnableTableActionControl();
    }

    private void deleteSelectedItems() {
        if (!isSelectAll) {
            Collection<SimpleAccount> currentDataList = view
                    .getPagedBeanTable().getCurrentDataList();
            List<Integer> keyList = new ArrayList<Integer>();
            for (SimpleAccount item : currentDataList) {
                if (item.isSelected()) {
                    keyList.add(item.getId());
                }
            }

            if (keyList.size() > 0) {
                accountService.removeWithSession(keyList,
                        AppContext.getUsername());
                doSearch(searchCriteria);
                checkWhetherEnableTableActionControl();
            }
        } else {
            accountService.removeByCriteria(searchCriteria);
            doSearch(searchCriteria);
        }

    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
    	if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
    		 super.onGo(container, data);
    	        doSearch((AccountSearchCriteria) data.getParams());
    	        AppContext.addFragment("crm/account/list", "Account List");
    	} else {
    		MessageConstants.showMessagePermissionAlert();
    	}
    }
}

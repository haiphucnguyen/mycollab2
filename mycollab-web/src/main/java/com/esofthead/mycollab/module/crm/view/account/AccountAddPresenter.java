package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class AccountAddPresenter extends CrmGenericPresenter<AccountAddView> {

    private static final long serialVersionUID = 1L;

    public AccountAddPresenter() {
        super(AccountAddView.class);
        bind();
    }

    private void bind() {
        view.getEditFormHandlers().addFormHandler(
                new EditFormHandler<Account>() {
                    @Override
                    public void onSave(final Account account) {
                        saveAccount(account);
                        ViewState viewState = HistoryViewManager.back();
                        if (viewState instanceof NullViewState) {
                            EventBus.getInstance().fireEvent(
                                    new AccountEvent.GotoList(this, null));
                        }
                    }

                    @Override
                    public void onCancel() {
                        ViewState viewState = HistoryViewManager.back();
                        if (viewState instanceof NullViewState) {
                            EventBus.getInstance().fireEvent(
                                    new AccountEvent.GotoList(this, null));
                        }
                    }

                    @Override
                    public void onSaveAndNew(final Account account) {
                        saveAccount(account);
                        EventBus.getInstance().fireEvent(
                                new AccountEvent.GotoAdd(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        super.onGo(container, data);
        view.editItem((Account) data.getParams());
    }

    public void saveAccount(Account account) {
        AccountService accountService = AppContext
                .getSpringBean(AccountService.class);

        account.setSaccountid(AppContext.getAccountId());
        if (account.getId() == null) {
            accountService.saveWithSession(account, AppContext.getUsername());
        } else {
            accountService.updateWithSession(account, AppContext.getUsername());
        }

    }
}

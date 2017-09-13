package com.mycollab.premium.mobile.module.crm.view.account;

import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.crm.events.AccountEvent;
import com.mycollab.mobile.module.crm.view.AbstractCrmPresenter;
import com.mycollab.mobile.module.crm.view.account.AccountAddView;
import com.mycollab.mobile.module.crm.view.account.IAccountAddPresenter;
import com.mycollab.mobile.shell.events.ShellEvent;
import com.mycollab.module.crm.domain.Account;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.service.AccountService;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class AccountAddPresenter extends AbstractCrmPresenter<AccountAddView> implements IAccountAddPresenter {
    private static final long serialVersionUID = -3664699848882470039L;

    public AccountAddPresenter() {
        super(AccountAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleAccount>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleAccount account) {
                saveAccount(account);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }

            @Override
            public void onSaveAndNew(final SimpleAccount account) {
                saveAccount(account);
                EventBusFactory.getInstance().post(new AccountEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canWrite(RolePermissionCollections.CRM_ACCOUNT)) {
            SimpleAccount account = null;
            if (data.getParams() instanceof SimpleAccount) {
                account = (SimpleAccount) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                account = accountService.findById((Integer) data.getParams(), AppUI.getAccountId());
            }
            if (account == null) {
                NotificationUtil.showRecordNotExistNotification();
                return;
            }
            view.editItem(account);
            super.onGo(container, data);
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private void saveAccount(Account account) {
        AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
        account.setSaccountid(AppUI.getAccountId());
        if (account.getId() == null) {
            accountService.saveWithSession(account, UserUIContext.getUsername());
        } else {
            accountService.updateWithSession(account, UserUIContext.getUsername());
        }
    }
}

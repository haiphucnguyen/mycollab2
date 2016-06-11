package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.ResourceNotFoundException;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.project.events.ClientEvent;
import com.esofthead.mycollab.module.project.i18n.ClientI18nEnum;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.IEditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public class ClientAddPresenter extends AbstractPresenter<ClientAddView> {
    public ClientAddPresenter() {
        super(ClientAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new IEditFormHandler<SimpleAccount>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleAccount account) {
                int accountId = saveAccount(account);
                EventBusFactory.getInstance().post(new ClientEvent.GotoRead(this, accountId));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null));
            }

            @Override
            public void onSaveAndNew(final SimpleAccount account) {
                saveAccount(account);
                EventBusFactory.getInstance().post(new ClientEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ClientContainer clientContainer = (ClientContainer) container;
        clientContainer.removeAllComponents();
        clientContainer.addComponent(view);
        if (AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT)) {
            SimpleAccount account = null;
            if (data.getParams() instanceof SimpleAccount) {
                account = (SimpleAccount) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                account = accountService.findById((Integer) data.getParams(), AppContext.getAccountId());
            }

            if (account == null) {
                throw new ResourceNotFoundException();
            }

            view.editItem(account);
            if (account.getId() == null) {
                AppContext.addFragment("project/client/add", AppContext.getMessage(GenericI18Enum
                        .BROWSER_ADD_ITEM_TITLE, AppContext.getMessage(ClientI18nEnum.SINGLE)));
            } else {
                AppContext.addFragment("project/client/edit/" + UrlEncodeDecoder.encode(account.getId()),
                        AppContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE, AppContext.getMessage(ClientI18nEnum.SINGLE),
                                account.getAccountname()));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private int saveAccount(Account account) {
        AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
        account.setSaccountid(AppContext.getAccountId());
        if (account.getId() == null) {
            accountService.saveWithSession(account, AppContext.getUsername());
        } else {
            accountService.updateWithSession(account, AppContext.getUsername());
        }
        return account.getId();
    }
}

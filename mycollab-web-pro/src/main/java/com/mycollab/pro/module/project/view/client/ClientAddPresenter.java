package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.ResourceNotFoundException;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.crm.domain.Account;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.service.AccountService;
import com.mycollab.module.project.event.ClientEvent;
import com.mycollab.module.project.i18n.ClientI18nEnum;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.IEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

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
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ClientContainer clientContainer = (ClientContainer) container;
        clientContainer.removeAllComponents();
        clientContainer.addComponent(view);
        if (UserUIContext.canWrite(RolePermissionCollections.INSTANCE.getCRM_ACCOUNT())) {
            SimpleAccount account = null;
            if (data.getParams() instanceof SimpleAccount) {
                account = (SimpleAccount) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                account = accountService.findById((Integer) data.getParams(), AppUI.getAccountId());
            }

            if (account == null) {
                throw new ResourceNotFoundException();
            }

            view.editItem(account);
            if (account.getId() == null) {
                AppUI.addFragment("project/client/add", UserUIContext.getMessage(GenericI18Enum
                        .BROWSER_ADD_ITEM_TITLE, UserUIContext.getMessage(ClientI18nEnum.SINGLE)));
            } else {
                AppUI.addFragment("project/client/edit/" + UrlEncodeDecoder.encode(account.getId()),
                        UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE, UserUIContext.getMessage(ClientI18nEnum.SINGLE),
                                account.getAccountname()));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private int saveAccount(Account account) {
        AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
        account.setSaccountid(AppUI.getAccountId());
        if (account.getId() == null) {
            accountService.saveWithSession(account, UserUIContext.getUsername());
        } else {
            accountService.updateWithSession(account, UserUIContext.getUsername());
        }
        return account.getId();
    }
}

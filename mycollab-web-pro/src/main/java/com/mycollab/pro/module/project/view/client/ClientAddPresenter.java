package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.domain.SimpleClient;
import com.mycollab.common.i18n.ClientI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.service.ClientService;
import com.mycollab.core.ResourceNotFoundException;
import com.mycollab.module.project.event.ClientEvent;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.IEditFormHandler;
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
        view.getEditFormHandlers().addFormHandler(new IEditFormHandler<SimpleClient>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleClient client) {
                int accountId = saveClient(client);
                EventBusFactory.getInstance().post(new ClientEvent.GotoRead(this, accountId));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null));
            }

            @Override
            public void onSaveAndNew(final SimpleClient client) {
                saveClient(client);
                EventBusFactory.getInstance().post(new ClientEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ClientContainer clientContainer = (ClientContainer) container;
        clientContainer.removeAllComponents();
        clientContainer.addComponent(view);

        if (UserUIContext.canWrite(RolePermissionCollections.CLIENT)) {
            SimpleClient client = null;
            if (data.getParams() instanceof SimpleClient) {
                client = (SimpleClient) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                ClientService clientService = AppContextUtil.getSpringBean(ClientService.class);
                client = clientService.findById((Integer) data.getParams(), AppUI.getAccountId());
            }

            if (client == null) {
                throw new ResourceNotFoundException();
            }

            view.editItem(client);
            if (client.getId() == null) {
                AppUI.addFragment("project/client/add", UserUIContext.getMessage(GenericI18Enum
                        .BROWSER_ADD_ITEM_TITLE, UserUIContext.getMessage(ClientI18nEnum.SINGLE)));
            } else {
                AppUI.addFragment("project/client/edit/" + UrlEncodeDecoder.encode(client.getId()),
                        UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE, UserUIContext.getMessage(ClientI18nEnum.SINGLE),
                                client.getName()));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private int saveClient(SimpleClient client) {
        ClientService clientService = AppContextUtil.getSpringBean(ClientService.class);
        client.setSaccountid(AppUI.getAccountId());
        if (client.getId() == null) {
            clientService.saveWithSession(client, UserUIContext.getUsername());
        } else {
            clientService.updateWithSession(client, UserUIContext.getUsername());
        }
        return client.getId();
    }
}

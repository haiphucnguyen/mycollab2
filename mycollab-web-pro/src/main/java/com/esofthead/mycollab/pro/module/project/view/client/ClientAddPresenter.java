package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
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
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ClientContainer clientContainer = (ClientContainer) container;
        clientContainer.removeAllComponents();
        clientContainer.addComponent(view);
        if (AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT)) {
            SimpleAccount account = (SimpleAccount) data.getParams();
            view.editItem(account);
            AppContext.addFragment("project/client/add", AppContext.getMessage(GenericI18Enum.BROWSER_ADD_ITEM_TITLE, "Client"));
        }
    }
}

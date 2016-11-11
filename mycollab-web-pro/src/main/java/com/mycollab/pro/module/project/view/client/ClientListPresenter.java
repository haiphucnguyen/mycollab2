package com.mycollab.pro.module.project.view.client;

import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.project.i18n.ClientI18nEnum;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewScope;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class ClientListPresenter extends AbstractPresenter<ClientListView> {
    public ClientListPresenter() {
        super(ClientListView.class);
    }

    @Override
    protected void postInitView() {
        view.getSearchHandlers().addSearchHandler(criteria -> view.display(criteria));
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ClientContainer clientContainer = (ClientContainer) container;
        clientContainer.removeAllComponents();
        clientContainer.addComponent(view);
        if (UserUIContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
            AccountSearchCriteria searchCriteria = (AccountSearchCriteria) data.getParams();
            view.display(searchCriteria);
            MyCollabUI.addFragment("project/client/list", UserUIContext.getMessage(ClientI18nEnum.LIST));
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}

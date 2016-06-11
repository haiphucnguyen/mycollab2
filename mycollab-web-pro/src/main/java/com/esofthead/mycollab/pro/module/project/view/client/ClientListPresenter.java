package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ClientI18nEnum;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

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
        view.getSearchHandlers().addSearchHandler(new SearchHandler<AccountSearchCriteria>() {
            @Override
            public void onSearch(AccountSearchCriteria criteria) {
                view.display(criteria);
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ClientContainer clientContainer = (ClientContainer) container;
        clientContainer.removeAllComponents();
        clientContainer.addComponent(view);
        if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
            AccountSearchCriteria searchCriteria = (AccountSearchCriteria) data.getParams();
            view.display(searchCriteria);
            AppContext.addFragment("project/client/list", AppContext.getMessage(ClientI18nEnum.LIST));
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}

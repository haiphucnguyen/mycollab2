package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
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
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ClientContainer clientContainer = (ClientContainer)container;
        clientContainer.removeAllComponents();
        clientContainer.addComponent(view);
        view.display();
        AppContext.addFragment("project/client/list", "Clients");
    }
}

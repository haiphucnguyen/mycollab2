package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public class ClientListPresenter extends AbstractPresenter<ClientListView>{
    public ClientListPresenter() {
        super(ClientListView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {

    }
}

package com.mycollab.pro.module.project.view.client;

import com.mycollab.core.MyCollabException;
import com.mycollab.module.project.view.ProjectModule;
import com.mycollab.module.project.view.client.IClientContainer;
import com.mycollab.module.project.view.client.IClientPresenter;
import com.mycollab.module.project.view.parameters.ClientScreenData;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public class ClientPresenter extends AbstractPresenter<IClientContainer> implements IClientPresenter {
    public ClientPresenter() {
        super(IClientContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectModule prjContainer = (ProjectModule) container;
        prjContainer.removeAllComponents();
        prjContainer.with(view).withAlign(view, Alignment.TOP_CENTER);

        AbstractPresenter presenter;
        if (data instanceof ClientScreenData.Search) {
            presenter = PresenterResolver.getPresenter(ClientListPresenter.class);
        } else if (data instanceof ClientScreenData.Add || data instanceof ScreenData.Edit) {
            presenter = PresenterResolver.getPresenter(ClientAddPresenter.class);
        } else if (data instanceof ClientScreenData.Read) {
            presenter = PresenterResolver.getPresenter(ClientReadPresenter.class);
        } else {
            throw new MyCollabException("No support screen data " + data);
        }
        presenter.go(view, data);
    }
}

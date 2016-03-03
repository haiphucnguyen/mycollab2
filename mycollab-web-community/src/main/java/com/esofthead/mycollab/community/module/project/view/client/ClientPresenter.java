package com.esofthead.mycollab.community.module.project.view.client;

import com.esofthead.mycollab.module.project.view.ProjectModule;
import com.esofthead.mycollab.module.project.view.client.IClientContainer;
import com.esofthead.mycollab.module.project.view.client.IClientPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
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
    }
}

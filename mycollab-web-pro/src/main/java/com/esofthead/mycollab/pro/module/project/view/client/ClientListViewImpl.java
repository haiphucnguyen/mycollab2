package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@ViewComponent
public class ClientListViewImpl extends AbstractPageView implements ClientListView {

    private ClientSearchPanel accountSearchPanel;

    public ClientListViewImpl() {
        this.setMargin(true);
    }

    @Override
    public void display() {
        accountSearchPanel = new ClientSearchPanel();
        this.addComponent(accountSearchPanel);
    }
}

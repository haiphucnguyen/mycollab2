/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.TabSheet;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class UserPermissionManagementViewImpl extends AbstractView implements UserPermissionManagementView {

    private TabSheet tabContainer;
    private UserPresenter userPresenter;
    private RolePresenter rolePresenter;

    public UserPermissionManagementViewImpl() {
        tabContainer = new TabSheet();
        constructTabs();
        this.addComponent(tabContainer);
    }

    private void constructTabs() {
        userPresenter = PresenterResolver.getPresenter(UserPresenter.class);
        tabContainer.addTab(userPresenter.getView(), "Users");
        //goto user list by default
        userPresenter.go(UserPermissionManagementViewImpl.this, null);

        rolePresenter = PresenterResolver.getPresenter(RolePresenter.class);
        tabContainer.addTab(rolePresenter.getView(), "Roles");
    }
}

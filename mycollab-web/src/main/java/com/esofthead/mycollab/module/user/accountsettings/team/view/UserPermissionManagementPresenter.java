/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountDashboardView;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.UserScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class UserPermissionManagementPresenter extends AbstractPresenter<UserPermissionManagementView> {
	private static final long serialVersionUID = 1L;

	public UserPermissionManagementPresenter() {
        super(UserPermissionManagementView.class);
    }
    
    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
    	AccountDashboardView accountContaier = (AccountDashboardView)container;
    	accountContaier.gotoSubView("Users & Permissions");
    	
    	AbstractPresenter<?> presenter = null;
        if ((data == null) || (data instanceof UserScreenData.Read)) {
        	presenter = PresenterResolver.getPresenter(UserPresenter.class);
        } else {
        	
        }
        
        presenter.go(view.getWidget(), data);
    }
    
}

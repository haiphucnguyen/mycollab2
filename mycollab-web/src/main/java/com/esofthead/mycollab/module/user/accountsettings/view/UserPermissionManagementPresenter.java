/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
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
        
    }
    
}

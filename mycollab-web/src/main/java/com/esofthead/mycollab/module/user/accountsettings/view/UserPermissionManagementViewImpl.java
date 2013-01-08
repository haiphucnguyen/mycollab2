/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.TabSheet;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class UserPermissionManagementViewImpl extends AbstractView implements UserPermissionManagementView {

    private TabSheet tabContainer;
    
    public UserPermissionManagementViewImpl() {
        tabContainer = new TabSheet();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 *
 * @author haiphucnguyen
 */
public interface RoleAddView  extends IFormAddView<Role> {

    HasEditFormHandlers<Role> getEditFormHandlers();
    
    PermissionMap getPermissionMap();
    
}

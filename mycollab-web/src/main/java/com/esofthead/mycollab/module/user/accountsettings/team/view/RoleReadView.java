/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 * 
 * @author haiphucnguyen
 */
public interface RoleReadView extends IPreviewView<SimpleRole> {

	HasPreviewFormHandlers<Role> getPreviewFormHandlers();

}

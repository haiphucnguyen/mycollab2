/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class RoleContainer extends AbstractView {
    private RoleController controller;
    
    public RoleContainer() {
        controller = new RoleController(this);
        this.setWidth("900px");
    }
}

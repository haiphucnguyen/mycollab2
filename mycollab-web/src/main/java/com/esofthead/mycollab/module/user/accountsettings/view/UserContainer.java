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
public class UserContainer extends AbstractView {
    
    private UserController controller;
    
    public UserContainer() {
        controller = new UserController(this);
        this.setWidth("900px");
    }
}

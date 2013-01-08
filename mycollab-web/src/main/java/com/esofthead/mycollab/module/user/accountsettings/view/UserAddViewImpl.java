/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class UserAddViewImpl extends AbstractView implements UserAddView{
    public UserAddViewImpl() {
        this.addComponent(new Label("User page"));
    }
}

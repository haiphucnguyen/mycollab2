/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class ForgotPasswordViewImpl extends AbstractView implements ForgotPasswordView {
    public ForgotPasswordViewImpl() {
        this.addComponent(new Label("Forgot password view"));
    }
}

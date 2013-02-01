/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class ForgotPasswordPresenter extends AbstractPresenter<ForgotPasswordView> {

    public ForgotPasswordPresenter() {
        super(ForgotPasswordView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        MainWindowContainer windowContainer = (MainWindowContainer) container;
        windowContainer.removeAllComponents();

        windowContainer.addComponent(view.getWidget());
    }
}

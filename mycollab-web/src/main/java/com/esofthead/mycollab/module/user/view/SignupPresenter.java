/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class SignupPresenter extends AbstractPresenter<SignupView> {
	private static final long serialVersionUID = 1L;

	public SignupPresenter() {
        super(SignupView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        MainWindowContainer windowContainer = (MainWindowContainer)container;
        windowContainer.removeAllComponents();
        
        windowContainer.addComponent(view.getWidget());
        AppContext.addFragment("user/signup", "Signup Page");
    }
    
}

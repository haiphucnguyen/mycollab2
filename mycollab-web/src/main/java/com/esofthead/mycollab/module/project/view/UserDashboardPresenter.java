/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class UserDashboardPresenter extends AbstractPresenter<UserDashboardView> {
    
    public UserDashboardPresenter() {
    super(UserDashboardView.class);    
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectContainer prjContainer = (ProjectContainer) container;
        prjContainer.removeAllComponents();
        prjContainer.addComponent((Component) view);
    }
    
}

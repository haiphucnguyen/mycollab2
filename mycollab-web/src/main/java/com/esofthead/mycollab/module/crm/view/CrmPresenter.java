/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class CrmPresenter extends AbstractPresenter<CrmContainer> {

    public CrmPresenter() {
        super(CrmContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        MainView mainView = (MainView) container;
        mainView.addView(view);
        
        AppContext.updateLastModuleVisit(ModuleNameConstants.CRM);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.shell.view.FragmentNavigator;
import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class CrmModulePresenter extends AbstractPresenter<CrmModule> {
	private static final long serialVersionUID = 1L;

	public CrmModulePresenter() {
		super(CrmModule.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		MainView mainView = (MainView) container;
		mainView.addModule((IModule) view);

		String[] params = (String[]) data.getParams();
		if (params == null || params.length == 0) {
			view.gotoCrmDashboard();
		} else {
			FragmentNavigator.shellUrlResolver.getSubResolver("crm").handle(
					params);
		}

		AppContext.updateLastModuleVisit(ModuleNameConstants.CRM);
	}
}

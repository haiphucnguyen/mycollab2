/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.shell.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.UserPreference;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class MainViewPresenter extends AbstractPresenter<MainView> {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory
			.getLogger(MainViewPresenter.class);

	public MainViewPresenter() {
		super(MainView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		UserPreference pref = AppContext.getUserPreference();
		if (pref.getLastmodulevisit() == null
				|| ModuleNameConstants.CRM.equals(pref.getLastmodulevisit())) {
			// go to crm module
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoCrmPage(this, null));
		} else if (ModuleNameConstants.PRJ.equals(pref.getLastmodulevisit())) {
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoProjectPage(this, null));
		} else {
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoConsolePage(this, null));
			log.debug("Do not support navigate to module: "
					+ pref.getLastmodulevisit());
		}
	}
}

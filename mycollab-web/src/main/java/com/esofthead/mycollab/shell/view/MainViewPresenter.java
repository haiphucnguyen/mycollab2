/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.shell.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.domain.UserPreference;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
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
		// if user type remember URL, instead of going to main page, to to his
		// url
		String url = UI.getCurrent().getPage().getUriFragment();
		if (url != null && !url.equals("")) {
			if (url.startsWith("/")) {
				url = url.substring(1);
			}
			FragmentNavigator.navigateByFragement(url);
		} else {
			UserPreference pref = AppContext.getUserPreference();
			if (pref.getLastmodulevisit() == null
					|| ModuleNameConstants.PRJ
							.equals(pref.getLastmodulevisit())) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoProjectModule(this, null));
			} else if (ModuleNameConstants.CRM
					.equals(pref.getLastmodulevisit())) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoCrmModule(this, null));
			} else if (ModuleNameConstants.ACCOUNT.equals(pref
					.getLastmodulevisit())) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoUserAccountModule(this, null));
			} else if (ModuleNameConstants.FILE.equals(pref
					.getLastmodulevisit())) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoFileModule(this, null));
			} else {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoConsolePage(this, null));
				log.debug("Do not support navigate to module: "
						+ pref.getLastmodulevisit());
			}
		}
	}
}

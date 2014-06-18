/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.shell;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.configuration.PasswordEncryptHelper;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.MobileApplication;
import com.esofthead.mycollab.mobile.module.crm.events.CrmEvent;
import com.esofthead.mycollab.mobile.module.user.events.UserEvent;
import com.esofthead.mycollab.mobile.module.user.view.LoginPresenter;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.mobile.shell.ui.MainViewPresenter;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.UserPreference;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.module.user.service.UserPreferenceService;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.vaadin.addon.touchkit.extensions.LocalStorage;
import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class ShellController implements IController {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ShellController.class);

	final private NavigationManager mainNav;

	public ShellController(NavigationManager navigationManager) {
		this.mainNav = navigationManager;
		bind();
	}

	private void bind() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoLoginView>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoLoginView.class;
					}

					@Override
					public void handle(ShellEvent.GotoLoginView event) {
						LoginPresenter presenter = PresenterResolver
								.getPresenter(LoginPresenter.class);
						presenter.go(mainNav, null);
					}

				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<UserEvent.PlainLogin>() {
					private static final long serialVersionUID = -6601631757376496199L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return UserEvent.PlainLogin.class;
					}

					@Override
					public void handle(UserEvent.PlainLogin event) {
						String[] data = (String[]) event.getData();
						doLogin(data[0], data[1], Boolean.valueOf(data[2]));
					}
				});
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoMainPage>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoMainPage.class;
					}

					@Override
					public void handle(ShellEvent.GotoMainPage event) {
						MainViewPresenter presenter = PresenterResolver
								.getPresenter(MainViewPresenter.class);
						presenter.go(mainNav, null);
					}

				});
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoCrmModule>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoCrmModule.class;
					}

					@Override
					public void handle(ShellEvent.GotoCrmModule event) {
						EventBus.getInstance().fireEvent(
								new CrmEvent.GotoHome(this, event.getData()));
					}
				});
	}

	public void doLogin(String username, String password,
			boolean isRememberPassword) {
		UserService userService = ApplicationContextUtil
				.getSpringBean(UserService.class);
		SimpleUser user = userService.authentication(username, password,
				AppContext.getSubDomain(), false);

		BillingAccountService billingAccountService = ApplicationContextUtil
				.getSpringBean(BillingAccountService.class);

		SimpleBillingAccount billingAccount = billingAccountService
				.getBillingAccountById(AppContext.getAccountId());

		log.debug("Get billing account successfully: "
				+ BeanUtility.printBeanObj(billingAccount));

		UserPreferenceService preferenceService = ApplicationContextUtil
				.getSpringBean(UserPreferenceService.class);
		UserPreference pref = preferenceService.getPreferenceOfUser(username,
				AppContext.getAccountId());

		log.debug("Login to system successfully. Save user and preference "
				+ pref + " to session");

		if (isRememberPassword) {
			LocalStorage storage = LocalStorage.get();
			String storeVal = username + "$"
					+ PasswordEncryptHelper.encyptText(password);
			storage.put(MobileApplication.LOGIN_DATA, storeVal);
		}

		AppContext.getInstance().setSession(user, pref, billingAccount);
		pref.setLastaccessedtime(new Date());
		preferenceService.updateWithSession(pref, AppContext.getUsername());
		EventBus.getInstance().fireEvent(
				new ShellEvent.GotoMainPage(this, null));
	}
}

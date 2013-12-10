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
package com.esofthead.mycollab.shell.view;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.configuration.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.view.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.shell.ShellController;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

public class MainWindowContainer extends CssLayout {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(MainWindowContainer.class);

	private static final String NAME_COOKIE = "mycollab";

	private boolean isAutoLogin;

	// private final UriFragmentUtility urifu;

	public MainWindowContainer() {

		// urifu = new UriFragmentUtility();
		//
		// urifu.addListener(new UriFragmentUtility.FragmentChangedListener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void fragmentChanged(FragmentChangedEvent source) {
		// log.debug("Change fragement: "
		// + source.getUriFragmentUtility().getFragment());
		// try {
		// String initialUrl = source.getUriFragmentUtility()
		// .getFragment();
		// if (AppContext.getSession() != null) {
		// FragmentNavigator.navigateByFragement(initialUrl);
		// } else {
		// ((MyCollabApplication) AppContext.getApplication())
		// .setInitialUrl(initialUrl);
		// }
		// } catch (Exception e) {
		// log.error("Error while change segment", e);
		// }
		//
		// }
		// });

		this.setCaption("MyCollab");
		ControllerRegistry.addController(new ShellController(this));

		this.setImmediate(true);

		isAutoLogin = true;
		setDefaultView();
	}

	public void setContent(ComponentContainer container) {
		this.removeAllComponents();
		this.addComponent(container);
	}

	public void unsetRememberPassword() {
		Cookie cookie = getCookieByName(NAME_COOKIE);

		if (cookie != null) {
			cookie.setValue("");
		}
	}

	public void rememberPassword(String username, String password) {
		// Remember password

		Cookie cookie = getCookieByName(NAME_COOKIE);
		if (cookie == null) {
			cookie = new Cookie(NAME_COOKIE, username + "$"
					+ PasswordEncryptHelper.encyptText(password));
		} else {
			cookie.setValue(username + "$"
					+ PasswordEncryptHelper.encyptText(password));
		}
		cookie.setMaxAge(60 * 60 * 24 * 7);
	}

	public void addFragement(String fragement) {
		Page.getCurrent().setUriFragment(fragement, false);
	}

	private final void setDefaultView() {
		final LoginPresenter presenter = PresenterResolver
				.getPresenter(LoginPresenter.class);
		LoginView loginView = presenter.getView();

		// Read previously stored cookie value
		if (isAutoLogin) {
			Cookie nameCookie = getCookieByName(NAME_COOKIE);
			if (nameCookie != null) {
				String loginInfo = nameCookie.getValue();
				if (loginInfo != null) {
					String[] loginParams = loginInfo.split("\\$");
					if (loginParams.length == 2) {
						presenter.doLogin(loginParams[0], PasswordEncryptHelper
								.decryptText(loginParams[1]), false);
					}
				}
			}
		}

		this.setStyleName("loginView");
		this.setContent(loginView.getWidget());
	}

	private Cookie getCookieByName(String name) {
		// Fetch all cookies from the request
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();

		// Iterate to find cookie by its name
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}

		return null;
	}

	public boolean isAutoLogin() {
		return isAutoLogin;
	}

	public void setAutoLogin(boolean isAutoLogin) {
		this.isAutoLogin = isAutoLogin;
	}
}

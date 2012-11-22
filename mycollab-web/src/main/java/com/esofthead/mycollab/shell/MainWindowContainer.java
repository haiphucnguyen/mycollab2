package com.esofthead.mycollab.shell;

import org.vaadin.browsercookies.BrowserCookies;

import com.esofthead.mycollab.module.user.presenter.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.module.user.view.LoginViewImpl;
import com.vaadin.ui.Window;

public class MainWindowContainer extends Window {
	private static final long serialVersionUID = 1L;

	private final ShellController controller;

	public BrowserCookies cookies;

	private LoginPresenter presenter;

	public MainWindowContainer() {
		this.setCaption("MyCollab");

		controller = new ShellController(this);
		cookies = new BrowserCookies();
		this.addComponent(cookies);
		// cookies.addListener(new BrowserCookies.UpdateListener() {
		//
		// public void cookiesUpdated(BrowserCookies bc) {
		// for (String name : bc.getCookieNames()) {
		// if(name.equals("loginInfo")) {
		// String loginInfo = bc.getCookie(name);
		// if(loginInfo != null) {
		// String[] loginParams = loginInfo.split("\\$");
		// if(loginParams.length == 2) {
		// LoginPresenter presenter = new LoginPresenter
		// }
		// }
		// }
		// }
		//
		// }
		// });

		LoginView loginView = new LoginViewImpl();
		LoginPresenter presenter = new LoginPresenter(loginView);
		this.setContent(loginView.getWidget());
	}
}

package com.esofthead.mycollab.shell;

import org.vaadin.browsercookies.BrowserCookies;

import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.module.user.presenter.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.module.user.view.LoginViewImpl;
import com.vaadin.ui.Window;

public class MainWindowContainer extends Window {
	private static final long serialVersionUID = 1L;

	private final ShellController controller;

	public LoginPresenter presenter;

	public MainWindowContainer() {
		this.setCaption("MyCollab");

		controller = new ShellController(this);

		LoginView loginView = new LoginViewImpl();
		presenter = new LoginPresenter(loginView);
		BrowserCookies cookies = new BrowserCookies();
		((LoginViewImpl) loginView).addComponent(cookies);
		cookies.addListener(new BrowserCookies.UpdateListener() {

			@Override
			public void cookiesUpdated(BrowserCookies bc) {
				for (String name : bc.getCookieNames()) {
					if (name.equals("loginInfo")) {
						String loginInfo = bc.getCookie(name);
						if (loginInfo != null) {
							String[] loginParams = loginInfo.split("\\$");
							if (loginParams.length == 2) {
								try {
									MainWindowContainer.this.presenter.doLogin(
											loginParams[0], loginParams[1]);
								} catch (EngroupException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}

			}
		});

		this.setContent(loginView.getWidget());
	}
}

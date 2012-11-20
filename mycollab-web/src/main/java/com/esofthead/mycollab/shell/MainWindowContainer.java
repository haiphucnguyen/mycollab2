package com.esofthead.mycollab.shell;

import com.esofthead.mycollab.module.user.presenter.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.module.user.view.LoginViewImpl;
import com.vaadin.ui.Window;

public class MainWindowContainer extends Window {
	private static final long serialVersionUID = 1L;

	private ShellController controller;

	public MainWindowContainer() {
		this.setCaption("MyCollab");

		controller = new ShellController(this);

		LoginView loginView = new LoginViewImpl();
		LoginPresenter presenter = new LoginPresenter(loginView);
		this.setContent(loginView.getWidget());
	}
}

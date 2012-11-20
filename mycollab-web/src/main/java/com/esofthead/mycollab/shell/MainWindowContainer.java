package com.esofthead.mycollab.shell;

import com.esofthead.mycollab.module.user.presenter.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.module.user.view.LoginViewImpl;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MainWindowContainer extends Window {
	private static final long serialVersionUID = 1L;

	private ShellController controller;

	private VerticalLayout content;

	public MainWindowContainer() {
		this.setCaption("MyCollab");

		content = new VerticalLayout();
		content.setSizeFull();
		this.setContent(content);

		controller = new ShellController(content);

		LoginView loginView = new LoginViewImpl();
		LoginPresenter presenter = new LoginPresenter(loginView, null);
		content.addComponent(loginView.getWidget());
	}
}

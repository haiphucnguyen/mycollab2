package com.esofthead.mycollab.module.user.presenter;

import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.SecurityService;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class LoginPresenter implements Presenter {

	private LoginView view;

	public LoginPresenter(LoginView view) {
		this.view = view;
		view.setPresenter(this);
	}

	public void doLogin(String username, String password) {
		try {
			SecurityService securityService = AppContext
					.getSpringBean(SecurityService.class);
			SimpleUser authentication = securityService.authentication(
					username, password);
			AppContext.setSession(authentication);
			EventBus.getInstance().fireEvent(new ShellEvent.GotoMainPage(this, null));
		} catch (EngroupException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void go(ComponentContainer container) {
		container.removeAllComponents();
		container.addComponent(view.getWidget());
	}
}

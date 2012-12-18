package com.esofthead.mycollab.module.user.presenter;

import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.SecurityService;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class LoginPresenter extends AbstractPresenter<LoginView> {
	private static final long serialVersionUID = 1L;

	public LoginPresenter() {
		super(LoginView.class);
		view.setPresenter(this);
	}

	public void doLogin(String username, String password) {
		try {
			SecurityService securityService = AppContext
					.getSpringBean(SecurityService.class);
			SimpleUser authentication = securityService.authentication(
					username, password);
			AppContext.setSession(authentication);
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoMainPage(this, null));
		} catch (EngroupException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		container.removeAllComponents();
		container.addComponent(view.getWidget());
	}
}

package com.esofthead.mycollab.module.user.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.SecurityService;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter.ViewInterface;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
@Component
@ViewInterface(LoginView.class)
public class LoginPresenter extends AbstractPresenter<LoginView> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private SecurityService securityService;

	public void doLogin(String username, String password) {
		try {
			SimpleUser authentication = securityService.authentication(
					username, password);
			AppContext.setSession(authentication);
			((LoginView) view).loginSuccess();
		} catch (EngroupException e) {
			e.printStackTrace();
		}
	}
}

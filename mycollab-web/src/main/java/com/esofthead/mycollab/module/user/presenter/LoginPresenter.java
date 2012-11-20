package com.esofthead.mycollab.module.user.presenter;

import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.SecurityService;
import com.esofthead.mycollab.web.AppContext;

public class LoginPresenter {

	public void doLogin(String username, String password) {
		try {
			SecurityService securityService = AppContext
					.getSpringBean(SecurityService.class);
			SimpleUser authentication = securityService.authentication(
					username, password);
			AppContext.setSession(authentication);
		} catch (EngroupException e) {
			e.printStackTrace();
		}
	}
}

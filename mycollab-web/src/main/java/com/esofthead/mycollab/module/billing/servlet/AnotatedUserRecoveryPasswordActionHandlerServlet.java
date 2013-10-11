package com.esofthead.mycollab.module.billing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.PasswordCheckerUtil;

@Component("updateUserPasswordServlet")
public class AnotatedUserRecoveryPasswordActionHandlerServlet implements
		HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String errMsg = "";

		String username = request.getParameter("username");

		String password = request.getParameter("password");

		if (password.length() < 8) {
			errMsg = "Your password is too short";
			PrintWriter out = response.getWriter();
			out.print(errMsg);
			return;
		} else if (!PasswordCheckerUtil.checkPasswordStrength(password)) {
			errMsg = "Your password must contain at least one digit, character and symbol";
			PrintWriter out = response.getWriter();
			out.print(errMsg);
			return;
		}
		SimpleUser simpleUser = new SimpleUser();
		simpleUser.setPassword(PasswordEncryptHelper
				.encryptSaltPassword(password));
		simpleUser.setRegisterstatus(RegisterStatusConstants.ACTIVE);
		simpleUser.setUsername(username);

		try {
			UserService userService = ApplicationContextUtil
					.getSpringBean(UserService.class);
			userService.updateWithSession(simpleUser, username);
		} catch (Exception e) {
			errMsg = LocalizationHelper
					.getMessage(GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE);
			PrintWriter out = response.getWriter();
			out.print(errMsg);
			return;
		}
	}

}

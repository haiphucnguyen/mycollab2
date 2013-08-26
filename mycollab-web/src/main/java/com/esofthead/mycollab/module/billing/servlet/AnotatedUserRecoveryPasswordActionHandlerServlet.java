package com.esofthead.mycollab.module.billing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.utils.PasswordCheckerUtil;
import com.esofthead.mycollab.web.AppContext;

@Component("userRecoveryPasswordActionHandlerServlet")
public class AnotatedUserRecoveryPasswordActionHandlerServlet implements
		HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Boolean error = false;
		String errMsg = "";

		String username = request.getParameter("username");

		String password = request.getParameter("password");
		// Integer roleId = Integer.parseInt(request.getParameter("roleId"));

		if (password.length() < 8) {
			error = true;
			errMsg = "Your password too short";
		} else if (PasswordCheckerUtil.checkPasswordStrength(password)) {
			error = true;
			errMsg = "Recommend you should type password at least contain one digit and symbol";
		}
		if (error) {
			PrintWriter out = response.getWriter();
			out.print(errMsg);
			return;
		}
		SimpleUser simpleUser = new SimpleUser();
		simpleUser.setPassword(PasswordEncryptHelper
				.encryptSaltPassword(password));
		// simpleUser.setRoleid(roleId);
		simpleUser.setRegisterstatus(RegisterStatusConstants.ACTIVE);
		simpleUser.setUsername(username);

		try {
			UserService userService = AppContext
					.getSpringBean(UserService.class);
			userService.updateWithSession(simpleUser, username);
			// response.sendRedirect(request.getContextPath() + "/");
		} catch (Exception e) {
			error = true;
			errMsg = "Error in while update your informations. We so sorry for this inconvenience";
		}
		if (error) {
			PrintWriter out = response.getWriter();
			out.print(errMsg);
			return;
		}
		PrintWriter out = response.getWriter();
		out.print(errMsg);
	}

}

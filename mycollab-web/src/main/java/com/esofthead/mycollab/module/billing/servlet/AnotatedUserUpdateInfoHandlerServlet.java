package com.esofthead.mycollab.module.billing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.PasswordCheckerUtil;

@Component("userUpdateInfoHandlerServlet")
public class AnotatedUserUpdateInfoHandlerServlet implements HttpRequestHandler {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Boolean error = false;
		String errMsg = "";

		String username = request.getParameter("username");
		int sAccountId = Integer.parseInt(request.getParameter("accountId"));

		String password = request.getParameter("password");

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
		simpleUser.setAccountId(sAccountId);
		simpleUser.setRegisterstatus(RegisterStatusConstants.ACTIVE);
		simpleUser.setUsername(username);

		try {
			UserService userService = ApplicationContextUtil
					.getBean(UserService.class);
			userService.updateWithSession(simpleUser, username);
		} catch (Exception e) {
			errMsg = "Error in while update your informations. We so sorry for this inconvenience";
			PrintWriter out = response.getWriter();
			out.print(errMsg);
			return;
		}
	}
}

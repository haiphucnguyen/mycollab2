package com.esofthead.mycollab.module.billing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.PasswordCheckerUtil;

@Component("userUpdateInfoHandlerServlet")
public class AnotatedUserUpdateInfoHandlerServlet implements HttpRequestHandler {
	private static Logger log = LoggerFactory
			.getLogger(AnotatedUserUpdateInfoHandlerServlet.class);

	@Autowired
	private UserMapper userMapper;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String errMsg = "";

		String username = request.getParameter("username");
		int sAccountId = Integer.parseInt(request.getParameter("accountId"));

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

		User user = new User();
		user.setPassword(PasswordEncryptHelper.encryptSaltPassword(password));
		user.setUsername(username);

		try {
			log.debug("Update password of user {}", username);
			UserService userService = ApplicationContextUtil
					.getBean(UserService.class);
			userService.updateWithSession(user, username);
		} catch (Exception e) {
			errMsg = LocalizationHelper
					.getMessage(GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE);
			PrintWriter out = response.getWriter();
			out.print(errMsg);
			log.error("Error while update user information", e);
			return;
		}
	}
}

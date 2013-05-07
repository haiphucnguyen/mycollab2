package com.esofthead.mycollab.module.billing.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.service.UserService;

@Component("verifyUserServletHandler")
public class AnotatedVerifyUserServletRequestHandler implements
		HttpRequestHandler {

	private static Logger log = LoggerFactory
			.getLogger(AnotatedVerifyUserServletRequestHandler.class);

	@Autowired
	private UserService userService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);
				pathInfo = UrlEncodeDecoder.decode(pathInfo);
				String[] params = pathInfo.split("/");
				if (params.length != 2) {
					throw new MyCollabException("Invalid params");
				} else {
					//TODO: Adjust register service
//					int accountId = Integer.parseInt(params[0]);
//					String username = params[1];
//					SimpleUser user = userService.findUserByUserName(username);
//					user.setRegisterstatus(RegisterStatusConstants.ACTIVE);
//					user.setPassword("123456");
//					userService.updateWithSession(user, user.getUsername());
//					request.getRequestDispatcher(request.getContextPath() + "/")
//							.forward(request, response);
//					request.setAttribute("username", user.getUsername());
//					request.setAttribute("password", user.getPassword());
				}
			}
		} else {
			// TODO: response to user invalid page
		}
	}
}

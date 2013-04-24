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
import com.esofthead.mycollab.core.UserInvalidInputException;
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
				String username = UrlEncodeDecoder.decode(pathInfo);
				try {
					userService.verifyUser(username);

					log.debug("Verify user successfully. Redirect to application page");
					response.sendRedirect(request.getContextPath() + "/");
				} catch (UserInvalidInputException e) {
					log.debug("Redirect user to user invalid page");
				}
			}
		} else {
			// TODO: response to user invalid page
		}
	}

}

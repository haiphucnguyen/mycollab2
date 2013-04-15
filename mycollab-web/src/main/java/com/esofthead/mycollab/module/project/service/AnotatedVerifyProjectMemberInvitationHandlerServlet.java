package com.esofthead.mycollab.module.project.service;

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
import com.esofthead.mycollab.module.user.service.UserService;

@Component("confirmInvitationMemberServletHandler")
public class AnotatedVerifyProjectMemberInvitationHandlerServlet implements HttpRequestHandler {

private static Logger log = LoggerFactory.getLogger(AnotatedVerifyProjectMemberInvitationHandlerServlet.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);
				String username = UrlEncodeDecoder.decode(pathInfo);
				log.debug("Username: " + username);
			}
		} else {
			// TODO: response to user invalid page
		}
	}

}

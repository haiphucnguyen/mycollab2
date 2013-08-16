package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.module.mail.service.MailRelayService;

@Component("denyInvitationHandleServlet")
public class AnotatedDenyInvitationHandleServlet implements HttpRequestHandler {

	@Autowired
	private MailRelayService mailRelayService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse respone) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			pathInfo = pathInfo.substring(1);
			String toEmail = pathInfo.substring(0, pathInfo.indexOf("/"));
			pathInfo = pathInfo.substring(toEmail.length() + 1);

			String fromEmail = pathInfo.substring(0, pathInfo.indexOf("/"));
			pathInfo = pathInfo.substring(fromEmail.length() + 1);

			String message = pathInfo.substring(0, pathInfo.indexOf("/"));
			pathInfo = pathInfo.substring(message.length() + 1);
			String fromName = pathInfo.substring(0, pathInfo.indexOf("/"));
			pathInfo = pathInfo.substring(fromName.length() + 1);

			String toName = pathInfo;

			mailRelayService.saveRelayEmail(new String[] { toName },
					new String[] { toEmail }, "Your invitation has been deny",
					message);
		}
	}
}

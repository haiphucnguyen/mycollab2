package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.module.mail.service.MailRelayService;

@Component("memberDenyInvitationFeedBackHandlerServlet")
public class AnotatedMemberDenyInvitationFeedBackHandlerServlet implements
		HttpRequestHandler {

	@Autowired
	private MailRelayService mailRelayService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse respone) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			String inviterEmail = request.getParameter("inviterEmail");
			String toEmail = request.getParameter("toEmail");
			String message = request.getParameter("message");
			String toName = request.getParameter("toName");
			String inviterName = request.getParameter("inviterName");

			mailRelayService.saveRelayEmail(new String[] { toName },
					new String[] { toEmail }, "Your invitation has been deny",
					message);
		}
	}
};

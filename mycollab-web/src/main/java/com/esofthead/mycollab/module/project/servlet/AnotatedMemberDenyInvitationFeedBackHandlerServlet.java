package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.module.mail.service.RelayEmailService;

@Component("memberDenyInvitationFeedBackHandlerServlet")
public class AnotatedMemberDenyInvitationFeedBackHandlerServlet implements
		HttpRequestHandler {

	@Autowired
	private RelayEmailService mailRelayService;

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
			if (inviterName.indexOf("/") != -1) {
				inviterName = inviterName
						.substring(0, inviterName.indexOf("/"));
			}
			toName = (toName.equals("You")) ? "" : toName;
			mailRelayService.saveRelayEmail(new String[] { inviterName },
					new String[] { inviterEmail }, toName + "(" + toEmail + ")"
							+ " has denied your invitation", message);
		}
	}
};

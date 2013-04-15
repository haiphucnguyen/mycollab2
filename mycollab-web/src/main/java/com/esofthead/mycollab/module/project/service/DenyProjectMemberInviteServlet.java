package com.esofthead.mycollab.module.project.service;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.support.HttpRequestHandlerServlet;

@WebServlet(description = "Http Servlet using pure java / annotations", urlPatterns = { "/project/member/invitation/deny_invite/*" }, name = "denyInvitationMemberServletHandler")
public class DenyProjectMemberInviteServlet extends HttpRequestHandlerServlet {
	private static final long serialVersionUID = 1L;

}

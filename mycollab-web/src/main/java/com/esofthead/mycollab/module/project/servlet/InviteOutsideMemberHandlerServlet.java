package com.esofthead.mycollab.module.project.servlet;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.support.HttpRequestHandlerServlet;

@WebServlet(description = "Http Servlet using pure java / annotations", urlPatterns = { "/project/outside/invitation/*" }, name = "inviteOutsideMemberHandlerServlet")
public class InviteOutsideMemberHandlerServlet extends
		HttpRequestHandlerServlet {
	private static final long serialVersionUID = 1L;
}

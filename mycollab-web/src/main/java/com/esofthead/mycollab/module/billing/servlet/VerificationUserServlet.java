package com.esofthead.mycollab.module.billing.servlet;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.support.HttpRequestHandlerServlet;

@WebServlet(description = "Http Servlet using pure java / annotations", urlPatterns = { "/user/confirm_invite/*" }, name = "verifyUserServletHandler")
public class VerificationUserServlet extends HttpRequestHandlerServlet {
	private static final long serialVersionUID = 1L;
}

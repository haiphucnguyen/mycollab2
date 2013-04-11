package com.esofthead.mycollab.module.billing.service;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.support.HttpRequestHandlerServlet;

@WebServlet(description = "Http Servlet using pure java / annotations", urlPatterns = { "/verify_user/*" }, name = "verifyUserServletHandler")
public class VerificationUserServlet extends HttpRequestHandlerServlet {
	private static final long serialVersionUID = 1L;
}

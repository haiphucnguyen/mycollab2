package com.esofthead.mycollab.module.billing.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

@WebServlet(description = "Http Servlet using pure java / annotations", urlPatterns = { "/verify_user" }, name = "verifyUserServlet")
public class VerificationUserServlet extends HttpRequestHandlerServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(VerificationUserServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("Verify user: " + req.getPathInfo());

		super.doGet(req, resp);
	}
}

package com.esofthead.mycollab.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/oauth2/google", loadOnStartup = 1)
public class GoogleCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(GoogleCallbackServlet.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("google callback");
	}
}

package com.esofthead.mycollab.module.file.servlet;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.support.HttpRequestHandlerServlet;

@WebServlet(urlPatterns = "/drive/ggDriveAuth", name = "ggDriveAuthServlet")
public class GoogleDriveHandlerAuthServlet extends HttpRequestHandlerServlet {
	private static final long serialVersionUID = 1L;

}

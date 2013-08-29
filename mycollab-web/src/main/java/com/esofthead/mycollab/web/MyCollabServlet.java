package com.esofthead.mycollab.web;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.vaadin.artur.icepush.ICEPushServlet;

public class MyCollabServlet extends ICEPushServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void writeAjaxPageHtmlHeader(BufferedWriter page, String title,
			String themeUri, HttpServletRequest request) throws IOException {
		super.writeAjaxPageHtmlHeader(page, title, themeUri, request);
		page.append("<meta name=\"robots\" content=\"nofollow\" />");
	}

}

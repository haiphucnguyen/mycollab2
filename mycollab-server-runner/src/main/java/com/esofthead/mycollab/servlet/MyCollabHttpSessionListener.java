package com.esofthead.mycollab.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class MyCollabHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		final HttpSession session = se.getSession();
		final ServletContext context = session.getServletContext();
		context.setAttribute(session.getId(), session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		final HttpSession session = se.getSession();
		final ServletContext context = session.getServletContext();
		context.removeAttribute(session.getId());
	}

}

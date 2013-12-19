package com.esofthead.mycollab.web;

import javax.servlet.ServletException;

import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class MyCollabServlet extends VaadinServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();
		getService().addSessionInitListener(new SessionInitListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void sessionInit(SessionInitEvent event) {
				event.getSession().addBootstrapListener(
						new MyCollabBootstrapListener());
			}
		});
	}
}

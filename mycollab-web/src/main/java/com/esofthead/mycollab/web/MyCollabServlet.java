package com.esofthead.mycollab.web;

import javax.servlet.ServletException;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class MyCollabServlet extends TouchKitServlet {
	private static final long serialVersionUID = 1L;

	private MyCollabUIProvider uiProvider = new MyCollabUIProvider();

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();
		getService().addSessionInitListener(new SessionInitListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void sessionInit(SessionInitEvent event) {
				event.getSession().addBootstrapListener(
						new MyCollabBootstrapListener());

				event.getSession().addUIProvider(uiProvider);
			}
		});
	}
}

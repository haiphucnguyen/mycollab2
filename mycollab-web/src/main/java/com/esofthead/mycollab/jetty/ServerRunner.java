package com.esofthead.mycollab.jetty;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.SessionTrackingMode;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.esofthead.mycollab.vaadin.mvp.ViewManager;

public class ServerRunner {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		String webappDirLocation = "src/main/webapp/";

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setWar(webappDirLocation);
		webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
		webapp.setResourceBase(webappDirLocation);
		Set<SessionTrackingMode> modes = new HashSet<SessionTrackingMode>();
		modes.add(SessionTrackingMode.COOKIE);
		modes.add(SessionTrackingMode.URL);

		webapp.getSessionHandler().getSessionManager()
				.setSessionTrackingModes(modes);
		server.setHandler(webapp);

		server.start();

		server.join();
	}

}

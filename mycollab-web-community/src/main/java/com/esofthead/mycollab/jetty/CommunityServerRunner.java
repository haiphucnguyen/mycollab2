package com.esofthead.mycollab.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class CommunityServerRunner {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		String webappDirLocation = "src/main/webapp/";
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setWar(webappDirLocation);
		webAppContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		webAppContext.setResourceBase(webappDirLocation);

		server.setHandler(webAppContext);

		server.start();

		server.join();
	}

}

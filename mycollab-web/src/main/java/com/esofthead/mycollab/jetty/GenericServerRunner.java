package com.esofthead.mycollab.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public abstract class GenericServerRunner {
	private Server server;

	public GenericServerRunner() {
		server = new Server(8080);
		String webappDirLocation = "src/main/webapp/";
		server.setHandler(buildContext(webappDirLocation));
	}

	public abstract WebAppContext buildContext(String baseDir);

	public void start() throws Exception {
		server.start();
		server.join();
	}

	public void stop() throws Exception {
		server.stop();
	}
}

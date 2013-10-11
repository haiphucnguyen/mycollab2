package com.esofthead.mycollab.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class ServerRunner {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		String webappDirLocation = "src/main/webapp/";
		// Configure webapp provided as external WAR
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setWar(webappDirLocation);
		webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
		webapp.setResourceBase(webappDirLocation);
		// webapp.setConfigurations(new Configuration[] {
		// new AnnotationConfiguration(), new WebXmlConfiguration(),
		// new WebInfConfiguration(), new TagLibConfiguration(),
		// new PlusConfiguration(), new MetaInfConfiguration(),
		// new FragmentConfiguration(), new EnvConfiguration() });
		// webapp.setParentLoaderPriority(true);

		server.setDumpAfterStart(true);
		server.setDumpBeforeStop(true);
		server.setHandler(webapp);

		server.start();
		server.join();
	}

}

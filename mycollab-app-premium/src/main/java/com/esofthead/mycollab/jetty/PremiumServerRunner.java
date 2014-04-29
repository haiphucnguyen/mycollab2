package com.esofthead.mycollab.jetty;

import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import com.esofthead.mycollab.jetty.clustering.InfinispanSessionManager;
import com.esofthead.mycollab.servlet.InstallationServlet;
import com.esofthead.mycollab.servlet.SetupServlet;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class PremiumServerRunner extends GenericServerRunner {

	@Override
	public WebAppContext buildContext(String baseDir) {
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setWar(baseDir);
		webAppContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		webAppContext.setResourceBase(baseDir);

		// create a InfinispanSessionManager instance
		InfinispanSessionManager sm = new InfinispanSessionManager();

		// apply the session manager to the jetty server
		SessionHandler sh = new SessionHandler();
		sh.setSessionManager(sm);

		webAppContext.setSessionHandler(sh);

		webAppContext.addServlet(new ServletHolder(new SetupServlet()),
				"/setup");
		webAppContext.addServlet(new ServletHolder(new InstallationServlet()),
				"/install");
		return webAppContext;
	}

	public static void main(String[] args) throws Exception {
		new PremiumServerRunner().run(args);
	}

}

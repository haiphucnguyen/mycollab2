package com.esofthead.mycollab.jetty;

import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import com.esofthead.mycollab.jetty.clustering.InfinispanSessionManager;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class OnDemandServerRunner extends GenericServerRunner {

	@Override
	public WebAppContext buildContext(String baseDir) {
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setWar(baseDir);
		webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		webAppContext.setResourceBase(baseDir);

		// create a InfinispanSessionManager instance
		InfinispanSessionManager sm = new InfinispanSessionManager();

		// apply the session manager to the jetty server
		SessionHandler sh = new SessionHandler();
		sh.setSessionManager(sm);

		webAppContext.setSessionHandler(sh);
		return webAppContext;
	}

	public static void main(String[] args) throws Exception {
		new OnDemandServerRunner().run(args);
	}
}

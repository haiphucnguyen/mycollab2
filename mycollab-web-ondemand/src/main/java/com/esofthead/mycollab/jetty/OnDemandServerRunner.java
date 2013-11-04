package com.esofthead.mycollab.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.infinispan.Cache;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.jetty.clustering.InfinispanHttpSession;
import com.esofthead.mycollab.jetty.clustering.InfinispanSessionManager;

public class OnDemandServerRunner {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		String webappDirLocation = "src/main/webapp/";
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setWar(webappDirLocation);
		webAppContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		webAppContext.setResourceBase(webappDirLocation);

		// add originSection clustering mode
		Cache<String, InfinispanHttpSession> cache = LocalCacheManager
				.getCacheManager().getCache("mainClustering");

		// create a InfinispanSessionManager instance
		InfinispanSessionManager sm = new InfinispanSessionManager(cache);

		// apply the session manager to the jetty server
		SessionHandler sh = new SessionHandler();
		sh.setSessionManager(sm);

		webAppContext.setSessionHandler(sh);
		// and start the cache
		cache.start();

		server.setHandler(webAppContext);

		server.start();

		server.join();
	}

}

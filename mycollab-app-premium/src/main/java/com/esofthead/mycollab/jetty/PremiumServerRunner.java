package com.esofthead.mycollab.jetty;

import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.infinispan.Cache;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.jetty.clustering.InfinispanHttpSession;
import com.esofthead.mycollab.jetty.clustering.InfinispanSessionManager;

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
		return webAppContext;
	}

	public static void main(String[] args) throws Exception {
		new PremiumServerRunner().run(args);
	}

}

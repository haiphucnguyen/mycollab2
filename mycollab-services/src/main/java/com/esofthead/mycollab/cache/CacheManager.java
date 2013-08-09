package com.esofthead.mycollab.cache;

import org.infinispan.api.BasicCache;
import org.infinispan.api.BasicCacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheManager {
	private static Logger log = LoggerFactory.getLogger(CacheManager.class);

	private static BasicCacheContainer instance;

	static {
		instance = new DefaultCacheManager();
		((DefaultCacheManager) instance).addListener(new CacheListener());
		// if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
		// try {
		// Configuration configuration = new ConfigurationBuilder()
		// .withProperties(SiteConfiguration.getCacheProperties())
		// .build();
		// instance = new RemoteCacheManager(configuration, true);
		// } catch (Exception e) {
		// log.error(
		// "Can not connect to remote cache, switch to local cache",
		// e);
		// instance = new DefaultCacheManager();
		// }
		// } else {
//		instance = new DefaultCacheManager();
//		((DefaultCacheManager) instance).addListener(new CacheListener());
		// try {
		// Configuration configuration = new ConfigurationBuilder()
		// .withProperties(SiteConfiguration.getCacheProperties())
		// .build();
		// instance = new RemoteCacheManager(configuration, true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
	}

	private static String GLOBAL_CACHE = "global";

	public static BasicCache<Object, Object> getCache(String id) {
		return instance.getCache(id);
	}

	public static BasicCache<Object, Object> getCache() {
		return instance.getCache(GLOBAL_CACHE);
	}

	public static void removeCache(String id) {
		BasicCache<Object, Object> cache = instance.getCache(id);
		cache.clear();
	}
}

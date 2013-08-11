package com.esofthead.mycollab.cache;

import org.infinispan.api.BasicCache;
import org.infinispan.api.BasicCacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalCacheManager {
	private static Logger log = LoggerFactory
			.getLogger(LocalCacheManager.class);

	private static BasicCacheContainer instance = new DefaultCacheManager();

	private static String GLOBAL_CACHE = "global";

	public static BasicCache<String, Object> getCache(String id) {
		return instance.getCache(id);
	}

	public static BasicCache<Object, Object> getCache() {
		return instance.getCache(GLOBAL_CACHE);
	}

	public static void removeCache(String id) {
		BasicCache<String, Object> cache = instance.getCache(id);
		cache.clear();
	}
}

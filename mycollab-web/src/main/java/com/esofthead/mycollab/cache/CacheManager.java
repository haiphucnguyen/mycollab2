package com.esofthead.mycollab.cache;

import java.io.IOException;
import java.io.InputStream;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.cache.mybatis.InfinispanCache;

public class CacheManager {
	private static Logger log = LoggerFactory.getLogger(CacheManager.class);

	private final static EmbeddedCacheManager CACHE_MANAGER = createCache();

	private static EmbeddedCacheManager createCache() {
		InputStream config = InfinispanCache.class
				.getResourceAsStream("infinispan.xml");
		EmbeddedCacheManager manager;
		if (config != null) {
			try {
				log.debug("Read configuration from infinispan.xml");
				manager = new DefaultCacheManager(config);
			} catch (IOException e) {
				log.error("Error while reading configuration file", e);
				throw new RuntimeException(e);
			}
		} else {
			log.debug("Using standard configuration");
			manager = new DefaultCacheManager();
		}
		return manager;
	}

	public static Cache<Object, Object> getCache(String id) {
		return CACHE_MANAGER.getCache(id);
	}
}

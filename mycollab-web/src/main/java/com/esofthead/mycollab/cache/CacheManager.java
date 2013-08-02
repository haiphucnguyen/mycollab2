package com.esofthead.mycollab.cache;

import java.io.IOException;
import java.io.InputStream;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheManager {
	private static Logger log = LoggerFactory.getLogger(CacheManager.class);

	private final static EmbeddedCacheManager CACHE_MANAGER = createCache("infinispan1.xml");

	private static String GLOBAL_CACHE = "global";

	public static EmbeddedCacheManager createCache(String configFile) {
		InputStream config = CacheManager.class.getClassLoader()
				.getResourceAsStream(configFile);
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
		manager.addListener(new CacheListener());
		return manager;
	}

	public static Cache<Object, Object> getCache(String id) {
		return CACHE_MANAGER.getCache(id);
	}

	public static Cache<Object, Object> getCache() {
		return CACHE_MANAGER.getCache(GLOBAL_CACHE);
	}

	public static void removeCache(String id) {
		CACHE_MANAGER.removeCache(id);
	}
}

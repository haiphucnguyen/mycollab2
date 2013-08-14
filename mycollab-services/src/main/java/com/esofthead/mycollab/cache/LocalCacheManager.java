package com.esofthead.mycollab.cache;

import java.io.InputStream;
import java.util.Set;

import org.infinispan.AdvancedCache;
import org.infinispan.api.BasicCache;
import org.infinispan.api.BasicCacheContainer;
import org.infinispan.context.Flag;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.configuration.DeploymentMode;
import com.esofthead.mycollab.configuration.SiteConfiguration;

public class LocalCacheManager {
	private static Logger log = LoggerFactory
			.getLogger(LocalCacheManager.class);

	private static BasicCacheContainer instance;

	static {
		try {
			InputStream configInputStream;
			if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
				configInputStream = LocalCacheManager.class.getClassLoader()
						.getResourceAsStream("infinispan.xml");
				instance = new DefaultCacheManager(configInputStream);
			} else {
				configInputStream = LocalCacheManager.class.getClassLoader()
						.getResourceAsStream("infinispan-local.xml");
				instance = new DefaultCacheManager(configInputStream);
			}
		} catch (Exception e) {
			log.debug(
					"Error while set up infinispan cache manager. Will initiate the default",
					e);
			instance = new DefaultCacheManager();
		}
	}

	private static String GLOBAL_CACHE = "global";

	public static BasicCache<String, Object> getCache(String id) {
		BasicCache<String, Object> cache = instance.getCache(id);
		if (cache instanceof AdvancedCache) {
			cache = ((AdvancedCache<String, Object>) cache)
					.withFlags(Flag.IGNORE_RETURN_VALUES);
		}
		return cache;
	}

	public static BasicCache<Object, Object> getCache() {
		return instance.getCache(GLOBAL_CACHE);
	}

	public static void removeCache(String id) {
		BasicCache<String, Object> cache = instance.getCache(id);
		cache.clear();
	}

	public static void removeCacheItems(String id, String prefixKey) {
		BasicCache<String, Object> cache = instance.getCache(id);
		log.debug("Remove cache has prefix {} in group {}", prefixKey, id);
		Set<String> keys = cache.keySet();
		if (keys != null && keys.size() > 0) {

			String[] keyArr = keys.toArray(new String[0]);
			for (int i = 0; i < keyArr.length; i++) {
				if (keyArr[i].startsWith(prefixKey)) {
					log.debug("Remove cache key {}", keyArr[i]);
					cache.remove(keyArr[i]);
				}
			}
		}
	}
}

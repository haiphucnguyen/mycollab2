package com.esofthead.mycollab.cache;

import org.infinispan.api.BasicCache;

public class InfinispanL2Cache implements L2Cache {
	private BasicCache<Object, Object> cache;

	public InfinispanL2Cache() {
		cache = LocalCacheManager.getCache();
	}

	@Override
	public Object get(String key) {
		return cache.get(key);
	}

	@Override
	public void put(String key, Object value) {
		cache.put(key, value);

	}
}

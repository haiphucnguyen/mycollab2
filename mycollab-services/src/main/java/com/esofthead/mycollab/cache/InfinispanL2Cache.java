package com.esofthead.mycollab.cache;

import org.infinispan.api.BasicCache;

public class InfinispanL2Cache implements L2Cache {
	private BasicCache<Object, Object> cache;

	public InfinispanL2Cache() {
		cache = LocalCacheManager.getCache();
	}
}

package com.esofthead.mycollab.cache.mybatis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;

import com.esofthead.mycollab.cache.CacheManager;

public class InfinispanCache implements org.apache.ibatis.cache.Cache {

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private static EmbeddedCacheManager cacheManager = CacheManager
			.createCache("infinispanDb.xml");

	private Cache<Object, Object> cache;
	private String id;

	public InfinispanCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		String newId = id;
		if (id.endsWith("Ext")) {
			newId = id.substring(0, id.length() - 3);
		}
		this.id = id;
		this.cache = cacheManager.getCache(newId);
	}

	public static void clearCache(String id) {
		Cache<Object, Object> dataCache = cacheManager.getCache(id);
		if (dataCache != null) {
			dataCache.clear();
		}
	}

	public void clear() {
		this.cache.clear();
	}

	public String getId() {
		return this.id;
	}

	public Object getObject(Object key) {
		return this.cache.get(key);
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public int getSize() {
		return this.cache.size();
	}

	public void putObject(Object key, Object value) {
		this.cache.put(key, value);
	}

	public Object removeObject(Object key) {
		return this.cache.remove(key);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof org.apache.ibatis.cache.Cache))
			return false;
		return this.id.equals(((org.apache.ibatis.cache.Cache) obj).getId());
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public String toString() {
		return InfinispanCache.class + "@" + this.id;
	}

}

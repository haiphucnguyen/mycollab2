package com.esofthead.mycollab.cache.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfinispanCache implements org.apache.ibatis.cache.Cache {

	private final static Logger log = LoggerFactory
			.getLogger(InfinispanCache.class);

	private final static EmbeddedCacheManager CACHE_MANAGER = createCache();

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	Cache<Object, Object> cache;
	private String id;

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

	public InfinispanCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		String newId = id;
		if (id.endsWith("Ext")) {
			newId = id.substring(0, id.length()-3);
		}
		this.id = id;
		this.cache = CACHE_MANAGER.getCache(newId);
	}

	public void clear() {
		this.cache.clear();
	}

	public String getId() {
		return this.id;
	}

	public Object getObject(Object key) {
//		Object result = this.cache.get(arg0);
//		log.debug("Asking for " + arg0 + ". Result is " + result);
		return this.cache.get(key);
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public int getSize() {
		return this.cache.size();
	}

	public void putObject(Object key, Object value) {
//		log.debug("Put (" + key + ", " + value + ") into cache");
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

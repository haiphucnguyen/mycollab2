package com.esofthead.mycollab.cache;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStarted;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStopped;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStoppedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener
public class CacheListener {
	private static Logger log = LoggerFactory.getLogger(CacheListener.class);

	@CacheEntryCreated
	public void dataAdded(CacheEntryCreatedEvent event) {
		if (event.isPre()) {
			log.debug("Going to add new entry {} created in the cache",
					event.getKey());
		} else {
			log.debug("Added new entry {} to the cache", event.getKey());
		}
	}

	@CacheEntryRemoved
	public void dataRemoved(CacheEntryRemovedEvent event) {
		if (event.isPre()) {
			log.debug("Going to remove entry {} created in the cache",
					event.getKey());
		} else {
			log.debug("Removed entry {} from the cache", event.getKey());
		}
	}

	@CacheStarted
	public void cacheStarted(CacheStartedEvent event) {
		log.debug("Cache Started");
	}

	@CacheStopped
	public void cacheStopped(CacheStoppedEvent event) {
		log.debug("Cache Stopped");
	}
}

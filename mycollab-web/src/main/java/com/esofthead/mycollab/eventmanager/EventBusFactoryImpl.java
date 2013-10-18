package com.esofthead.mycollab.eventmanager;

import org.infinispan.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.web.AppContext;

class EventBusFactoryImpl implements EventBusFactory {
	private static Logger log = LoggerFactory
			.getLogger(EventBusFactoryImpl.class);

	private static final String EVENT_BUS_VAL = "eventBusVal";

	public EventBus getInstance() {
		EventBus eventBus = (EventBus) AppContext.getVariable(EVENT_BUS_VAL);
		log.debug("Event bus {}", eventBus);
		if (eventBus == null) {
			eventBus = new EventBusImpl();
			AppContext.putVariable(EVENT_BUS_VAL, eventBus);
			log.debug("Create new event bus {}", eventBus);
		}
		return eventBus;
	}

	public EventBus getInstanceSession(String appId) {
		BasicCache<String, Object> cache = LocalCacheManager.getCache(appId);
		EventBus eventBus = (EventBus) cache.get(EVENT_BUS_VAL);
		return eventBus;
	}
}

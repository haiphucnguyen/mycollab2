/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.eventmanager;

import org.infinispan.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.web.MyCollabApplication;

/**
 * 
 * @author haiphucnguyen
 *
 */
class EventBusFactoryImpl implements EventBusFactory {
	private static Logger log = LoggerFactory
			.getLogger(EventBusFactoryImpl.class);

	private static final String EVENT_BUS_VAL = "eventBusVal";

	public EventBus getInstance() {
		EventBus eventBus = (EventBus) MyCollabApplication.getVariable(EVENT_BUS_VAL);
		log.debug("Event bus {}", eventBus);
		if (eventBus == null) {
			eventBus = new EventBusImpl();
			MyCollabApplication.putVariable(EVENT_BUS_VAL, eventBus);
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

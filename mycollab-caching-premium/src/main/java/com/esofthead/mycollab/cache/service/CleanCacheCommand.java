package com.esofthead.mycollab.cache.service;

import com.esofthead.mycollab.cache.CleanCacheEvent;
import com.esofthead.mycollab.module.GenericCommand;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@Component
public class CleanCacheCommand extends GenericCommand {

    @Autowired
    private CacheService cacheService;

    @AllowConcurrentEvents
    @Subscribe
    public void cleanCaches(CleanCacheEvent event) {
        cacheService.removeCacheItems(event.sAccountId().toString(), event.cls());
    }
}

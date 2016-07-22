package com.mycollab.premium.cache.service;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.mycollab.cache.CleanCacheEvent;
import com.mycollab.cache.service.CacheService;
import com.mycollab.module.esb.GenericCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@Component
public class CleanCacheCommand extends GenericCommand {

    private static final Logger LOG = LoggerFactory.getLogger(CleanCacheCommand.class);

    @Autowired
    private CacheService cacheService;

    @AllowConcurrentEvents
    @Subscribe
    public void cleanCaches(CleanCacheEvent event) {
        try {
            cacheService.removeCacheItems(event.sAccountId().toString(), event.cls());
        } catch (ExecutionException e) {
            LOG.error("Error", e);
        }
    }
}

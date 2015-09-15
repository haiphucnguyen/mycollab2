package com.esofthead.mycollab.cache.service;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.cache.CleanCacheEvent;
import com.esofthead.mycollab.module.GenericCommand;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@Component
public class CleanCacheCommand extends GenericCommand {
    @AllowConcurrentEvents
    @Subscribe
    public void cleanCaches(CleanCacheEvent event) {
        CacheUtils.cleanCaches(event.sAccountId(), event.cls());
    }
}

package com.mycollab.module.common.esb

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.cache.CleanCacheEvent
import com.mycollab.cache.service.CacheService
import com.mycollab.module.esb.GenericCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
@Component
class CleanCacheCommand extends GenericCommand {
  @Autowired private val cacheService: CacheService = null
  
  @AllowConcurrentEvents
  @Subscribe def cleanCaches(event: CleanCacheEvent) {
    cacheService.removeCacheItems(event.sAccountId.toString, event.cls: _*)
  }
}

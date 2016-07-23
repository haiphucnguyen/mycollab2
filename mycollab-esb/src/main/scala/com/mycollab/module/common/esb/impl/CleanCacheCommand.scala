package com.mycollab.module.common.esb.impl

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.cache.CleanCacheEvent
import com.mycollab.cache.service.CacheService
import com.mycollab.module.esb.GenericCommand
import org.springframework.beans.factory.annotation.Autowired

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class CleanCacheCommand extends GenericCommand {
  @Autowired private val cacheService: CacheService = null
  
  @AllowConcurrentEvents
  @Subscribe def cleanCaches(event: CleanCacheEvent) {
    cacheService.removeCacheItems(event.sAccountId.toString, event.cls :_*)
  }
}

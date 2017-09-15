package com.mycollab.cache.service.impl

import com.mycollab.cache.service.CacheService
import org.infinispan.AdvancedCache
import org.infinispan.commons.api.BasicCache
import org.infinispan.context.Flag
import org.infinispan.manager.EmbeddedCacheManager
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
@Service
@Profile("production")
class InfinispanCacheService(private val instance: EmbeddedCacheManager) : CacheService {

    override fun putValue(group: String, key: String, value: Any) {
        val cache = getCache(group)
        cache.put(key, value)
    }

    private fun getCache(group: String): BasicCache<String, Any> {
        var cache: BasicCache<String, Any> = instance.getCache(group)
        if (cache is AdvancedCache<*, *>) {
            cache = (cache as AdvancedCache<String, Any>).withFlags(Flag.IGNORE_RETURN_VALUES)
        }
        return cache
    }

    override fun getValue(group: String, key: String): Any? {
        val cache = getCache(group)
        return cache[key]
    }

    override fun removeCacheItem(group: String, prefixKey: String) {
        val cache = instance.getCache<String, Any>(group)
        LOG.debug("Remove cache has prefix $prefixKey in group $group")
        val keys = cache.keys
        keys.forEach {
            if (it.startsWith(prefixKey)) {
                LOG.debug("Remove cache key $it")
                cache.remove(it)
            }
        }
    }

    override fun removeCacheItems(group: String, vararg classes: Class<*>) {
        classes.forEach { removeCacheItem(group, it.name) }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(InfinispanCacheService::class.java)
    }
}

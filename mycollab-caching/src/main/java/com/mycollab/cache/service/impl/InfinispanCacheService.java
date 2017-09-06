package com.mycollab.cache.service.impl;

import com.mycollab.cache.service.CacheService;
import org.apache.commons.collections.CollectionUtils;
import org.infinispan.AdvancedCache;
import org.infinispan.commons.api.BasicCache;
import org.infinispan.context.Flag;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
@Service
@Profile({"production"})
public class InfinispanCacheService implements CacheService {
    private static Logger LOG = LoggerFactory.getLogger(InfinispanCacheService.class);

    @Autowired
    private EmbeddedCacheManager instance;

    @Override
    public void putValue(String group, String key, Object value) {
        BasicCache<String, Object> cache = getCache(group);
        cache.put(key, value);
    }

    private BasicCache<String, Object> getCache(String group) {
        BasicCache<String, Object> cache = instance.getCache(group);
        if (cache instanceof AdvancedCache) {
            cache = ((AdvancedCache<String, Object>) cache).withFlags(Flag.IGNORE_RETURN_VALUES);
        }
        return cache;
    }

    @Override
    public Object getValue(String group, String key) {
        BasicCache<String, Object> cache = getCache(group);
        return cache.get(key);
    }

    @Override
    public void removeCacheItem(String id, String prefixKey) {
        BasicCache<String, Object> cache = instance.getCache(id);
        LOG.debug("Remove cache has prefix {} in group {}", prefixKey, id);
        Set<String> keys = cache.keySet();
        if (CollectionUtils.isNotEmpty(keys)) {

            String[] keyArr = keys.toArray(new String[0]);
            for (String aKeyArr : keyArr) {
                if (aKeyArr.startsWith(prefixKey)) {
                    LOG.debug("Remove cache key {}", aKeyArr);
                    cache.remove(aKeyArr);
                }
            }
        }
    }

    @Override
    public void removeCacheItems(String group, Class<?>... classes) {
        for (Class<?> prefKey : classes) {
            removeCacheItem(group, prefKey.getName());
        }
    }
}

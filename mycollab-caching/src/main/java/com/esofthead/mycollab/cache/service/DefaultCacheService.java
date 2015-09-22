package com.esofthead.mycollab.cache.service;

import com.esofthead.mycollab.cache.CacheObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
@Service
@Order(value = 10)
public class DefaultCacheService implements CacheService {
    private Map<String, Object> cacheManager = new WeakHashMap<>();

    @Override
    public Object getValue(String group, String key) {
        CacheObject<String, Object> cacheObject = getCache(group);
        if (cacheObject != null) {
            return cacheObject.get(key);
        }
        return null;
    }

    private synchronized CacheObject<String, Object> getCache(String group) {
        if (!cacheManager.containsKey(group)) {
            CacheObject<String, Object> newCacheObject = new CacheObject();
            cacheManager.put(group, newCacheObject);
            return newCacheObject;
        }
        return (CacheObject<String, Object>) cacheManager.get(group);
    }

    @Override
    public void putValue(String group, String key, Object value) {
        CacheObject<String, Object> cache = getCache(group);
        cache.put(key, value);
    }

    @Override
    public void removeCacheItems(String group, String prefixKey) {
        CacheObject<String, Object> cache = getCache(group);
        Set<String> keys = cache.keySet();
        if (CollectionUtils.isNotEmpty(keys)) {

            String[] keyArr = keys.toArray(new String[0]);
            for (int i = 0; i < keyArr.length; i++) {
                if (keyArr[i].startsWith(prefixKey)) {
                    cache.remove(keyArr[i]);
                }
            }
        }
    }

    @Override
    public void removeCacheItems(String group, Class<?>... classes) {
        for (Class<?> prefKey : classes) {
            removeCacheItems(group, prefKey.getName());
        }
    }
}

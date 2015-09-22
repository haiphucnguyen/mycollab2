package com.esofthead.mycollab.cache.service;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public interface CacheService {
    String GLOBAL_CACHE = "global";

    Object getValue(String group, String key);

    void putValue(String group, String key, Object value);

    void removeCacheItems(String group, String prefixKey);

    void removeCacheItems(String group, Class<?>... cls);
}

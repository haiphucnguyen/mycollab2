/**
 * This file is part of mycollab-caching-community.
 *
 * mycollab-caching-community is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-caching-community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-caching-community.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.community.cache.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mycollab.cache.service.CacheService;
import com.mycollab.community.cache.CacheObject;
import com.mycollab.core.MyCollabException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
@Service
@Order(value = 10)
public class DefaultCacheService implements CacheService {

    private LoadingCache<String, Object> cacheManager = CacheBuilder.newBuilder()
            .expireAfterAccess(30, TimeUnit.MINUTES).build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String s) throws Exception {
                    return null;
                }
            });

    @Override
    public Object getValue(String group, String key) {
        CacheObject<String, Object> cacheObject = getCache(group);
        if (cacheObject != null) {
            return cacheObject.get(key);
        }
        return null;
    }

    private synchronized CacheObject<String, Object> getCache(String group) {
        try {
            if (cacheManager.get(group) == null) {
                CacheObject<String, Object> newCacheObject = new CacheObject<>();
                cacheManager.put(group, newCacheObject);
                return newCacheObject;
            }
            return (CacheObject<String, Object>) cacheManager.get(group);
        } catch (ExecutionException e) {
            throw new MyCollabException(e);
        }
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

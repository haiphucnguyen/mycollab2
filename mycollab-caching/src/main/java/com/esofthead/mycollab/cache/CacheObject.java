package com.esofthead.mycollab.cache;

import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class CacheObject<K, V> {
    private Map<K, V> objects = new WeakHashMap<>();

    public V get(K key) {
        return objects.get(key);
    }

    public Set<K> keySet() {
        return objects.keySet();
    }

    public void remove(K key) {
        objects.remove(key);
    }

    public void put(K key, V value) {
        objects.put(key, value);
    }
}

package com.esofthead.mycollab.core;

import java.util.Iterator;
import java.util.WeakHashMap;

public class WeakHashmapStack<K, V> extends WeakHashMap<K, V> {

	private static int MAX_SIZE = 5;

	@Override
	public V put(K key, V value) {
		if (this.size() >= MAX_SIZE) {
			Iterator<K> iterator = this.keySet().iterator();
			while (iterator.hasNext()) {
				K keyVal = iterator.next();
				V val = this.get(keyVal);
				if (!NotDisposeClass.class.isAssignableFrom(val.getClass())) {
					this.remove(keyVal);
					break;
				}
			}
		}
		return super.put(key, value);
	}

	public static interface NotDisposeClass {
	}
}

package com.esofthead.mycollab.core;

import java.util.WeakHashMap;

public class WeakHashmapStack<K, V> extends WeakHashMap<K, V> {

	private static int MAX_SIZE = 5;

	@Override
	public V put(K key, V value) {
		if (this.size() >= MAX_SIZE) {
			this.remove(this.keySet().iterator().next());
		}
		return super.put(key, value);
	}

}

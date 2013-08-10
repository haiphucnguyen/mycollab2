package com.esofthead.mycollab.cache;

public interface L2Cache {
	Object get(String key);

	void put(String key, Object value);
}

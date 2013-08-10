package com.esofthead.mycollab.cache;

import java.io.IOException;

import com.esofthead.mycollab.core.MyCollabException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;

public class MemcacheL2Cache implements L2Cache {
	private MemcachedClient client;

	public MemcacheL2Cache() {
		try {
			MemcachedClient c = new MemcachedClient(
					new BinaryConnectionFactory(),
					AddrUtil.getAddresses("localhost:11212"));
		} catch (IOException e) {
			throw new MyCollabException(e);
		}
	}
}

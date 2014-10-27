package com.esofthead.mycollab.jgroups.service.impl;

import java.util.concurrent.locks.Lock;

import org.infinispan.CacheImpl;
import org.infinispan.remoting.transport.Transport;
import org.infinispan.remoting.transport.jgroups.JGroupsTransport;
import org.jgroups.Channel;
import org.jgroups.blocks.locking.LockService;
import org.jgroups.fork.ForkChannel;
import org.jgroups.protocols.CENTRAL_LOCK;
import org.jgroups.stack.Protocol;
import org.jgroups.stack.ProtocolStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.jgroups.service.DistributionLockService;
import com.esofthead.mycollab.jgroups.service.DummyLock;

public class DistributionLockServiceImpl implements DistributionLockService {
	private static final Logger LOG = LoggerFactory
			.getLogger(DistributionLockServiceImpl.class);

	@Override
	public Lock getLock(String lockName) {
		ForkChannel channel = getChannel();
		if (channel != null) {
			ProtocolStack protocolStack = channel.getProtocolStack();
			Protocol tmp = protocolStack.getDownProtocol();
			while (tmp != null) {
				tmp = tmp.getDownProtocol();
			}

			LockService lock_service = new LockService(channel);
			Lock lock = lock_service.getLock(lockName);
			return lock;
		} else {
			return new DummyLock();
		}
	}

	private static ForkChannel getChannel() {
		CacheImpl<Object, Object> cache = (CacheImpl<Object, Object>) LocalCacheManager
				.getCache();
		Transport tp;
		ForkChannel fork_ch;
		try {
			tp = cache.getAdvancedCache().getRpcManager().getTransport();
		} catch (Exception e) {
			return null;
		}

		Channel main_ch = ((JGroupsTransport) tp).getChannel();
		try {
			fork_ch = new ForkChannel(main_ch, "hijack-stack", "lead-hijacker",
					true, ProtocolStack.ABOVE, CENTRAL_LOCK.class);
			return fork_ch;
		} catch (Exception e) {
			LOG.error("Can not init the distributed lock", e);
			return null;
		}
	}

}

package com.esofthead.mycollab.jgroups.service.impl;

import java.util.concurrent.locks.Lock;

import org.infinispan.cache.impl.CacheImpl;
import org.infinispan.remoting.transport.Transport;
import org.infinispan.remoting.transport.jgroups.JGroupsTransport;
import org.jgroups.Channel;
import org.jgroups.blocks.locking.LockService;
import org.jgroups.fork.ForkChannel;
import org.jgroups.protocols.CENTRAL_LOCK;
import org.jgroups.stack.Protocol;
import org.jgroups.stack.ProtocolStack;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.lock.DistributionLockService;

@Service
public class DistributionLockServiceImpl implements DistributionLockService {

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
			return null;
		}
	}

	private static ForkChannel getChannel() {
		try {
			CacheImpl<Object, Object> cache = (CacheImpl<Object, Object>) LocalCacheManager.getCache();
			Transport tp = cache.getAdvancedCache().getCacheManager().getTransport();

			ForkChannel fork_ch;

			Channel main_ch = ((JGroupsTransport) tp).getChannel();

			fork_ch = new ForkChannel(main_ch, "hijack-stack", "lead-hijacker", true, ProtocolStack.ABOVE, CENTRAL_LOCK.class);
			return fork_ch;
		} catch (Exception e) {
			// LOG.error("Can not init the distributed lock", e);
			return null;
		}
	}

}

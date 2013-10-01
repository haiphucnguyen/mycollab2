package com.esofthead.mycollab.jgroups;

import java.util.concurrent.locks.Lock;

import org.infinispan.CacheImpl;
import org.infinispan.remoting.transport.Transport;
import org.infinispan.remoting.transport.jgroups.JGroupsTransport;
import org.jgroups.Channel;
import org.jgroups.blocks.locking.LockService;
import org.jgroups.fork.ForkChannel;
import org.jgroups.protocols.FRAG2;
import org.jgroups.stack.ProtocolStack;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.core.MyCollabException;

public class DistributionLockUtil {
	private static ForkChannel getChannel() {
		CacheImpl<Object, Object> cache = (CacheImpl<Object, Object>) LocalCacheManager
				.getCache();
		Transport tp;
		ForkChannel fork_ch;
		tp = cache.getAdvancedCache().getRpcManager().getTransport();
		Channel main_ch = ((JGroupsTransport) tp).getChannel();
		try {
			fork_ch = new ForkChannel(main_ch, "hijack-stack", "lead-hijacker",
					true, ProtocolStack.ABOVE, FRAG2.class);
			return fork_ch;
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	public static Lock getLock(String lockName) {
		ForkChannel channel = getChannel();
		LockService lock_service = new LockService(channel);
		Lock lock = lock_service.getLock(lockName);
		return lock;
	}
}

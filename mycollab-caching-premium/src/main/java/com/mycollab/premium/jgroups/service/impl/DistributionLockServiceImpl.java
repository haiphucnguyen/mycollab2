package com.mycollab.premium.jgroups.service.impl;

import com.mycollab.cache.service.CacheService;
import com.mycollab.lock.DistributionLockService;
import org.jgroups.blocks.locking.LockService;
import org.jgroups.fork.ForkChannel;
import org.jgroups.stack.Protocol;
import org.jgroups.stack.ProtocolStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;

@Service
public class DistributionLockServiceImpl implements DistributionLockService {
    @Autowired
    private CacheService cacheService;

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
            return lock_service.getLock(lockName);
        } else {
            return null;
        }
    }

    private ForkChannel getChannel() {
//        try {
//            InfinispanCacheService infinispanCacheService = (InfinispanCacheService)cacheService;
//            BasicCache<String, Object> cache = infinispanCacheService.getCache();
//            Transport tp = cache.getAdvancedCache().getCacheManager().getTransport();
//
//            ForkChannel fork_ch;
//
//            Channel main_ch = ((JGroupsTransport) tp).getChannel();
//
//            fork_ch = new ForkChannel(main_ch, "hijack-stack", "lead-hijacker", true, ProtocolStack.ABOVE, CENTRAL_LOCK.class);
//            return fork_ch;
//        } catch (Exception e) {
//            // LOG.error("Can not init the distributed lock", e);
//            return null;
//        }
        return null;
    }

}

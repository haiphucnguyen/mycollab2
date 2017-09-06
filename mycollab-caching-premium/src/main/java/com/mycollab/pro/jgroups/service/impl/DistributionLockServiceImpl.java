package com.mycollab.pro.jgroups.service.impl;

import com.mycollab.lock.DistributionLockService;
import org.jgroups.JChannel;
import org.jgroups.blocks.locking.LockService;
import org.jgroups.protocols.pbcast.NAKACK2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
public class DistributionLockServiceImpl implements DistributionLockService {
    private static Logger LOG = LoggerFactory.getLogger(DistributionLockServiceImpl.class);

    @Override
    public Lock getLock(String lockName) {
        NAKACK2 A;
        try {
            JChannel ch = new JChannel(DistributionLockServiceImpl.class.getClassLoader().getResourceAsStream("jgroups.xml")); // locking.xml needs to have a locking protocol towards the top
            LockService lock_service = new LockService(ch);
            ch.connect("lock-cluster");
            return lock_service.getLock(lockName);
        } catch (Exception e) {
            LOG.error("Error while creating a lock instance", e);
            return null;
        }
    }
}

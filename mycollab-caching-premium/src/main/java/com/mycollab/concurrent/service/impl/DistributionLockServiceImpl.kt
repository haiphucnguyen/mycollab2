package com.mycollab.concurrent.service.impl

import com.mycollab.concurrent.DistributionLockService
import org.jgroups.JChannel
import org.jgroups.blocks.locking.LockService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import java.util.concurrent.locks.Lock

@Component
class DistributionLockServiceImpl : DistributionLockService {

    override fun getLock(lockName: String): Lock? {
        return try {
            val ch = JChannel(DistributionLockServiceImpl::class.java.classLoader.getResourceAsStream("jgroups.xml")) // locking.xml needs to have a locking protocol towards the top
            val lock_service = LockService(ch)
            ch.connect("lock-cluster")
            lock_service.getLock(lockName)
        } catch (e: Exception) {
            LOG.error("Error while creating a lock instance", e)
            null
        }

    }

    companion object {
        private val LOG = LoggerFactory.getLogger(DistributionLockServiceImpl::class.java)
    }
}

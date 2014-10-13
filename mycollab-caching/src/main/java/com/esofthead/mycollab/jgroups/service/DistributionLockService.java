package com.esofthead.mycollab.jgroups.service;

import java.util.concurrent.locks.Lock;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public interface DistributionLockService {
	Lock getLock(String lockName);
}

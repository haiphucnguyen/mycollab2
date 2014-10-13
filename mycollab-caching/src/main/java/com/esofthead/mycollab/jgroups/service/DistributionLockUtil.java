package com.esofthead.mycollab.jgroups.service;

import java.util.concurrent.locks.Lock;

import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class DistributionLockUtil {
	public static Lock getLock(String lockName) {
		try {
			DistributionLockService lockService = ApplicationContextUtil
					.getSpringBean(DistributionLockService.class);
			return lockService.getLock(lockName);
		} catch (Exception e) {
			return new DummyLock();
		}
	}
}

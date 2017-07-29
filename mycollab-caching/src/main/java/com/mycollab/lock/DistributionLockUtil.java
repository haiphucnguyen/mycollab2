package com.mycollab.lock;

import com.mycollab.spring.AppContextUtil;
import org.apache.commons.collections.map.AbstractReferenceMap;
import org.apache.commons.collections.map.ReferenceMap;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class DistributionLockUtil {
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static final Map map = Collections.synchronizedMap(new ReferenceMap(AbstractReferenceMap.WEAK, AbstractReferenceMap.WEAK));

    public static Lock getLock(String lockName) {
        try {
            DistributionLockService lockService = AppContextUtil.getSpringBean(DistributionLockService.class);
            Lock lock = lockService.getLock(lockName);
            if (lock == null) {
                return getStaticDefaultLock(lockName);
            } else {
                return lock;
            }
        } catch (Exception e) {
            return getStaticDefaultLock(lockName);
        }
    }

    public static void removeLock(String lockName) {
        map.remove(lockName);
    }

    @SuppressWarnings("unchecked")
    private static Lock getStaticDefaultLock(String lockName) {
        synchronized (map) {
            Lock lock = (Lock) map.get(lockName);
            if (lock == null) {
                lock = new ReentrantLock();
                map.put(lockName, lock);
            }
            return lock;
        }
    }
}

package com.mycollab.module.crm.service;

import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.MeetingWithBLOBs;
import com.mycollab.module.crm.domain.SimpleMeeting;
import com.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface MeetingService extends IDefaultService<Integer, MeetingWithBLOBs, MeetingSearchCriteria> {

    @Cacheable
    SimpleMeeting findById(Integer meetingId, @CacheKey Integer sAccountId);
}

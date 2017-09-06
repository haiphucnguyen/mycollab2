package com.mycollab.common.service;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.common.domain.TimelineTracking;
import com.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.ICrudService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public interface TimelineTrackingService extends ICrudService<Integer, TimelineTracking> {
    @Cacheable
    Map<String, List<GroupItem>> findTimelineItems(String fieldGroup, List<String> groupVals, Date start, Date end,
                                                   @CacheKey TimelineTrackingSearchCriteria criteria);
}

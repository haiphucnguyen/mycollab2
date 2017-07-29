package com.mycollab.common.service;

import com.mycollab.common.domain.ActivityStreamWithBLOBs;
import com.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.db.persistence.service.IDefaultService;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ActivityStreamService extends IDefaultService<Integer, ActivityStreamWithBLOBs, ActivityStreamSearchCriteria> {

    @CacheEvict
    Integer save(@CacheKey ActivityStreamWithBLOBs activityStream);
}

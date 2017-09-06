package com.mycollab.module.crm.service;

import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.CrmTask;
import com.mycollab.module.crm.domain.SimpleCrmTask;
import com.mycollab.module.crm.domain.criteria.CrmTaskSearchCriteria;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface TaskService extends IDefaultService<Integer, CrmTask, CrmTaskSearchCriteria> {
    @Cacheable
    SimpleCrmTask findById(Integer taskId, @CacheKey Integer sAccountId);
}

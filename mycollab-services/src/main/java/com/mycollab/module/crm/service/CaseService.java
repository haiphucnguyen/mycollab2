package com.mycollab.module.crm.service;

import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.CaseWithBLOBs;
import com.mycollab.module.crm.domain.SimpleCase;
import com.mycollab.module.crm.domain.criteria.CaseSearchCriteria;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface CaseService extends IDefaultService<Integer, CaseWithBLOBs, CaseSearchCriteria> {

    @Cacheable
    SimpleCase findById(Integer caseId, @CacheKey Integer sAccountId);
}

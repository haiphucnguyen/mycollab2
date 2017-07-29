package com.mycollab.module.project.service;

import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface RiskService extends IDefaultService<Integer, Risk, RiskSearchCriteria> {
    @Cacheable
    SimpleRisk findById(Integer riskId, @CacheKey Integer sAccountId);
}

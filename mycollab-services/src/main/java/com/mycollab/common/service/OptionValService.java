package com.mycollab.common.service;

import com.mycollab.common.domain.OptionVal;
import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.ICrudService;

import java.util.List;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public interface OptionValService extends ICrudService<Integer, OptionVal> {

    boolean isExistedOptionVal(String type, String typeVal, String fieldGroup, Integer projectId, Integer sAccountId);

    void createDefaultOptions(Integer sAccountId);

    @Cacheable
    List<OptionVal> findOptionVals(String type, Integer projectId, @CacheKey Integer sAccountId);

    @Cacheable
    List<OptionVal> findOptionValsExcludeClosed(String type, Integer projectId, @CacheKey Integer sAccountId);

    @CacheEvict
    void massUpdateOptionIndexes(List<Map<String, Integer>> mapIndexes, @CacheKey Integer sAccountId);
}

package com.mycollab.db.persistence.service;

import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;

import java.util.List;

/**
 * @param <S>
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ISearchableService<S extends SearchCriteria> extends IService {
    /**
     * @param criteria
     * @return
     */
    @Cacheable
    Integer getTotalCount(@CacheKey S criteria);

    /**
     * @param searchRequest
     * @return
     */
    @Cacheable
    List findPageableListByCriteria(@CacheKey BasicSearchRequest<S> searchRequest);

    /**
     * @param searchCriteria
     * @param firstIndex
     * @param numberOfItems
     * @return
     */
    @Cacheable
    List findAbsoluteListByCriteria(@CacheKey S searchCriteria, Integer firstIndex, Integer numberOfItems);

    /**
     * @param criteria
     * @param sAccountId
     */
    @CacheEvict
    void removeByCriteria(S criteria, @CacheKey Integer sAccountId);

    /**
     * @param criteria
     * @return
     */
    @Cacheable
    Integer getNextItemKey(@CacheKey S criteria);

    /**
     * @param criteria
     * @return
     */
    @Cacheable
    Integer getPreviousItemKey(@CacheKey S criteria);
}

package com.mycollab.db.persistence.service;

import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;

import java.io.Serializable;
import java.util.List;

/**
 * @param <K>
 * @param <T>
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ICrudService<K extends Serializable, T> extends IService {

    /**
     * @param record
     * @param username
     * @return
     */
    @CacheEvict
    Integer saveWithSession(@CacheKey T record, String username);

    /**
     * @param record
     * @param username
     * @return
     */
    @CacheEvict
    Integer updateWithSession(@CacheKey T record, String username);

    /**
     * @param record
     * @param username
     * @return
     */
    @CacheEvict
    Integer updateSelectiveWithSession(@CacheKey T record, String username);

    /**
     * @param record
     * @param primaryKeys
     * @param accountId
     */
    @CacheEvict
    void massUpdateWithSession(T record, List<K> primaryKeys, @CacheKey Integer accountId);

    /**
     * @param primaryKey
     * @param sAccountId
     * @return
     */
    @Cacheable
    T findByPrimaryKey(K primaryKey, @CacheKey Integer sAccountId);

    /**
     * @param item
     * @param username
     * @param sAccountId
     */
    @CacheEvict
    void removeWithSession(T item, String username, @CacheKey Integer sAccountId);

    /**
     * @param items
     * @param username
     * @param sAccountId
     */
    @CacheEvict
    void massRemoveWithSession(List<T> items, String username, @CacheKey Integer sAccountId);
}

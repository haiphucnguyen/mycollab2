package com.mycollab.common.service;

import com.mycollab.common.domain.CustomViewStore;
import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.ICrudService;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public interface CustomViewStoreService extends ICrudService<Integer, CustomViewStore> {
    @Cacheable
    CustomViewStore getViewLayoutDef(@CacheKey Integer accountId, String username, String viewId);

    @CacheEvict
    void saveOrUpdateViewLayoutDef(@CacheKey CustomViewStore viewStore);
}

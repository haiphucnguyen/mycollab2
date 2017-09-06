package com.mycollab.form.service;

import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IService;
import com.mycollab.form.view.builder.type.DynaForm;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface MasterFormService extends IService {
    @Cacheable
    DynaForm findCustomForm(@CacheKey Integer sAccountId, String moduleName);

    @CacheEvict
    void saveCustomForm(@CacheKey Integer sAccountId, String moduleName, DynaForm form);
}

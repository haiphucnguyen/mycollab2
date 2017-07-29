package com.mycollab.module.ecm.service;

import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.ICrudService;
import com.mycollab.module.ecm.domain.DriveInfo;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface DriveInfoService extends ICrudService<Integer, DriveInfo> {

    @CacheEvict
    void saveOrUpdateDriveInfo(@CacheKey DriveInfo driveInfo);

    @Cacheable
    DriveInfo getDriveInfo(@CacheKey Integer sAccountId);

    @Cacheable
    Long getUsedStorageVolume(@CacheKey Integer sAccountId);
}

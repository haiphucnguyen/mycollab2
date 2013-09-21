package com.esofthead.mycollab.module.ecm.service;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.ecm.domain.DriveInfo;

public interface DriveInfoService extends ICrudService<Integer, DriveInfo> {

	@CacheEvict
	void saveOrUpdateDriveInfo(@CacheKey DriveInfo driveInfo);

	@Cacheable
	DriveInfo getDriveInfo(@CacheKey Integer sAccountId);

	@Cacheable
	Long getUsedStorageVolume(@CacheKey Integer sAccountId);
}

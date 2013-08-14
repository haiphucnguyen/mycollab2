package com.esofthead.mycollab.module.tracker.service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;

public interface VersionService extends
		IDefaultService<Integer, Version, VersionSearchCriteria> {
	@Cacheable
	SimpleVersion findById(int versionId, @CacheKey int sAccountId);
}

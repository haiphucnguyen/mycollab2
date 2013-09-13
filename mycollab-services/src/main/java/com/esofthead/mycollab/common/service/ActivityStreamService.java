package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;

public interface ActivityStreamService extends
		IDefaultService<Integer, ActivityStream, ActivityStreamSearchCriteria> {

	@CacheEvict
	Integer save(@CacheKey ActivityStream activityStream);
}

package com.esofthead.mycollab.module.tracker.service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;

public interface ComponentService extends
		IDefaultService<Integer, Component, ComponentSearchCriteria> {

	@Cacheable
	SimpleComponent findById(int componentId, @CacheKey int sAccountId);
}

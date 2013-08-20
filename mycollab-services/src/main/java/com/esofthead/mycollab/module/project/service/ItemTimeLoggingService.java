package com.esofthead.mycollab.module.project.service;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;

@RemotingDestination
public interface ItemTimeLoggingService
		extends
		IDefaultService<Integer, ItemTimeLogging, ItemTimeLoggingSearchCriteria> {

	@Cacheable
	Double getTotalHoursByCriteria(
			@CacheKey ItemTimeLoggingSearchCriteria criteria);
}

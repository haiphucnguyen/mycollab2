package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;

public interface ItemTimeLoggingService
		extends
		IDefaultService<Integer, ItemTimeLogging, ItemTimeLoggingSearchCriteria> {
	
	@Cacheable
	Double getTotalHoursByCriteria(ItemTimeLoggingSearchCriteria criteria);
}

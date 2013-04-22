package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.TimeTrackingItem;
import com.esofthead.mycollab.module.project.domain.criteria.TimeTrackingItemSearchCriteria;

public interface TimeTrackingItemService
		extends
		IDefaultService<Integer, TimeTrackingItem, TimeTrackingItemSearchCriteria> {

}

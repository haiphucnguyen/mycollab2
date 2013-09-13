package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;

public interface MonitorItemService extends ICrudService<Integer, MonitorItem>,
		ISearchableService<MonitorSearchCriteria> {

	boolean isUserWatchingItem(String username, String type, int typeid);
}

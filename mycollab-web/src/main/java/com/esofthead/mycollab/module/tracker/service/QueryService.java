package com.esofthead.mycollab.module.tracker.service;

import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.tracker.domain.Query;
import com.esofthead.mycollab.module.tracker.domain.criteria.QuerySearchCriteria;

public interface QueryService extends
		IDefaultService<Integer, Query, QuerySearchCriteria> {
}

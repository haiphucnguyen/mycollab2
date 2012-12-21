package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.persistence.IDefaultService;

public interface ActivityStreamService extends
		IDefaultService<Integer, ActivityStream, ActivityStreamSearchCriteria> {

}

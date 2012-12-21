package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.persistence.IDefaultService;

public interface ActivityStreamService extends
		IDefaultService<Integer, ActivityStream, ActivityStreamSearchCriteria> {

	void saveActivityStream(int sAccountId, String module, String type,
			int typeid, String action, String createdUser);
}

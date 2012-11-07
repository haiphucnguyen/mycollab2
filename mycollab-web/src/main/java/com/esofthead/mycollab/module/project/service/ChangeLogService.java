package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.project.domain.ChangeLog;
import com.esofthead.mycollab.module.project.domain.criteria.ChangeLogSearchCriteria;

public interface ChangeLogService extends
		IDefaultService<Integer, ChangeLog, ChangeLogSearchCriteria> {

	void saveChangeLog(int projectid, String postedUser, String source,
			int sourceid, String action, String sourceDesc);
}

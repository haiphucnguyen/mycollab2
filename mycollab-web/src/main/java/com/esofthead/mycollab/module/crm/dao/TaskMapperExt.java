package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.domain.criteria.TaskSearchCriteria;

public interface TaskMapperExt extends ISearchableDAO<TaskSearchCriteria> {
	SimpleTask findTaskById(int taskId);
}

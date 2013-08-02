package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.domain.criteria.TodoSearchCriteria;

public interface TaskMapperExt extends ISearchableDAO<TodoSearchCriteria> {
	SimpleTask findTaskById(int taskId);
}

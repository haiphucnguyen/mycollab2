package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleTaskResource;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import java.util.List;

public interface TaskMapperExt extends ISearchableDAO<TaskSearchCriteria>{
	List<SimpleTaskResource> getTaskAssignments(int projectid);

	List<GroupItem> getTaskSummary(TaskSearchCriteria criteria);
}

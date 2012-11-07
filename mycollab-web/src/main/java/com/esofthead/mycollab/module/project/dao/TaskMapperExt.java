package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleTaskResource;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;

public interface TaskMapperExt {
	List<SimpleTaskResource> getTaskAssignments(int projectid);

	int getTotalCount(TaskSearchCriteria criteria);

	List<Risk> findPagableList(TaskSearchCriteria criteria, RowBounds rowBounds);

	void insertAndReturnKey(Task task);

	List<GroupItem> getTaskSummary(TaskSearchCriteria criteria);
}

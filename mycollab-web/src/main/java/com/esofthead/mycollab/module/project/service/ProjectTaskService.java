package com.esofthead.mycollab.module.project.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;

public interface ProjectTaskService extends
		IDefaultService<Integer, Task, TaskSearchCriteria> {

	List<GroupItem> getTaskSummary(TaskSearchCriteria criteria);
}

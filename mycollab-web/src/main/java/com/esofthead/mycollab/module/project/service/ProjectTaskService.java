package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.schedule.INotificationSchedulable;

public interface ProjectTaskService extends
		IDefaultService<Integer, Task, TaskSearchCriteria>,
		INotificationSchedulable {
	SimpleTask findTaskById(int taskId);
}

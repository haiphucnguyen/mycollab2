package com.esofthead.mycollab.module.project.service;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;

@RemotingDestination
public interface ProjectTaskService extends
		IDefaultService<Integer, Task, TaskSearchCriteria> {
	@Cacheable
	SimpleTask findById(int taskId, @CacheKey int sAccountId);
}

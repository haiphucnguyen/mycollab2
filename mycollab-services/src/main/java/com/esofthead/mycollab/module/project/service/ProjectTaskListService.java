/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;

/**
 * 
 * @author haiphucnguyen
 */
public interface ProjectTaskListService extends
		IDefaultService<Integer, TaskList, TaskListSearchCriteria> {
	@Cacheable
	SimpleTaskList findById(int taskListId, @CacheKey Integer sAccountId);

	@CacheEvict
	void updateTaskListIndex(TaskList[] taskLists, @CacheKey Integer sAccountId);
}

/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.dao.TaskMapperExt;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.crm.service.TaskService;

@Service
@Transactional
@Traceable(module = "Crm", type = "Task", nameField = "subject")
public class TaskServiceImpl extends
		DefaultService<Integer, Task, TaskSearchCriteria> implements
		TaskService {

	@Autowired
	protected TaskMapper taskMapper;

	@Autowired
	private TaskMapperExt taskMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Task> getCrudMapper() {
		return taskMapper;
	}

	@Override
	public ISearchableDAO<TaskSearchCriteria> getSearchMapper() {
		return taskMapperExt;
	}

	@Override
	public SimpleTask findTaskById(int taskId) {
		return taskMapperExt.findTaskById(taskId);
	}

}

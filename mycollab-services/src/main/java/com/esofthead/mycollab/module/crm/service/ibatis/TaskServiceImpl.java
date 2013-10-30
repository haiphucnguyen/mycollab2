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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.interceptor.aspect.Watchable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.dao.TaskMapperExt;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.TodoSearchCriteria;
import com.esofthead.mycollab.module.crm.service.EventService;
import com.esofthead.mycollab.module.crm.service.TaskService;
import com.esofthead.mycollab.schedule.email.crm.TaskRelayEmailNotificationAction;

@Service
@Transactional
@Traceable(module = "Crm", type = "Task", nameField = "subject")
@Auditable(module = "Crm", type = "Task")
@Watchable(type = CrmTypeConstants.TASK, userFieldName = "assignuser", emailHandlerBean = TaskRelayEmailNotificationAction.class)
public class TaskServiceImpl extends
		DefaultService<Integer, Task, TodoSearchCriteria> implements
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
	public ISearchableDAO<TodoSearchCriteria> getSearchMapper() {
		return taskMapperExt;
	}

	@Override
	public SimpleTask findById(int taskId, int sAccountId) {
		return taskMapperExt.findById(taskId);
	}

	@Override
	public int saveWithSession(Task record, String username) {
		int result = super.saveWithSession(record, username);
		CacheUtils.cleanCaches(record.getSaccountid(), EventService.class);
		return result;
	}

	@Override
	public int updateWithSession(Task record, String username) {
		int result = super.updateWithSession(record, username);
		CacheUtils.cleanCaches(record.getSaccountid(), EventService.class);
		return result;
	}

	@Override
	public int removeWithSession(Integer primaryKey, String username,
			int accountId) {
		int result = super.removeWithSession(primaryKey, username, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
		return result;
	}

	@Override
	public void removeByCriteria(TodoSearchCriteria criteria, int accountId) {
		super.removeByCriteria(criteria, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
	}

	@Override
	public void massRemoveWithSession(List<Integer> primaryKeys,
			String username, int accountId) {
		super.massRemoveWithSession(primaryKeys, username, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
	}

	@Override
	public void massUpdateWithSession(Task record, List<Integer> primaryKeys,
			int accountId) {
		super.massUpdateWithSession(record, primaryKeys, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
	}

	@Override
	public void updateBySearchCriteria(Task record,
			TodoSearchCriteria searchCriteria) {
		super.updateBySearchCriteria(record, searchCriteria);
		CacheUtils.cleanCaches((Integer) searchCriteria.getAccountId()
				.getValue(), EventService.class);
	}

}

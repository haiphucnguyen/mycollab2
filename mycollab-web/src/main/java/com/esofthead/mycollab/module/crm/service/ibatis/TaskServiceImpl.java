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

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.TaskMapperExt;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.crm.service.TaskService;

public class TaskServiceImpl extends DefaultCrudService<Integer, Task>
		implements TaskService {
	
	private TaskMapperExt taskExtDAO;

	public void setTaskExtDAO(TaskMapperExt taskExtDAO) {
		this.taskExtDAO = taskExtDAO;
	}

	public List findPagableListByCriteria(TaskSearchCriteria criteria,
			int skipNum, int maxResult) {
		return taskExtDAO.findPagableList(criteria,
				new RowBounds(skipNum, maxResult));
	}

	public int getTotalCount(TaskSearchCriteria criteria) {
		return taskExtDAO.getTotalCount(criteria);
	}

}

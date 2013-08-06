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

import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.dao.TargetMapper;
import com.esofthead.mycollab.module.crm.dao.TargetMapperExt;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.domain.SimpleTarget;
import com.esofthead.mycollab.module.crm.domain.Target;
import com.esofthead.mycollab.module.crm.domain.TaskExample;
import com.esofthead.mycollab.module.crm.domain.criteria.TargetSearchCriteria;
import com.esofthead.mycollab.module.crm.service.TargetService;

@Service
@Transactional
@Traceable(module = "Crm", type = "Target", nameField = "lastname")
@Auditable(module = "Crm", type = "Target")
public class TargetServiceImpl extends
		DefaultService<Integer, Target, TargetSearchCriteria> implements
		TargetService {

	@Autowired
	private TargetMapper targetMapper;

	@Autowired
	private TargetMapperExt targetMapperExt;

	@Autowired
	private TaskMapper taskMapper;

	@Override
	public ICrudGenericDAO<Integer, Target> getCrudMapper() {
		return targetMapper;
	}

	@Override
	public ISearchableDAO<TargetSearchCriteria> getSearchMapper() {
		return targetMapperExt;
	}

	public int remove(Integer primaryKey) {
		int result = super.remove(primaryKey);
		TaskExample ex = new TaskExample();
		ex.createCriteria().andTypeEqualTo(CrmTypeConstants.TARGET)
				.andTypeidEqualTo(primaryKey);
		taskMapper.deleteByExample(ex);
		return result;
	}

	public SimpleTarget findTargetById(int targetId) {
		return targetMapperExt.findTargetById(targetId);
	}

}

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

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.crm.Constants;
import com.esofthead.mycollab.module.crm.dao.LeadMapper;
import com.esofthead.mycollab.module.crm.dao.LeadMapperExt;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.TaskExample;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

@Service
public class LeadServiceImpl extends DefaultService<Integer, Lead, LeadSearchCriteria>
		implements LeadService {
	
	@Autowired
	private LeadMapper leadMapper;
	
	@Autowired
	private LeadMapperExt leadMapperExt;

	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private TaskMapper taskMapper;
	
	@Override
	public ICrudGenericDAO<Integer, Lead> getCrudMapper() {
		return leadMapper;
	}

	@Override
	public ISearchableDAO<LeadSearchCriteria> getSearchMapper() {
		return leadMapperExt;
	}

	@Override
	public int updateWithSession(Lead record, String username) {
		Lead oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-lead-" + record.getId();
		auditLogService.saveAuditLog(
				username, refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, username);
	}

	public int remove(Integer primaryKey) {
		int result = super.remove(primaryKey);
		TaskExample ex = new TaskExample();
		ex.createCriteria().andTypeEqualTo(Constants.LEAD)
				.andTypeidEqualTo(primaryKey);
		taskMapper.deleteByExample(ex);
		return result;
	}

	public SimpleLead findLeadById(int leadId) {
		return leadMapperExt.findLeadById(leadId);
	}

}

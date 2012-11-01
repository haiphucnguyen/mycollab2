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
import com.esofthead.mycollab.module.crm.Constants;
import com.esofthead.mycollab.module.crm.dao.LeadMapperExt;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.TaskExample;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

public class LeadServiceImpl extends DefaultCrudService<Integer, Lead>
		implements LeadService {
	private LeadMapperExt leadExtDAO;

	private AuditLogService auditLogService;

	public void setLeadExtDAO(LeadMapperExt leadExtDAO) {
		this.leadExtDAO = leadExtDAO;
	}

	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	private TaskMapper taskDAO;

	public void setTaskDAO(TaskMapper taskDAO) {
		this.taskDAO = taskDAO;
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
		taskDAO.deleteByExample(ex);
		return result;
	}

	public List<SimpleLead> findPagableListByCriteria(
			LeadSearchCriteria criteria, int skipNum, int maxResult) {
		return leadExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	public int getTotalCount(LeadSearchCriteria criteria) {
		return leadExtDAO.getTotalCount(criteria);
	}

	public SimpleLead findLeadById(int leadId) {
		return leadExtDAO.findLeadById(leadId);
	}

}

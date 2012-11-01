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
import com.esofthead.mycollab.module.crm.dao.OpportunityMapperExt;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.TaskExample;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

public class OpportunityServiceImpl extends
		DefaultCrudService<Integer, Opportunity> implements OpportunityService {

	private OpportunityMapperExt opportunityExtDAO;

	private AuditLogService auditLogService;

	public void setOpportunityExtDAO(OpportunityMapperExt opportunityExtDAO) {
		this.opportunityExtDAO = opportunityExtDAO;
	}

	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	private TaskMapper taskDAO;

	public void setTaskDAO(TaskMapper taskDAO) {
		this.taskDAO = taskDAO;
	}

	@Override
	public int updateWithSession(Opportunity record, String username) {
		Opportunity oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-opportunity-" + record.getId();
		auditLogService.saveAuditLog(username, refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, username);
	}

	public int remove(Integer primaryKey) {
		int result = super.remove(primaryKey);
		TaskExample ex = new TaskExample();
		ex.createCriteria().andTypeEqualTo(Constants.OPPORTUNITY)
				.andTypeidEqualTo(primaryKey);
		taskDAO.deleteByExample(ex);
		return result;
	}

	public List<SimpleOpportunity> findPagableListByCriteria(
			OpportunitySearchCriteria criteria, int skipNum, int maxResult) {
		return opportunityExtDAO.findPagableList(criteria, new RowBounds(
				skipNum, maxResult));
	}

	public int getTotalCount(OpportunitySearchCriteria criteria) {
		return opportunityExtDAO.getTotalCount(criteria);
	}

	public SimpleOpportunity findOpportunityById(int opportunityId) {
		return opportunityExtDAO.findOpportunityById(opportunityId);
	}

}

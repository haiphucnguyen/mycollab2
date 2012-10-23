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

import com.esofthead.mycollab.core.PlatformManager;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.Constants;
import com.esofthead.mycollab.module.crm.dao.CampaignMapperExt;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.TaskExample;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

public class CampaignServiceImpl extends DefaultCrudService<Campaign, Integer>
		implements CampaignService {
	private TaskMapper taskDAO;

	private CampaignMapperExt campaignExtDAO;

	private AuditLogService auditLogService;

	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	public int remove(Integer primaryKey) {
		int result = super.remove(primaryKey);
		TaskExample ex = new TaskExample();
		ex.createCriteria().andTypeEqualTo(Constants.CAMPAIGN).andTypeidEqualTo(primaryKey);
		taskDAO.deleteByExample(ex);
		return result;
	}

	@Override
	public int updateWithSession(Campaign record, String userSessionId) {
		Campaign oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-campaign-" + record.getId();
		auditLogService.saveAuditLog(
				PlatformManager.getInstance().getSession(userSessionId)
						.getRemoteUser(), refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, userSessionId);
	}

	public void setTaskDAO(TaskMapper taskDAO) {
		this.taskDAO = taskDAO;
	}

	public void setCampaignExtDAO(CampaignMapperExt campaignExtDAO) {
		this.campaignExtDAO = campaignExtDAO;
	}

	public List<SimpleCampaign> findPagableListByCriteria(
			CampaignSearchCriteria criteria, int skipNum, int maxResult) {
		return campaignExtDAO.findPagableList(criteria, new RowBounds(skipNum, maxResult));
	}

	public int getTotalCount(CampaignSearchCriteria criteria) {
		return campaignExtDAO.getTotalCount(criteria);
	}

	public SimpleCampaign findCampaignById(int campaignId) {
		return campaignExtDAO.findCampaignById(campaignId);
	}
}

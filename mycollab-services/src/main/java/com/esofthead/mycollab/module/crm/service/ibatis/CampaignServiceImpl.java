/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.interceptor.aspect.Watchable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.dao.CampaignAccountMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignContactMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignLeadMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignMapperExt;
import com.esofthead.mycollab.module.crm.domain.CampaignAccount;
import com.esofthead.mycollab.module.crm.domain.CampaignAccountExample;
import com.esofthead.mycollab.module.crm.domain.CampaignContact;
import com.esofthead.mycollab.module.crm.domain.CampaignContactExample;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.CampaignLeadExample;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.schedule.email.crm.CampaignRelayEmailNotificationAction;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Service
@Transactional
@Traceable(module = ModuleNameConstants.CRM, type = CrmTypeConstants.CAMPAIGN, nameField = "campaignname")
@Auditable(module = ModuleNameConstants.CRM, type = CrmTypeConstants.CAMPAIGN)
@Watchable(type = CrmTypeConstants.CAMPAIGN, userFieldName = "assignuser", emailHandlerBean = CampaignRelayEmailNotificationAction.class)
public class CampaignServiceImpl extends
		DefaultService<Integer, CampaignWithBLOBs, CampaignSearchCriteria>
		implements CampaignService {

	@Autowired
	private CampaignMapper campaignMapper;
	@Autowired
	private CampaignMapperExt campaignMapperExt;
	@Autowired
	private CampaignAccountMapper campaignAccountMapper;
	@Autowired
	private CampaignContactMapper campaignContactMapper;
	@Autowired
	private CampaignLeadMapper campaignLeadMapper;

	@Override
	public ICrudGenericDAO<Integer, CampaignWithBLOBs> getCrudMapper() {
		return campaignMapper;
	}

	@Override
	public ISearchableDAO<CampaignSearchCriteria> getSearchMapper() {
		return campaignMapperExt;
	}

	@Override
	public SimpleCampaign findById(int campaignId, int sAccountUd) {
		return campaignMapperExt.findById(campaignId);
	}

	@Override
	public void saveCampaignAccountRelationship(
			List<CampaignAccount> associateAccounts, Integer sAccountId) {
		for (CampaignAccount associateAccount : associateAccounts) {
			CampaignAccountExample ex = new CampaignAccountExample();
			ex.createCriteria()
					.andAccountidEqualTo(associateAccount.getAccountid())
					.andCampaignidEqualTo(associateAccount.getCampaignid());
			if (campaignAccountMapper.countByExample(ex) == 0) {
				campaignAccountMapper.insert(associateAccount);
			}
		}
	}

	@Override
	public void removeCampaignAccountRelationship(
			CampaignAccount associateAccount, Integer sAccountId) {
		CampaignAccountExample ex = new CampaignAccountExample();
		ex.createCriteria()
				.andAccountidEqualTo(associateAccount.getAccountid())
				.andCampaignidEqualTo(associateAccount.getCampaignid());
		campaignAccountMapper.deleteByExample(ex);
	}

	@Override
	public void saveCampaignContactRelationship(
			List<CampaignContact> associateContacts, Integer sAccountId) {
		for (CampaignContact associateContact : associateContacts) {
			CampaignContactExample ex = new CampaignContactExample();
			ex.createCriteria()
					.andCampaignidEqualTo(associateContact.getCampaignid())
					.andContactidEqualTo(associateContact.getContactid());
			if (campaignContactMapper.countByExample(ex) == 0) {
				campaignContactMapper.insert(associateContact);
			}
		}
	}

	@Override
	public void removeCampaignContactRelationship(
			CampaignContact associateContact, Integer sAccountId) {
		CampaignContactExample ex = new CampaignContactExample();
		ex.createCriteria()
				.andCampaignidEqualTo(associateContact.getCampaignid())
				.andContactidEqualTo(associateContact.getContactid());
		campaignContactMapper.deleteByExample(ex);
	}

	@Override
	public void saveCampaignLeadRelationship(List<CampaignLead> associateLeads,
			Integer sAccountId) {
		for (CampaignLead associateLead : associateLeads) {
			CampaignLeadExample ex = new CampaignLeadExample();
			ex.createCriteria()
					.andCampaignidEqualTo(associateLead.getCampaignid())
					.andLeadidEqualTo(associateLead.getLeadid());
			if (campaignLeadMapper.countByExample(ex) == 0) {
				campaignLeadMapper.insert(associateLead);
			}
		}
	}

	@Override
	public void removeCampaignLeadRelationship(CampaignLead associateLead,
			Integer sAccountId) {
		CampaignLeadExample ex = new CampaignLeadExample();
		ex.createCriteria().andCampaignidEqualTo(associateLead.getCampaignid())
				.andLeadidEqualTo(associateLead.getLeadid());
		campaignLeadMapper.deleteByExample(ex);
	}
}

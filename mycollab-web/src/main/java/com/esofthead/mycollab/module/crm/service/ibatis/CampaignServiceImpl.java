/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.FlushCache;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.AccountMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignAccountMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignContactMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignLeadMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignMapperExt;
import com.esofthead.mycollab.module.crm.dao.ContactMapper;
import com.esofthead.mycollab.module.crm.dao.LeadMapper;
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

@Service
@Transactional
@Traceable(module = "Crm", type = "Campaign", nameField = "campaignname")
@Auditable(module = "Crm", type = "Campaign")
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
	public SimpleCampaign findById(int campaignId) {
		return campaignMapperExt.findCampaignById(campaignId);
	}

	@Override
	@FlushCache(ids = { CampaignMapper.class, AccountMapper.class })
	public void saveCampaignAccountRelationship(
			List<CampaignAccount> associateAccounts) {
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
	@FlushCache(ids = { CampaignMapper.class, AccountMapper.class })
	public void removeCampaignAccountRelationship(
			CampaignAccount associateAccount) {
		CampaignAccountExample ex = new CampaignAccountExample();
		ex.createCriteria()
				.andAccountidEqualTo(associateAccount.getAccountid())
				.andCampaignidEqualTo(associateAccount.getCampaignid());
		campaignAccountMapper.deleteByExample(ex);
	}

	@Override
	@FlushCache(ids = { CampaignMapper.class, ContactMapper.class })
	public void saveCampaignContactRelationship(
			List<CampaignContact> associateContacts) {
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
	@FlushCache(ids = { CampaignMapper.class, ContactMapper.class })
	public void removeCampaignContactRelationship(
			CampaignContact associateContact) {
		CampaignContactExample ex = new CampaignContactExample();
		ex.createCriteria()
				.andCampaignidEqualTo(associateContact.getCampaignid())
				.andContactidEqualTo(associateContact.getContactid());
		campaignContactMapper.deleteByExample(ex);
	}

	@Override
	@FlushCache(ids = { CampaignMapper.class, LeadMapper.class })
	public void saveCampaignLeadRelationship(List<CampaignLead> associateLeads) {
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
	@FlushCache(ids = { CampaignMapper.class, LeadMapper.class })
	public void removeCampaignLeadRelationship(CampaignLead associateLead) {
		CampaignLeadExample ex = new CampaignLeadExample();
		ex.createCriteria().andCampaignidEqualTo(associateLead.getCampaignid())
				.andLeadidEqualTo(associateLead.getLeadid());
		campaignLeadMapper.deleteByExample(ex);
	}
}

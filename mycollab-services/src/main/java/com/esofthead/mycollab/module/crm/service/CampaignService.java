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
package com.esofthead.mycollab.module.crm.service;

import java.util.List;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.CampaignAccount;
import com.esofthead.mycollab.module.crm.domain.CampaignContact;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;

public interface CampaignService extends
		IDefaultService<Integer, CampaignWithBLOBs, CampaignSearchCriteria> {

	@Cacheable
	SimpleCampaign findById(int campaignId, @CacheKey int sAccountId);

	@CacheEvict(serviceMap = { AccountService.class })
	void saveCampaignAccountRelationship(
			List<CampaignAccount> associateAccounts,
			@CacheKey Integer sAccountId);

	@CacheEvict(serviceMap = { AccountService.class })
	void removeCampaignAccountRelationship(CampaignAccount associateAccount,
			@CacheKey Integer sAccountId);

	@CacheEvict(serviceMap = { ContactService.class })
	void saveCampaignContactRelationship(
			List<CampaignContact> associateContacts,
			@CacheKey Integer sAccountId);

	@CacheEvict(serviceMap = { ContactService.class })
	void removeCampaignContactRelationship(CampaignContact associateContact,
			@CacheKey Integer sAccountId);

	@CacheEvict(serviceMap = { LeadService.class })
	void saveCampaignLeadRelationship(List<CampaignLead> associateLeads,
			@CacheKey Integer sAccountId);

	@CacheEvict(serviceMap = { LeadService.class })
	void removeCampaignLeadRelationship(CampaignLead associateLead,
			@CacheKey Integer sAccountId);
}

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

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.OpportunityContact;
import com.esofthead.mycollab.module.crm.domain.OpportunityLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;

public interface OpportunityService extends
		IDefaultService<Integer, Opportunity, OpportunitySearchCriteria> {

	@Cacheable
	SimpleOpportunity findById(int opportunityId, @CacheKey int sAccountId);

	@Cacheable
	List<GroupItem> getSalesStageSummary(
			@CacheKey OpportunitySearchCriteria criteria);

	@Cacheable
	List<GroupItem> getPipeline(@CacheKey OpportunitySearchCriteria criteria);

	@Cacheable
	List<GroupItem> getLeadSourcesSummary(
			@CacheKey OpportunitySearchCriteria criteria);

	@CacheEvict(serviceMap = { ContactService.class })
	void saveOpportunityContactRelationship(
			List<OpportunityContact> associateContacts,
			@CacheKey Integer sAccountId);

	@CacheEvict(serviceMap = { ContactService.class })
	void removeOpportunityContactRelationship(
			OpportunityContact associateContact, @CacheKey Integer sAccountId);

	@CacheEvict(serviceMap = { LeadService.class })
	void saveOpportunityLeadRelationship(List<OpportunityLead> associateLeads,
			@CacheKey Integer sAccountId);

	@CacheEvict(serviceMap = { LeadService.class })
	void removeOpportunityLeadRelationship(OpportunityLead associateLead,
			@CacheKey Integer sAccountId);
}

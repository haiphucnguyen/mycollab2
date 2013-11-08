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
package com.esofthead.mycollab.module.crm.service;

import java.util.List;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.ContactCase;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;

public interface ContactService extends
		IDefaultService<Integer, Contact, ContactSearchCriteria> {

	@Cacheable
	SimpleContact findById(int contactId, @CacheKey int sAccountId);

	@CacheEvict(serviceMap = { OpportunityService.class })
	void removeContactOpportunityRelationship(
			ContactOpportunity associateOpportunity,
			@CacheKey Integer sAccountId);

	@CacheEvict(serviceMap = { OpportunityService.class })
	void saveContactOpportunityRelationship(
			List<ContactOpportunity> associateOpportunities,
			@CacheKey Integer accountId);

	@CacheEvict(serviceMap = { CaseService.class })
	void saveContactCaseRelationship(List<ContactCase> associateCases,
			@CacheKey Integer accountId);

	@CacheEvict(serviceMap = { CaseService.class })
	void removeContactCaseRelationship(ContactCase associateCase,
			@CacheKey Integer sAccountId);
}

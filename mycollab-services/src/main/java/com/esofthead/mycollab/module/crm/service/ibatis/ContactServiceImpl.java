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

import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.ContactCaseMapper;
import com.esofthead.mycollab.module.crm.dao.ContactMapper;
import com.esofthead.mycollab.module.crm.dao.ContactMapperExt;
import com.esofthead.mycollab.module.crm.dao.ContactOpportunityMapper;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.ContactCase;
import com.esofthead.mycollab.module.crm.domain.ContactCaseExample;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunityExample;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;

@Service
@Transactional
@Traceable(module = "Crm", type = "Contact", nameField = "lastname")
@Auditable(module = "Crm", type = "Contact")
public class ContactServiceImpl extends
		DefaultService<Integer, Contact, ContactSearchCriteria> implements
		ContactService {

	@Autowired
	private ContactMapper contactMapper;
	@Autowired
	private ContactMapperExt contactMapperExt;
	@Autowired
	private ContactOpportunityMapper contactOpportunityMapper;
	@Autowired
	private ContactCaseMapper contactCaseMapper;

	@Override
	public ICrudGenericDAO<Integer, Contact> getCrudMapper() {
		return contactMapper;
	}

	@Override
	public ISearchableDAO<ContactSearchCriteria> getSearchMapper() {
		return contactMapperExt;
	}

	@Override
	public SimpleContact findById(int contactId, int sAccountId) {
		SimpleContact contact = contactMapperExt.findById(contactId);
		return contact;
	}

	@Override
	public void removeContactOpportunityRelationship(
			ContactOpportunity associateOpportunity, Integer sAccountId) {
		ContactOpportunityExample ex = new ContactOpportunityExample();
		ex.createCriteria()
				.andContactidEqualTo(associateOpportunity.getContactid())
				.andOpportunityidEqualTo(
						associateOpportunity.getOpportunityid());
		contactOpportunityMapper.deleteByExample(ex);
	}

	@Override
	public void saveContactOpportunityRelationship(
			List<ContactOpportunity> associateOpportunities, Integer accountId) {
		for (ContactOpportunity assoOpportunity : associateOpportunities) {
			ContactOpportunityExample ex = new ContactOpportunityExample();
			ex.createCriteria()
					.andContactidEqualTo(assoOpportunity.getContactid())
					.andOpportunityidEqualTo(assoOpportunity.getOpportunityid());
			if (contactOpportunityMapper.countByExample(ex) == 0) {
				contactOpportunityMapper.insert(assoOpportunity);
			}
		}
	}

	@Override
	public void saveContactCaseRelationship(List<ContactCase> associateCases,
			Integer accountId) {
		for (ContactCase associateCase : associateCases) {
			ContactCaseExample ex = new ContactCaseExample();
			ex.createCriteria()
					.andContactidEqualTo(associateCase.getContactid())
					.andCaseidEqualTo(associateCase.getCaseid());
			if (contactCaseMapper.countByExample(ex) == 0) {
				contactCaseMapper.insert(associateCase);
			}
		}
	}

	@Override
	public void removeContactCaseRelationship(ContactCase associateCase,
			Integer sAccountId) {
		ContactCaseExample ex = new ContactCaseExample();
		ex.createCriteria().andContactidEqualTo(associateCase.getContactid())
				.andCaseidEqualTo(associateCase.getCaseid());
		contactCaseMapper.deleteByExample(ex);
	}
}
